package com.example.amo1.controller;

import com.example.amo1.service.DataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 统计模块 controller
 * by wrs
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/data")
public class DataController {

    @Resource(name = "dataService")
    private DataService dataService;
}
