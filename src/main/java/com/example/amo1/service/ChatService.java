package com.example.amo1.service;

import com.example.amo1.model.Chat;
import com.example.amo1.model.ResultMessage;

import java.util.List;

public interface ChatService {

    //发送私聊信息
    ResultMessage sendMessage(Chat chat);

    //获取与某人的所有私聊信息
    List<Chat> getAllMessage(int user1_id,int user2_id);

    //删除某条信息
    ResultMessage deleteMessage(int id);

    //获取用户所有私聊信息
    List<Chat> getAllMessageOfUser(int i);
}
