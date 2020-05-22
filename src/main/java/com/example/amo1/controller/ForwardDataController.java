package com.example.amo1.controller;

import com.example.amo1.model.ForwardData;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.ForwardDataService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 发布动态
     * @param forwardData
     * @return
     */
    @RequestMapping("dynamic")
    public ResultMessage publishDynamic(@RequestBody ForwardData forwardData) {
        forwardDataService.publishDynamic(forwardData);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("发布成功");
        return resultMessage;
    }

    /**
     * 根据动态id删除动态
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public ResultMessage delete(@PathVariable Integer id) {
        forwardDataService.delete(id);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("删除成功");
        return resultMessage;
    }

    /**
     * 根据用户id查询所有动态
     * @param userId
     * @return
     */
    @RequestMapping("selAll/{userId}")
    public List<ForwardData> selByUserId(@PathVariable Integer userId) {
        return forwardDataService.selAll(userId);
    }
}
