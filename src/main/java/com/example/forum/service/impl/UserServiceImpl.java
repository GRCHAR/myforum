package com.example.forum.service.impl;

import com.example.forum.bo.User;
import com.example.forum.dao.UserDao;
import com.example.forum.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author genghaoran
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

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
}
