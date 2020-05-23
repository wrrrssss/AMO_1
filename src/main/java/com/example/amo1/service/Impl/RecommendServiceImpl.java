package com.example.amo1.service.Impl;

import com.example.amo1.mapper.HistoryMapper;
import com.example.amo1.mapper.ListsMapper;
import com.example.amo1.mapper.ManuscriptMapper;
import com.example.amo1.mapper.UserMapper;
import com.example.amo1.model.History;
import com.example.amo1.model.Lists;
import com.example.amo1.model.Manuscript;
import com.example.amo1.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private ManuscriptMapper manuscriptMapper;

    @Autowired
    private ListsMapper listsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public List<Manuscript> personalizedRecommend(int user_id) {

        //使用杰卡德相似系数Jaccard similarity coefficient进行相似度计算，根据相似度进行推荐

        //认为收藏比重 > 浏览比重 比重为0.8:0.2 如果有一边数据缺失认为是0，相似系数越高，相似度越高

        //1、计算用户相似度

        //1.0 获取所有用户ID
        List<Integer> user_ids = userMapper.getAllUserId();

        //获取当前用户的位置
        int index = user_ids.indexOf(user_id);

        //1.1 计算各个用户收藏上的杰卡德相似系数
        List<Double> Jaccard_collect = new ArrayList<>();


        //1.1.1 获取该用户的所有收藏ID（去掉重复）
        List<Integer> user_collectsID = getAllManuscriptID(user_id);

        //如果用户有收藏记录
        if (user_collectsID.size() > 0){

            //1.1.2 计算每个用户收藏上的杰卡德相似系数
            for (int i: user_ids) {
                //如果是他本身，相似度设置为0
                if (i == user_id){
                    Jaccard_collect.add(0.0);
                }
                else{
                    List<Integer> temp_collectsID = getAllManuscriptID(i);
                    //如果没有收藏数,并集为0，交集为原用户的收藏数
                    if (temp_collectsID.size() == 0){
                        Jaccard_collect.add(0.0);
                    }

                    else{
                        Jaccard_collect.add(1.0*calculateCommon(user_collectsID,temp_collectsID)
                                /calculateTogether(user_collectsID,temp_collectsID));
                    }
                }
            }
        }

        //1.2 计算各个用户浏览记录上的杰卡德相似系数
        List<Double> Jaccard_history = new ArrayList<>();

        //1.2.1 获取该用户的所有浏览过的稿件ID（去掉重复）
        List<Integer> user_historyID = getAllHistoryManuscriptID(user_id);

        //如果用户有浏览记录
        if (user_historyID.size() > 0){
            //1.1.2 计算每个用户浏览记录上的杰卡德相似系数
            for (int i: user_ids) {
                //如果是他本身，相似度设置为0
                if (i == user_id){
                    Jaccard_history.add(0.0);
                }
                else{
                    List<Integer> temp_historyID = getAllHistoryManuscriptID(user_id);
                    //如果没有收藏数,并集为0，交集为原用户的收藏数
                    if (temp_historyID.size() == 0){
                        Jaccard_history.add(0.0);
                    }

                    else{
                        Jaccard_history.add(1.0*calculateCommon(user_historyID,temp_historyID)
                                /calculateTogether(user_historyID,temp_historyID));
                    }
                }
            }
        }

        //总的杰卡德相似系数
        List<Double> Jaccard = new ArrayList<>();

        if (Jaccard_history.size() == 0 && Jaccard_collect.size() == 0){
            //随机返回五个
            return manuscriptMapper.selectFive();
        }
        else if (Jaccard_collect.size() == 0 && Jaccard_history.size() > 0){
            Jaccard = Jaccard_history;
        }
        else if (Jaccard_collect.size() > 0 && Jaccard_history.size() == 0){
            Jaccard = Jaccard_collect;
        }
        else{
            for (int i = 0; i < Jaccard_collect.size(); i++) {
                Jaccard.add(Jaccard_collect.get(i)*0.8+Jaccard_history.get(i)*0.2);
            }
        }


        //2、获得最相似用户
        int MaxSimilarityIndex = 0;
        double Max = Jaccard.get(0);
        for (int i = 0; i < Jaccard.size();i++){
            if (Jaccard.get(i) > Max){
                Max = Jaccard.get(i);
                MaxSimilarityIndex = i;
            }
        }

        //3、推荐相似稿件
        List<Integer> similar_collects = getAllManuscriptID(user_ids.get(MaxSimilarityIndex));
        List<Integer> similar_historys = getAllHistoryManuscriptID(user_ids.get(MaxSimilarityIndex));

        List<Manuscript> result = new ArrayList<>();

        int recommend_number = 5;

        for (int i = 0; i < similar_collects.size() && result.size() < recommend_number; i++) {
            if (!user_collectsID.contains(similar_collects.get(i))){
                result.add(manuscriptMapper.selectByPrimaryKey(similar_collects.get(i)));
            }
        }

        if (result.size() < recommend_number){
            for (int i = 0; i < similar_historys.size() && result.size() < recommend_number; i++) {
                if (!user_historyID.contains(similar_historys.get(i))){
                    result.add(manuscriptMapper.selectByPrimaryKey(similar_historys.get(i)));
                }
            }
        }

        if (result.size() < recommend_number){
            List<Manuscript> manuscripts = manuscriptMapper.selectAll();

            int index_temp = 0;
            while (result.size() < recommend_number && index_temp < manuscripts.size()){

                if (!result.contains(manuscripts.get(index_temp))){
                    result.add(manuscripts.get(index_temp));
                }
                index_temp++;

            }
        }

        return result;
    }

    @Override
    public List<Manuscript> distanceMatchingRecommend(int user_id) {
        return manuscriptMapper.selectFive();
    }

    @Override
    public List<Manuscript> getAll() {
        return manuscriptMapper.selectAll();
    }

    @Override
    public List<Manuscript> getManuscriptByUser(int user_id) {
        return manuscriptMapper.selAll(user_id);
    }

    @Override
    public List<Manuscript> searchByTitle(String demo) {
        return manuscriptMapper.searchByTitle(demo);
    }

    private List<Integer> getAllManuscriptID(int user_id){
        //获取该用户的所有收藏夹
        List<Lists> user_Lists = listsMapper.getAllList(user_id);

        //获取该用户的所有收藏ID
        List<Integer> user_collectsID = new ArrayList<>();

        for(Lists lists: user_Lists){
            if (!user_collectsID.contains(lists.getId())){
                user_collectsID.add(lists.getId());
            }
        }

        return user_collectsID;
    }

    private List<Integer> getAllHistoryManuscriptID(int user_id){
        List<History> histories = historyMapper.getAllHistoryOfAUser(user_id);

        List<Integer> result = new ArrayList<>();

        for (History history: histories){
            if (!result.contains(history.getId())){
                result.add(history.getId());
            }
        }

        return result;
    }

    private int calculateCommon(List<Integer> list1,List<Integer> list2){
        int result = 0;

        for (int i: list1){
            if (list2.contains(i))
                result++;
        }

        return result;
    }

    private int calculateTogether(List<Integer> list1,List<Integer> list2){
        return list1.size() + list2.size() - calculateCommon(list1,list2);
    }
}
