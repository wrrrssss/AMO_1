package com.example.amo1.mapper;

import com.example.amo1.model.History;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @class HistoryMapper
 * @description 浏览记录模块
 *
 * @author wrs
 * @date 2020/4/27
 */
@Mapper
public interface HistoryMapper {

    @Insert("insert into yummy.account(user_id,manuscript_id,check_time) " +
            "values (#{user_id},#{manuscript_id},#{check_time})")
    void insert(History history);
}
