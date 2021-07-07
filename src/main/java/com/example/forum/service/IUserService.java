package com.example.forum.service;

import com.example.forum.bo.User;
import org.springframework.stereotype.Service;

/**
 * @author genghaoran
 */

public interface IUserService {

    /**
     * 用户注册
     * @param name 用户名
     * @param password 密码
     * @return 返回结果代码
     */
    public int register(String name, String password);

    /**
     * 用户登录
     * @param name 用户名
     * @param password 密码
     * @return 用户ID
     */
    public int login(String name, String password);

    /**
     * 获取用户
     * @param userId 用户ID
     * @return 用户对象
     */
    public User getUser(int userId);

    /**
     * 删除用户
     * @param userid 用户ID
     * @return 删除结果
     */
    int deleteUser(int userid);
}
