package com.example.amo1.service;

import com.example.amo1.model.History;
import com.example.amo1.model.ResultMessage;

import java.util.List;

public interface HistoryService {

    /**
     * 记录历史记录
     * @param history 浏览记录
     * @return 记录成功与否
     */
    ResultMessage recordHistory(History history);

    /**
     * 删除一条历史记录
     * @param id 历史记录id
     * @return 删除成功与否
     */
    ResultMessage deleteHistory(int id);

    /**
     * 获取一个用户的所有历史记录
     * @param user_id 用户id
     * @return 所有历史记录
     */
    List<History> getAllHistoryOfAUser(int user_id);
}
