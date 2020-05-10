package com.example.amo1.controller;

import com.example.amo1.model.User;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("register")
    public ResultMessage register(@RequestBody User user){
        userService.register(user);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("注册成功");
        return resultMessage;
    }

    @RequestMapping("login")
    public ResultMessage login(@RequestBody User user){
        ResultMessage resultMessage = new ResultMessage();
        if(user.getPassword().equals(userService.login(user).getPassword())) {  //输入的密码等于数据库对应账户的密码
            resultMessage.setSuccess(true);
            resultMessage.setMessage("登录成功");
        } else {
            resultMessage.setSuccess(false);
            resultMessage.setMessage("账户或密码错误");
        }
        return resultMessage;
    }

    @RequestMapping("update")
    public ResultMessage update(@RequestBody User user){
        userService.updateUser(user);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("修改成功");
        return resultMessage;
    }

    @RequestMapping("delete/{id}")
    public ResultMessage delete(@PathVariable Integer id){
        userService.deleteUser(id);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("删除成功");
        return resultMessage;
    }
}
