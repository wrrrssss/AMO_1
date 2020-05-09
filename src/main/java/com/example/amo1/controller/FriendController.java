package com.example.amo1.controller;

import com.example.amo1.model.Friend;
import com.example.amo1.model.Group;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.FriendService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 好友管理模块 controller
 * by wrs
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/friend")
public class FriendController {

    @Resource(name = "friendService")
    private FriendService friendService;

    //新增好友
    public ResultMessage addFriend(@RequestBody Friend friend){
        return friendService.addFriend(friend);
    }

    //删除好友
    public ResultMessage deleteFriend(@RequestParam(value = "id")String id){
        return friendService.deleteFriend(Integer.parseInt(id));
    }

    //修改好友分组或者备注名信息
    public ResultMessage modifyFriend(@RequestBody Friend friend){
        return friendService.modifyFriend(friend);
    }

    //新增分组 todo 用户创建的时候自动新增一个默认分组
    public ResultMessage addGroup(@RequestBody Group group){
        return friendService.addGroup(group);
    }

    //修改分组
    public ResultMessage modifyGroup(@RequestBody Group group){
        return friendService.modifyGroup(group);
    }

    //删除分组
    public ResultMessage deleteGroup(@RequestParam(value = "group_id")String id){
        return friendService.deleteGroup(Integer.parseInt(id));
    }

    //获取所有分组
    public List<Group> getAllGroup(@RequestParam(value = "user_id")String user_id){
        return friendService.getAllGroup(Integer.parseInt(user_id));
    }

    //获取某组的所有好友
    public List<Friend> getAllFriend(@RequestParam(value = "group_id")String group_id){
        return friendService.getAllFriend(Integer.parseInt(group_id));
    }
}
