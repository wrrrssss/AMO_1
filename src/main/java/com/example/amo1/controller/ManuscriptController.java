package com.example.amo1.controller;

import com.example.amo1.model.*;
import com.example.amo1.service.ManuscriptService;
import com.example.amo1.util.Define;
import com.example.amo1.vo.SearchVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户原创模块，稿件发布
 */
@RestController
@RequestMapping("/manuscript")
public class ManuscriptController {

    @Resource
    private ManuscriptService manuscriptService;

    /**
     * 发布稿件
     * @param manuscript
     * @return
     */
    @RequestMapping("publish")
    public ResultMessage publishManuscript(@RequestBody Manuscript manuscript) {
        manuscriptService.publishManuscript(manuscript);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("发布成功");
        return resultMessage;
    }

    /**
     * 根据id查找稿件
     * @param id
     * @return
     */
    @RequestMapping("selById/{id}")
    public Manuscript selById(@PathVariable Integer id) {
        return manuscriptService.select(id);
    }


    @RequestMapping("list/{currPage}")
    public PageInfo<Manuscript> list(@PathVariable Integer currPage, Integer userId) {
        return manuscriptService.list(currPage, Define.PAGE_SIZE, userId);
    }

    /**
     * 删除稿件
     * @param id
     * @return
     */
    @RequestMapping("delById/{id}")
    public ResultMessage delById(@PathVariable Integer id) {
        manuscriptService.delete(id);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("删除成功");
        return resultMessage;
    }

    /**
     * 全文检索
     * @param currPage
     * @param searchVO
     * @return
     */
    @RequestMapping("search/{currPage}")
    public PageInfo<Manuscript> search(@PathVariable Integer currPage, @RequestBody SearchVO searchVO) {
        System.out.println(searchVO);
        return manuscriptService.search(currPage, Define.PAGE_SIZE, searchVO.getKeyword());
    }

    /**
     * 点赞加一
     * @param loveData
     * @return
     */
    @RequestMapping("loveAddOne")
    public ResultMessage loveAddOne(@RequestBody LoveData loveData) {
        manuscriptService.loveAddOne(loveData);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("点赞成功");
        return resultMessage;
    }

    /**
     * 取消点赞
     * @param loveData
     * @return
     */
    @RequestMapping("cancelLove")
    public ResultMessage cancelLove(@RequestBody LoveData loveData) {
        manuscriptService.cancelLove(loveData);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("取消点赞成功");
        return resultMessage;
    }

    /**
     * 收藏加一
     * @param collectionData
     * @return
     */
    @RequestMapping("collectionAddOne")
    public ResultMessage collectionAddOne(@RequestBody CollectionData collectionData) {
        manuscriptService.collectionAddOne(collectionData);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("收藏成功");
        return resultMessage;
    }

    /**
     * 取消收藏
     * @param collectionData
     * @return
     */
    @RequestMapping("cancelCollection")
    public ResultMessage cancelLove(@RequestBody CollectionData collectionData) {
        manuscriptService.cancelCollection(collectionData);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("取消收藏成功");
        return resultMessage;
    }

    /**
     * 转发加一
     * @param forwardData
     * @return
     */
    @RequestMapping("forwardAddOne")
    public ResultMessage forwardAddOne(@RequestBody ForwardData forwardData) {
        manuscriptService.forwardAddOne(forwardData);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("转发成功");
        return resultMessage;
    }

    /**
     * 取消转发
     * @param forwardData
     * @return
     */
    @RequestMapping("cancelForward")
    public ResultMessage cancelForward(@RequestBody ForwardData forwardData) {
        manuscriptService.cancelForward(forwardData);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("取消转发成功");
        return resultMessage;
    }
}
