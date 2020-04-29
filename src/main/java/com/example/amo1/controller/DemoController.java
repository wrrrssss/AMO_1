package com.example.amo1.controller;

import com.example.amo1.model.Demo;
import com.example.amo1.model.ResultMessage;
import com.example.amo1.service.DemoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @RequestMapping(value = "/login")
    public ResultMessage login(@RequestBody Demo demo){
        return demoService.login(demo);
    }

    @RequestMapping(value = "/register")
    public ResultMessage register(@RequestBody Demo demo){
        System.out.println("success");
        System.out.println(demo.getUsername());
        System.out.println(demo.getPassword());
//        return new ResultMessage(true,"注册成功");
        return demoService.register(demo);
    }

    @RequestMapping("insert")
    public ResultMessage insert(@RequestBody Demo demo) {
        demoService.insert(demo);
        return new ResultMessage(true,"注册成功");
    }
}
