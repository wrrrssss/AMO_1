package com.example.amo1.service;

import com.example.amo1.model.User;
import com.example.amo1.model.ResultMessage;

/**
 * service示例代码
 * by wrs
 */
public interface UserService {

    /**
     * 注册
     * @param user 填写信息
     * @return 注册结果
     */
    void register(User user);

    /**
     * 登录
     * @param user 登录信息
     * @return 登录结果
     */
    User login(User user);


    /**
     * 修改
     * @param user 修改信息
     * @return 修改结果
     */
    void updateUser(User user);

    /**
     * 删除
     * @param id 删除id
     * @return 删除结果
     */
    void deleteUser(Integer id);

    /**
     * 根据用户名查找信息
     * @param username
     * @return
     */
    User selByUsername(String username);

    /**
     * 根据用户Id查找信息
     * @param userId
     * @return
     */
    User selByUserId(Integer userId);



}
