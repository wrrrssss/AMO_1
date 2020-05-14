package com.example.amo1.controller;

import com.example.amo1.model.FirstComment;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.FirstCommentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 一级评论模块
 */
@RestController
@RequestMapping("/firstComment")
public class FirstCommentController {

    @Resource
    private FirstCommentService firstCommentService;

    /**
     * 一级评论
     * @param firstComment
     * @return
     */
    @RequestMapping("add")
    public ResultMessage add(@RequestBody FirstComment firstComment) {
        firstCommentService.add(firstComment);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("评论成功");
        return resultMessage;
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public ResultMessage delete(@PathVariable Integer id) {
        firstCommentService.delete(id);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("删除成功");
        return resultMessage;
    }

    /**
     * 查找评论
     * @param id
     * @return
     */
    @RequestMapping("sel/{id}")
    public FirstComment select(@PathVariable Integer id) {
        return firstCommentService.select(id);
    }

    /**
     * 根据稿件id查询所有评论
     * @param id
     * @return
     */
    @RequestMapping("selAll/{id}")
    public List<FirstComment> selectByManuscriptId(@PathVariable Integer id) {
        return firstCommentService.selectByManuscriptId(id);
    }
}
