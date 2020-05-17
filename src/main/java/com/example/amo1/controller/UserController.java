package com.example.amo1.controller;

import com.example.amo1.model.Group;
import com.example.amo1.model.Lists;
import com.example.amo1.model.User;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.CollectService;
import com.example.amo1.service.FriendService;
import com.example.amo1.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户管理模块
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CollectService collectService;

    @Resource
    private FriendService friendService;

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    public ResultMessage register(@RequestBody User user){
        ResultMessage resultMessage = new ResultMessage();
        User qUser = userService.selByUsername(user.getUsername());
        if(qUser == null) {     //用户名不存在
            userService.register(user);
            //创建用户创建默认收藏夹
            Lists lists = new Lists(String.valueOf(user.getId()), "默认收藏夹", null);
            collectService.createFavoritesList(lists);
            //创建用户创建默认好友分组
            Group group = new Group();
            group.setUserId(user.getId());
            group.setName("默认分组");
            friendService.addGroup(group);
            resultMessage.setSuccess(true);
            resultMessage.setMessage("注册成功");
        } else {
            resultMessage.setSuccess(false);
            resultMessage.setMessage("用户名已存在");
        }
        return resultMessage;
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("login")
    public ResultMessage login(@RequestBody User user){
        ResultMessage resultMessage = new ResultMessage();
        User qUser = userService.login(user);
        if(user.getPassword().equals(qUser.getPassword())) {  //输入的密码等于数据库对应账户的密码
            resultMessage.setSuccess(true);
            resultMessage.setMessage("登录成功");
            resultMessage.setUser(qUser);
        } else {
            resultMessage.setSuccess(false);
            resultMessage.setMessage("账户或密码错误");
        }
        return resultMessage;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping("update")
    public ResultMessage update(@RequestBody User user){
        userService.updateUser(user);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("修改成功");
        return resultMessage;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public ResultMessage delete(@PathVariable Integer id){
        userService.deleteUser(id);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("删除成功");
        return resultMessage;
    }
}
