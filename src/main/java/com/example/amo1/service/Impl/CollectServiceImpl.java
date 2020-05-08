package com.example.amo1.service.Impl;

import com.example.amo1.mapper.ListsMapper;
//import com.example.amo1.mapper.TagMapper;
import com.example.amo1.mapper.TagMapper;
import com.example.amo1.model.Lists;
//import com.example.amo1.model.Manuscript;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.model.Tag;
import com.example.amo1.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("collectService")
public class CollectServiceImpl implements CollectService {

    @Autowired
    private ListsMapper listsMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public ResultMessage createFavoritesList(Lists lists) {
        //检查是否有同名文件夹存在
        if (listsMapper.ifExist(lists) != null){
            return new ResultMessage(false,"该用户已经存在同名文件夹");
        }

        //创建收藏夹
        listsMapper.insert(lists);

        //将标签插入
        List<String> tags = lists.getTag();

        for (String tag : tags) {
            tagMapper.insert(new Tag(lists.getId(), tag));
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
    public ResultMessage collect(int manuscript_id, int list_id) {
        return null;
    }

    @Override
    public ResultMessage moveToAnotherList(int manuscript_id, int list_id) {
        return null;
    }

    @Override
    public ResultMessage cancelCollect(int manuscript_id, int list_id) {
        return null;
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

//    @Override
//    public List<Manuscript> getAllManuscriptOfList(int list_id) {
//        return null;
//    }
}
