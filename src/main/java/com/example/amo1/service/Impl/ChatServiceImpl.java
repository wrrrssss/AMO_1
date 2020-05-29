package com.example.amo1.service.Impl;

import com.example.amo1.mapper.ChatMapper;
import com.example.amo1.model.Chat;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public ResultMessage sendMessage(Chat chat) {
        chatMapper.insert(chat);
        return new ResultMessage(true,"发送成功");
    }

    @Override
    public List<Chat> getAllMessage(int user1_id, int user2_id) {
        return chatMapper.getAll(new Chat(user1_id,user2_id));
    }

    @Override
    public ResultMessage deleteMessage(int id) {
        chatMapper.deleteByPrimaryKey(id);
        return new ResultMessage(true,"删除消息成功");
    }

    @Override
    public List<Chat> getAllMessageOfUser(int i) {
        return chatMapper.getAllOfUser(i);
    }
}
