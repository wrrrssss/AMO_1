package com.example.amo1.service.Impl;

import com.example.amo1.mapper.ForwardDataMapper;
import com.example.amo1.model.ForwardData;
import com.example.amo1.service.ForwardDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForwardDataServiceImpl implements ForwardDataService {

    @Autowired
    private ForwardDataMapper forwardDataMapper;

    @Override
    public ForwardData selectByUser(ForwardData ForwardData) {
        return forwardDataMapper.selectByUser(ForwardData);
    }
}
