package com.example.amo1.mapper;

import com.example.amo1.model.Message;
import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectAll(Integer user_id);

    int updateByPrimaryKey(Message record);
}