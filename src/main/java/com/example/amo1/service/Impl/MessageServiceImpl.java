package com.example.amo1.service.Impl;

import com.example.amo1.mapper.ChatMapper;
import com.example.amo1.mapper.MessageMapper;
import com.example.amo1.model.Chat;
import com.example.amo1.model.Message;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public ResultMessage sendMessage(Message message) {
        messageMapper.insert(message);
        return new ResultMessage(true,"发送成功");
    }

    @Override
    public List<Message> getAllMessage(int user_id) {
        return messageMapper.selectAll(user_id);
    }

    @Override
    public ResultMessage deleteMessage(int id) {
        messageMapper.deleteByPrimaryKey(id);
        return new ResultMessage(true,"删除消息成功");
    }
}
