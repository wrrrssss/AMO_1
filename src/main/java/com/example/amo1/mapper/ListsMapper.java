package com.example.amo1.mapper;

import com.example.amo1.model.Lists;

import java.util.List;

public interface ListsMapper {

    //新建收藏夹
    void insert(Lists lists);

    //删除收藏夹
    void delete(Integer id);

    //修改收藏夹信息
    void modify(Lists lists);

    //获得用户的所有收藏夹
    List<Lists> getAllList(Integer user_id);

    //收藏夹是否已经存在
    Lists ifExist(Lists lists);

    //收藏夹是否已经被删除
    Lists ifDelete(Integer id);
}
