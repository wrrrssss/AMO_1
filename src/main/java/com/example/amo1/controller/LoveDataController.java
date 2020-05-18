package com.example.amo1.controller;

import com.example.amo1.model.LoveData;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.LoveDataService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/loveData")
public class LoveDataController {

    @Resource
    private LoveDataService loveDataService;

    @RequestMapping("selByUser")
    public ResultMessage selectByUser(@RequestBody LoveData loveData) {
        LoveData qLoveData = loveDataService.selectByUser(loveData);
        ResultMessage resultMessage = new ResultMessage();
        if(qLoveData != null) {
            resultMessage.setSuccess(true);
            resultMessage.setMessage("已点赞");
        } else {
            resultMessage.setSuccess(false);
            resultMessage.setMessage("未点赞");
        }
        return resultMessage;
    }
}
