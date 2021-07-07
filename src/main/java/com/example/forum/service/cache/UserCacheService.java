package com.example.forum.service.cache;

import com.example.forum.bo.User;
import com.example.forum.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.forum.service.cache.IUserCacheService;

/**
 * @author genghaoran
 */
@Service
@CacheConfig(cacheNames = "users")
public class UserCacheService implements IUserCacheService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    private final Logger logger = LoggerFactory.getLogger(UserCacheService.class);

    @Cacheable(key = "#p0")
    public User selectUser(int id){
        logger.info("redis select user.id:" + id);
        return userDao.getUser(id);
    }

    @Override
    public User addUser(int id){
        logger.info("redis addUser:" + id);
        User user = userDao.getUser(id);
        redisTemplate.opsForValue().set(String.valueOf(id), user);
        return user;
    }

    @Override
    public boolean deleteUser(int id){
        logger.info("redis delete user:" + id);
        boolean deleted = false;
        try{
            deleted = redisTemplate.delete(String.valueOf(id));
        } catch (NullPointerException e){
            logger.info("redis doesn't have user:" + id);
        }
        return deleted;
    }


    @Override
    public int countUser(){
        while(!redisTemplate.hasKey("user_ount")){
            redisTemplate.opsForValue().set("user_count", 0);
        }
        return (Integer)redisTemplate.opsForValue().get("user_count");
    }









}
