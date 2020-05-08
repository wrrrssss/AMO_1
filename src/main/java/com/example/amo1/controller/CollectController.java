package com.example.amo1.controller;

import com.example.amo1.model.Lists;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.CollectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 收藏管理模块 controller
 * by wrs
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/collect")
public class CollectController {

    @Resource(name = "collectService")
    private CollectService collectService;


    //新建收藏夹
    @RequestMapping(value = "/createFavoritesList")
    public ResultMessage createFavoritesList(@RequestBody Lists lists){
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        lists.setCreate_time(df.format(new Date()));
        return collectService.createFavoritesList(lists);
    }

    //删除收藏夹
    @RequestMapping(value = "/deleteFavoritesList")
    public ResultMessage deleteFavoritesList(@RequestParam(value = "id")String id){
        return collectService.deleteFavoritesList(Integer.parseInt(id));
    }

    //修改收藏夹信息
    @RequestMapping(value = "/modifyFavoritesList")
    public ResultMessage modifyFavoritesList(@RequestBody Lists lists){
        return collectService.modifyFavoritesList(lists);
    }

    //收藏稿件
    public ResultMessage collect(){
        return null;
    }

    //移动收藏到另一收藏夹
    public ResultMessage moveToAnotherList(){
        return null;
    }

    //取消收藏
    public ResultMessage cancelCollect(){
        return null;
    }

    //获得用户的所有收藏夹
    @RequestMapping(value = "/getAllList")
    public List<Lists> getAllList(@RequestParam(value = "user_id")String user_id){
        return collectService.getAllList(Integer.parseInt(user_id));
    }

//    //获得收藏夹所有收藏 todo
//    public List<Manuscript> getAllManuscriptOfList(){
//        return null;
//    }


}
