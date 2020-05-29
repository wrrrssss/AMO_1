package com.example.amo1.mapper;

import com.example.amo1.model.Chat;
import java.util.List;

public interface ChatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chat record);

    Chat selectByPrimaryKey(Integer id);

    List<Chat> selectAll();

    int updateByPrimaryKey(Chat record);

    List<Chat> getAll(Chat record);

    List<Chat> getAllOfUser(Integer user_id);
}