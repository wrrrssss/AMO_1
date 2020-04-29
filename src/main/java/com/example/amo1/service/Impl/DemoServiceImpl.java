package com.example.amo1.service.Impl;

import com.example.amo1.mapper.DmMapper;
import com.example.amo1.mapper.DemoMapper;
import com.example.amo1.model.Demo;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private DmMapper dmMapper;

    @Override
    public ResultMessage register(Demo demo) {
        //判断是否有重复 todo
        demoMapper.insert(demo);
        return new ResultMessage(true,"注册成功");
    }

    @Override
    public ResultMessage login(Demo demo) {
        return null;
    }

    @Override
    public Demo getDemo(int id) {
        return null;
    }

    @Override
    public ResultMessage updateDemo(Demo demo) {
        return null;
    }

    @Override
    public ResultMessage deleteDemo(int id) {
        return null;
    }

    @Override
    public void insert(Demo demo) {
        dmMapper.insert(demo);
    }
}
