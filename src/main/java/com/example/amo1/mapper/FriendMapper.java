package com.example.amo1.mapper;

import com.example.amo1.model.Friend;
import java.util.List;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friend record);

    Friend selectByPrimaryKey(Integer id);

    List<Friend> selectAll(Integer group_id);

    int updateByPrimaryKey(Friend record);

    List<Friend> getFans(Integer i);
}