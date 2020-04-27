package com.example.amo1.service.Impl;

import com.example.amo1.mapper.HistoryMapper;
import com.example.amo1.model.History;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;


    @Override
    public ResultMessage recordHistory(History history) {
        this.historyMapper.insert(history);
        return new ResultMessage(true,"记录成功");
    }
}
