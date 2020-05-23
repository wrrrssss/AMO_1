package com.example.amo1.service.Impl;

import com.example.amo1.mapper.CollectMapper;
import com.example.amo1.mapper.ListsMapper;
import com.example.amo1.mapper.ManuscriptMapper;
import com.example.amo1.mapper.TagMapper;
import com.example.amo1.model.*;
import com.example.amo1.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("collectService")
public class CollectServiceImpl implements CollectService {

    @Autowired
    private ListsMapper listsMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ManuscriptMapper manuscriptMapper;

    @Override
    public ResultMessage createFavoritesList(Lists lists) {
        //检查是否有同名文件夹存在
        if (listsMapper.ifExist(lists) != null){
            return new ResultMessage(false,"该用户已经存在同名文件夹");
        }

        //创建收藏夹
        listsMapper.insert(lists);

        if(lists.getTag() != null) {
            //将标签插入
            List<String> tags = lists.getTag();

            for (String tag : tags) {
                tagMapper.insert(new Tag(lists.getId(), tag));
            }
        }

        return new ResultMessage(true,"创建成功");
    }

    @Override
    public ResultMessage deleteFavoritesList(int list_id) {
        //检测是否存在该收藏夹
        if (listsMapper.ifDelete(list_id) == null){
            return new ResultMessage(false,"收藏夹已被删除");
        }

        //删除收藏夹信息
        listsMapper.delete(list_id);

        //删除标签信息
        tagMapper.deleteAll(list_id);

        //删除收藏关系信息
        collectMapper.deleteList(list_id);

        return new ResultMessage(true,"删除成功");
    }

    @Override
    public ResultMessage modifyFavoritesList(Lists lists) {
        //检测是否存在该收藏夹
        if (listsMapper.ifDelete(lists.getId()) == null){
            return new ResultMessage(false,"收藏夹已被删除");
        }

        //更新收藏夹名字
        listsMapper.modify(lists);

        //检查标签是否有更新
        List<String> old_tags = tagMapper.getAllTag(lists.getId());

        List<String> new_tags = lists.getTag();

        //增加新的标签
        for (String new_tag : new_tags) {
            //如果不存在就新增标签
            if (!old_tags.contains(new_tag)){
                tagMapper.insert(new Tag(lists.getId(),new_tag));
            }
        }

        //删除旧的标签
        for (String old_tag : old_tags){
            //如果不存在就删除标签
            if (!new_tags.contains(old_tag)){
                tagMapper.deleteOne(new Tag(lists.getId(),old_tag));
            }
        }

        return new ResultMessage(true,"更新成功");
    }

    @Override
    public ResultMessage collect(int manuscript_id, int list_id, int user_id) {
        //稿件是否还存在 todo

        //收藏夹是否还存在 todo

        //是否已经收藏过 todo
        if (collectMapper.ifExist(new Collect(manuscript_id,list_id,user_id))!=null)
            return new ResultMessage(false,"已经收藏进该收藏夹");

        //收藏
        collectMapper.insert(new Collect(manuscript_id,list_id,user_id));

        return new ResultMessage(true,"收藏成功");
    }

    @Override
    public ResultMessage moveOutList(int manuscript_id, int user_id) {
        collectMapper.delete(new Collect(manuscript_id,user_id));
        return new ResultMessage(true,"删除成功");
    }

    @Override
    public List<Lists> getAllList(int user_id) {
        //判断用户id是否存在 todo

        //获取所有的收藏夹
        List<Lists> lists = listsMapper.getAllList(user_id);

        //获取所有收藏夹的标签信息
        for (Lists list : lists){
            list.setTag(tagMapper.getAllTag(list.getId()));
        }

        return lists;
    }

    @Override
    public List<Manuscript> getAllManuscriptOfList(int list_id) {
        //获得所有稿件id
        List<Integer> manuscript_ids = collectMapper.getAllOfList(list_id);

        List<Manuscript> manuscripts = new ArrayList<>();

        //获取所有稿件
        for (int manuscript_id: manuscript_ids) {
            manuscripts.add(manuscriptMapper.selectByPrimaryKey(manuscript_id));
        }

        return manuscripts;
    }
}
