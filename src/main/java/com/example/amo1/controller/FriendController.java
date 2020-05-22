package com.example.amo1.controller;

import com.example.amo1.model.Friend;
import com.example.amo1.model.Group;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.FriendService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @RequestMapping(value = "/addFriend")
    public ResultMessage addFriend(@RequestBody Friend friend){
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        friend.setBeginTime(df.format(new Date()));
        return friendService.addFriend(friend);
    }

    //删除好友
    @RequestMapping(value = "/deleteFriend")
    public ResultMessage deleteFriend(@RequestParam(value = "user_id")String user_id,
                                      @RequestParam(value = "friend_id")String friend_id){
        return friendService.deleteFriend(Integer.parseInt(user_id),Integer.parseInt(friend_id));
    }

    //修改好友分组或者备注名信息
    @RequestMapping(value = "/modifyFriend")
    public ResultMessage modifyFriend(@RequestBody Friend friend){
        return friendService.modifyFriend(friend);
    }

    //新增分组 todo 用户创建的时候自动新增一个默认分组
    @RequestMapping(value = "/addGroup")
    public ResultMessage addGroup(@RequestBody Group group){
        return friendService.addGroup(group);
    }

    //修改分组
    @RequestMapping(value = "/modifyGroup")
    public ResultMessage modifyGroup(@RequestBody Group group){
        return friendService.modifyGroup(group);
    }

    //删除分组
    @RequestMapping(value = "/deleteGroup")
    public ResultMessage deleteGroup(@RequestParam(value = "group_id")String id){
        return friendService.deleteGroup(Integer.parseInt(id));
    }

    //获取所有分组
    @RequestMapping(value = "/getAllGroup")
    public List<Group> getAllGroup(@RequestParam(value = "user_id")String user_id){
        return friendService.getAllGroup(Integer.parseInt(user_id));
    }

    //获取某组的所有好友
    @RequestMapping(value = "/getAllFriend")
    public List<Friend> getAllFriend(@RequestParam(value = "group_id")String group_id){
        return friendService.getAllFriend(Integer.parseInt(group_id));
    }

    //获取粉丝数
    @RequestMapping(value = "/getFansNumber")
    public int getFansNumber(@RequestParam(value = "user_id")String user_id){
        return friendService.getFansNumber(Integer.parseInt(user_id));
    }

    //判断两人是否为好友
    @RequestMapping(value = "/isFriend")
    public boolean isFriend(@RequestParam(value = "user_id")String user_id,
                            @RequestParam(value = "friend_id")String friend_id){
        return friendService.isFriend(Integer.parseInt(user_id),Integer.parseInt(friend_id));
    }
}
