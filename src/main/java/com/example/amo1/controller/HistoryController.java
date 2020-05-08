package com.example.amo1.controller;

import com.example.amo1.model.History;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.HistoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 历史记录模块 controller
 * by wrs
 */

//跨域
@CrossOrigin
//controller
@RestController
//地址
@RequestMapping(value = "/history")
public class HistoryController {

    @Resource(name = "historyService")
    private HistoryService historyService;


    /**
     * 记录历史记录
     * 会在离开历史页面的时候调用
     * @param user_id 用户id
     * @param manuscript_id 稿件id
     * @return 操作是否成功
     */
    @RequestMapping(value = "/recordHistory")
    private ResultMessage recordHistory(@RequestParam(value = "user_id")String user_id,
                                        @RequestParam(value = "manuscript_id")String manuscript_id){
        return historyService.recordHistory(new History(user_id,manuscript_id));
    }

    /**
     * 删除一条浏览记录
     * @param id 浏览记录id
     * @return 操作是否成功
     */
    @RequestMapping(value = "/deleteHistory")
    private ResultMessage deleteHistory(@RequestParam(value = "id")String id){
        return historyService.deleteHistory(Integer.parseInt(id));
    }

    /**
     * 获取一个用户所有浏览记录
     * @param user_id 用户id
     * @return 操作是否成功
     */
    @RequestMapping(value = "/getAllHistoryOfUser")
    private List<History> getAllHistoryOfUser(@RequestParam(value = "user_id")String user_id){
        return historyService.getAllHistoryOfAUser(Integer.parseInt(user_id));
    }
}
