package com.example.amo1.controller;

import com.example.amo1.model.Chat;
import com.example.amo1.model.Message;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 消息管理模块 controller
 * 有点类似网易云的不能回复的私信一样
 * by wrs
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Resource(name = "messageService")
    private MessageService messageService;

    //发送消息
    @RequestMapping(value = "/sendMessage")
    public ResultMessage sendMessage(@RequestBody Message message){
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        message.setSendTime(df.format(new Date()));
        return messageService.sendMessage(message);
    }

    //获取所有消息
    @RequestMapping(value = "/getAllMessage")
    public List<Message> getAllMessage(@RequestParam(value = "user_id") String user_id){
        return messageService.getAllMessage(Integer.parseInt(user_id));
    }

    //删除某条消息
    @RequestMapping(value = "/deleteMessage")
    public ResultMessage deleteMessage(@RequestParam(value = "id") String id){
        return messageService.deleteMessage(Integer.parseInt(id));

    }
}
