package com.example.amo1.controller;

import com.example.amo1.model.Manuscript;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.ManuscriptService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/manuscript")
public class ManuscriptController {

    @Resource
    private ManuscriptService manuscriptService;

    @RequestMapping("publish")
    public ResultMessage publishManuscript(@RequestBody Manuscript manuscript) {
        manuscriptService.publishManuscript(manuscript);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("发布成功");
        return resultMessage;
    }

    @RequestMapping("selById/{id}")
    public Manuscript selById(@PathVariable Integer id) {
        return manuscriptService.select(id);
    }

    @RequestMapping("delById/{id}")
    public ResultMessage delById(@PathVariable Integer id) {
        manuscriptService.delete(id);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("删除成功");
        return resultMessage;
    }
}
