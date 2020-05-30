package com.example.amo1.mapper;

import com.example.amo1.model.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendMapper {
    int deleteByPrimaryKey(@Param("user_id") Integer user_id,@Param("friend_id") Integer friend_id);

    int insert(Friend record);

    Friend selectByPrimaryKey(Integer id);

    List<Friend> selectAll(Integer group_id);

    int updateByPrimaryKey(Friend record);

    List<Friend> getFans(Integer i);

    List<Friend> selectByGroup(Integer group_id);

    Friend isFriend(@Param("user_id") Integer user_id,@Param("friend_id") Integer friend_id);

    List<Integer> getAllFansId(Integer i);
}