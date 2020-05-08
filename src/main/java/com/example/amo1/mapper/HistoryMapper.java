package com.example.amo1.mapper;

import com.example.amo1.model.History;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 历史记录模块 mapper
 * by wrs
 */

public interface HistoryMapper {

    //新增浏览记录
    void insert(History history);

    //删除某一条记录
    void delete(Integer id);

    //获取用户所有记录
    List<History> getAllHistoryOfAUser(Integer user_id);

}
