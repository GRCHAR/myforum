package com.example.forum.service;

import com.example.forum.bo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author genghaoran
 */

@Service
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
     * 判断该用户名密码是否正确
     * @param userName 用户名
     * @param password 密码
     * @return 判断结果
     */
    boolean isExistUser(String userName, String password);

    /**
     * 删除用户
     * @param userid 用户ID
     * @return 删除结果
     */
    int deleteUser(int userid);

    void uploadUserImage(MultipartFile multipartFile, int userId);

    void getUserImage(HttpServletResponse response, int userId);
}
