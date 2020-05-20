package com.example.amo1.controller;

import com.example.amo1.model.ForwardData;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.ForwardDataService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/forwardData")
public class ForwardDataController {

    @Resource
    private ForwardDataService forwardDataService;

    @RequestMapping("selByUser")
    public ResultMessage selectByUser(@RequestBody ForwardData forwardData) {
        ForwardData qForwardData = forwardDataService.selectByUser(forwardData);
        ResultMessage resultMessage = new ResultMessage();
        if(qForwardData != null) {
            resultMessage.setSuccess(true);
            resultMessage.setMessage("已转发");
        } else {
            resultMessage.setSuccess(false);
            resultMessage.setMessage("未转发");
        }
        return resultMessage;
    }
}
