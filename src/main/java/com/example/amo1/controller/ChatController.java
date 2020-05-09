package com.example.amo1.controller;

import com.example.amo1.model.Chat;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.ChatService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 私聊模块 controller
 * by wrs
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    @Resource(name = "chatService")
    private ChatService chatService;

    //发送私聊信息
    @RequestMapping(value = "/sendMessage")
    public ResultMessage sendMessage(@RequestBody Chat chat){
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        chat.setSendTime(df.format(new Date()));
        return chatService.sendMessage(chat);
    }

    //获取与某人的所有私聊信息
    @RequestMapping(value = "/getAllMessage")
    public List<Chat> getAllMessage(@RequestParam(value = "user1_id") String user1_id,
                                    @RequestParam(value = "user2_id") String user2_id){
        return chatService.getAllMessage(Integer.parseInt(user1_id),Integer.parseInt(user2_id));
    }

    //删除某条信息
    @RequestMapping(value = "/deleteMessage")
    public ResultMessage deleteMessage(@RequestParam(value = "id") String id){
        return chatService.deleteMessage(Integer.parseInt(id));

    }
}
