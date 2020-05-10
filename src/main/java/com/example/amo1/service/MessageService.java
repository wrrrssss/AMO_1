package com.example.amo1.service;

import com.example.amo1.model.Chat;
import com.example.amo1.model.Message;
import com.example.amo1.model.ResultMessage;

import java.util.List;

public interface MessageService {

    //发送私聊信息
    ResultMessage sendMessage(Message message);

    //获取用户所有消息
    List<Message> getAllMessage(int user_id);

    //删除某条消息
    ResultMessage deleteMessage(int id);
}
