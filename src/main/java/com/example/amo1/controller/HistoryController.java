package com.example.amo1.controller;

import com.example.amo1.model.History;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.HistoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
     * 记录浏览记录
     * 会在离开浏览页面的时候调用
     * @param user_id 用户id
     * @param manuscript_id 稿件id
     * @return 操作是否成功
     */
    @RequestMapping(value = "/recordHistory")
    private ResultMessage recordHistory(@RequestParam(value = "uid")String user_id,
                                        @RequestParam(value = "mid")String manuscript_id){
        return historyService.recordHistory(new History(user_id,manuscript_id));
    }
}
