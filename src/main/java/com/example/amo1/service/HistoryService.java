package com.example.amo1.service;

import com.example.amo1.model.History;
import com.example.amo1.model.ResultMessage;

public interface HistoryService {

    /**
     * 记录浏览记录
     * @param history 浏览记录
     * @return 记录成功与否
     */
    ResultMessage recordHistory(History history);
}
