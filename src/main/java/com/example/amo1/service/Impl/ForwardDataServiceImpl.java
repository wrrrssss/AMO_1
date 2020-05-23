package com.example.amo1.service.Impl;

import com.example.amo1.mapper.ForwardDataMapper;
import com.example.amo1.model.ForwardData;
import com.example.amo1.service.ForwardDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ForwardDataServiceImpl implements ForwardDataService {

    @Autowired
    private ForwardDataMapper forwardDataMapper;

    @Override
    public ForwardData selectByUser(ForwardData forwardData) {
        return forwardDataMapper.selectByUser(forwardData);
    }

    /**
     * 发布动态
     * @param forwardData
     */
    @Override
    public void publishDynamic(ForwardData forwardData) {
        forwardData.setForwardDate(new Date());
        forwardDataMapper.insert(forwardData);
    }

    @Override
    public void delete(Integer id) {
        forwardDataMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ForwardData> selAll(Integer userId) {
        return forwardDataMapper.selectByUserId(userId);
    }
}
