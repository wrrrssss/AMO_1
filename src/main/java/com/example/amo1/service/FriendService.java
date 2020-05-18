package com.example.amo1.service;

import com.example.amo1.model.Friend;
import com.example.amo1.model.Group;
import com.example.amo1.model.ResultMessage;

import java.util.List;

public interface FriendService {

    //新增好友
    ResultMessage addFriend(Friend friend);

    //删除好友
    ResultMessage deleteFriend(int id);

    //修改好友分组
    ResultMessage modifyFriend(Friend friend);

    //新增分组 todo 用户创建的时候自动新增一个默认分组
    ResultMessage addGroup(Group group);

    //修改分组
    ResultMessage modifyGroup(Group group);

    //删除分组
    ResultMessage deleteGroup(int id);

    //获取所有分组
    List<Group> getAllGroup(int user_id);

    //获取某组的所有好友
    List<Friend> getAllFriend(int group_id);

    //获取粉丝数
    int getFansNumber(int i);
}
