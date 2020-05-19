package com.example.amo1.service.Impl;

import com.example.amo1.mapper.LoveDataMapper;
import com.example.amo1.model.LoveData;
import com.example.amo1.service.LoveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoveDataServiceImpl implements LoveDataService {

    @Autowired
    private LoveDataMapper loveDataMapper;

    @Override
    public LoveData selectByUser(LoveData loveData) {
        return loveDataMapper.selectByUser(loveData);
    }
}
