package com.example.amo1.controller;

import com.example.amo1.model.Manuscript;
import com.example.amo1.service.ManuscriptService;
import com.example.amo1.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 推荐模块 controller
 * by wrs
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/recommend")
public class RecommendController {

    @Resource(name = "recommendService")
    private RecommendService recommendService;

    //个性化推荐（基于用户的协同过滤算法）
    @RequestMapping(value = "/personalizedRecommend")
    public List<Manuscript> personalizedRecommend(@RequestParam(value = "user_id")String user_id){
        return recommendService.personalizedRecommend(Integer.parseInt(user_id));
    }

    //距离匹配推荐
    @RequestMapping(value = "/distanceMatchingRecommend")
    public List<Manuscript> distanceMatchingRecommend(@RequestParam(value = "user_id") String user_id){
        return recommendService.distanceMatchingRecommend(Integer.parseInt(user_id));
    }

    @RequestMapping(value = "/getAllManuscript")
    public List<Manuscript> getAllManuscript(){
        return recommendService.getAll();
    }


}
