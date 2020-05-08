package com.example.amo1.service.Impl;

import com.example.amo1.mapper.HistoryMapper;
import com.example.amo1.model.History;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;


    @Override
    public ResultMessage recordHistory(History history) {
        this.historyMapper.insert(history);
        return new ResultMessage(true,"记录成功");
    }

    @Override
    public ResultMessage deleteHistory(int id) {
        this.historyMapper.delete(id);
        return new ResultMessage(true,"删除成功");
    }

    @Override
    public List<History> getAllHistoryOfAUser(int user_id) {
        return this.historyMapper.getAllHistoryOfAUser(user_id);
    }
}
