package com.example.amo1.controller;

import com.example.amo1.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
