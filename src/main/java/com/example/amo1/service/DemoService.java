package com.example.amo1.service;

import com.example.amo1.model.Demo;
import com.example.amo1.model.ResultMessage;

/**
 * service示例代码
 * by wrs
 */
public interface DemoService {

    /**
     * 注册
     * @param demo 填写信息
     * @return 注册结果
     */
    ResultMessage register(Demo demo);

    /**
     * 登录
     * @param demo 登录信息
     * @return 登录结果
     */
    ResultMessage login(Demo demo);

    /**
     * 根据id获取信息
     * @param id demoId
     * @return demo
     */
    Demo getDemo(int id);

    /**
     * 修改
     * @param demo 修改信息
     * @return 修改结果
     */
    ResultMessage updateDemo(Demo demo);

    /**
     * 删除
     * @param id 删除id
     * @return 删除结果
     */
    ResultMessage deleteDemo(int id);


}
