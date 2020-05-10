package com.example.amo1.service.Impl;

import com.example.amo1.mapper.UserMapper;
import com.example.amo1.model.User;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        //判断是否有重复 todo
        userMapper.insert(user);
    }

    @Override
    public User login(User user) {
        return userMapper.selectByUsername(user.getUsername());
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User selByUsername(String username) {
        return userMapper.selectByUsername(username);
    }


}
