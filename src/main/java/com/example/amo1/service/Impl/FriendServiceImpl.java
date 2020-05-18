package com.example.amo1.service.Impl;

import com.example.amo1.mapper.FriendMapper;
import com.example.amo1.mapper.GroupMapper;
import com.example.amo1.model.Friend;
import com.example.amo1.model.Group;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "friendService")
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public ResultMessage addFriend(Friend friend) {
        //判断是否已经存在

        //添加好友
        friendMapper.insert(friend);

        return new ResultMessage(true,"新增成功");
    }

    @Override
    public ResultMessage deleteFriend(int id) {
        friendMapper.deleteByPrimaryKey(id);
        return new ResultMessage(true,"删除成功");
    }

    @Override
    public ResultMessage modifyFriend(Friend friend) {
        friendMapper.updateByPrimaryKey(friend);
        return new ResultMessage(true,"修改成功");
    }

    @Override
    public ResultMessage addGroup(Group group) {
        //判断用户是否存在

        //判断该用户是否已经有同名分组

        //新增分组
        groupMapper.insert(group);
        return new ResultMessage(true,"新增分组成功");
    }

    @Override
    public ResultMessage modifyGroup(Group group) {
        groupMapper.updateByPrimaryKey(group);
        return new ResultMessage(true,"修改分组信息成功");
    }

    @Override
    public ResultMessage deleteGroup(int id) {
        groupMapper.deleteByPrimaryKey(id);
        return new ResultMessage(true,"删除成功");
    }

    @Override
    public List<Group> getAllGroup(int user_id) {
        return groupMapper.selectAll(user_id);
    }

    @Override
    public List<Friend> getAllFriend(int group_id) {
        return friendMapper.selectAll(group_id);
    }

    @Override
    public int getFansNumber(int i) {
        return friendMapper.getFans(i).size();
    }
}
