package com.example.amo1.mapper;

import com.example.amo1.model.Group;
import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    Group selectByPrimaryKey(Integer id);

    List<Group> selectAll(Integer user_id);

    int updateByPrimaryKey(Group record);
}