package com.example.amo1.controller;

import com.example.amo1.model.ResultMessage;
import com.example.amo1.model.SecondComment;
import com.example.amo1.service.SecondCommentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 二级评论模块
 */
@RestController
@RequestMapping("/secondComment")
public class SecondCommentController {

    @Resource
    private SecondCommentService secondCommentService;

    /**
     * 二级评论
     * @param secondComment
     * @return
     */
    @RequestMapping("add")
    public ResultMessage add(@RequestBody SecondComment secondComment) {
        secondCommentService.add(secondComment);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("评论成功");
        return resultMessage;
    }

    /**
     * 删除二级评论
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public ResultMessage delete(@PathVariable Integer id) {
        secondCommentService.delete(id);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("删除成功");
        return resultMessage;
    }

    /**
     * 根据id查找二级评论
     * @param id
     * @return
     */
    @RequestMapping("sel/{id}")
    public SecondComment select(@PathVariable Integer id) {
        return secondCommentService.select(id);
    }

    /**
     * 根据一级评论的id查找该一级评论下的所有二级评论
     * @param id
     * @return
     */
    @RequestMapping("selAll/{id}")
    public List<SecondComment> selectByFirstCommentId(@PathVariable Integer id) {
        return secondCommentService.selectByFirstCommentId(id);
    }
}
