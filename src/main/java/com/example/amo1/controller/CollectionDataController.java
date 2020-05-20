package com.example.amo1.controller;

import com.example.amo1.model.CollectionData;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.CollectionDataService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/collectionData")
public class CollectionDataController {

    @Resource
    private CollectionDataService collectionService;

    @RequestMapping("selByUser")
    public ResultMessage selectByUser(@RequestBody CollectionData collectionData) {
        CollectionData qCollectionData = collectionService.selectByUser(collectionData);
        ResultMessage resultMessage = new ResultMessage();
        if(qCollectionData != null) {
            resultMessage.setSuccess(true);
            resultMessage.setMessage("已收藏");
        } else {
            resultMessage.setSuccess(false);
            resultMessage.setMessage("未收藏");
        }
        return resultMessage;
    }
}
