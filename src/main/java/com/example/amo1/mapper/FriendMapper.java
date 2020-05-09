package com.example.amo1.mapper;

import com.example.amo1.model.Friend;
import java.util.List;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friend record);

    Friend selectByPrimaryKey(Integer id);

    List<Friend> selectAll();

    int updateByPrimaryKey(Friend record);
}