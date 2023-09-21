package com.example.forum.service.impl;

import com.example.forum.bo.User;
import com.example.forum.config.ConfigParam;
import com.example.forum.dao.UserDao;
import com.example.forum.service.IUserService;
import com.example.forum.service.cache.IUserCacheService;
import com.example.forum.service.cache.UserCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author genghaoran
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IUserCacheService userCacheService;

    @Autowired
    private ConfigParam configParam;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public int register(String name, String password) {
        logger.info("register, String name:" + name + " String password:" + password);
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.registerUser(user);
        return user.getId();
    }

    @Override
    public int login(String name, String password){
        logger.info("login, String name:" + name + " password:" + password);
        User user = userDao.getUserByNameAndPassword(name, password);
        return user.getId();
    }



    @Override
    public User getUser(int userId){
        User user = new User();
        try{
            user = userDao.getUser(userId);
        }catch (Exception e){
            logger.info("UserServiceImpl: getUser: int userId " + e.getMessage() );
            return null;
        }
        return user;
    }

    @Override
    public boolean isExistUser(String userName, String password){
        User user  = userDao.getUserByNameAndPassword(userName, password);
        return user != null;
    }

    @Override
    public int deleteUser(int userid){
        try{
            return userDao.deleteById(userid);
        } catch (Exception e){
            return 1;
        }
    }

    @Override
    public void uploadUserImage(MultipartFile multipartFile, int userId){
        File fileDir = new File(String.valueOf(configParam.getUserImagePath()));

        File file = new File(configParam.getUserImagePath() + "userId.jpg");
        if(!fileDir.exists()){
            if (!fileDir.mkdirs()) {
                logger.error("not successes mkdir!");
            }
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getUserImage(HttpServletResponse response, int userId){
        try {
            byte[] buffer = new byte[1024];
            File file = new File(configParam.getUserImagePath() + "userId.jpg");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            int readNumber = bufferedInputStream.read(buffer, 0, 1024);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            while(readNumber != -1){
                bufferedOutputStream.write(buffer, 0, readNumber);
                readNumber = bufferedInputStream.read(buffer,0,1024);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
