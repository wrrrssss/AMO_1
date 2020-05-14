package com.example.amo1.service;

import com.example.amo1.model.Lists;
import com.example.amo1.model.Manuscript;
import com.example.amo1.model.ResultMessage;

import java.util.List;

/**
 * 收藏管理模块 service
 * by wrs
 */

public interface CollectService {

    //新建收藏夹
    ResultMessage createFavoritesList(Lists lists);

    //删除收藏夹
    ResultMessage deleteFavoritesList(int list_id);

    //修改收藏夹信息
    ResultMessage modifyFavoritesList(Lists lists);

    //收藏稿件
    ResultMessage collect(int manuscript_id, int list_id, int user_id);

    //取消收藏
    ResultMessage moveOutList(int manuscript_id, int list_id);

    //获得用户的所有收藏夹
    List<Lists> getAllList(int user_id);

    //获得收藏夹所有收藏
    List<Manuscript> getAllManuscriptOfList(int list_id);
}
