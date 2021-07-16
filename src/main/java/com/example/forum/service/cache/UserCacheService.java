package com.example.forum.service.cache;

import com.example.forum.bo.User;
import com.example.forum.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import com.example.forum.service.cache.IUserCacheService;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;
import java.util.Optional;

/**
 * @author genghaoran
 */
@Service
@CacheConfig(cacheNames = "users")
public class UserCacheService implements IUserCacheService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
        if(redisTemplate.hasKey("user_ount")){
            redisTemplate.opsForValue().set("user_count", "2");
            redisTemplate.opsForValue().set("user_count_lock", "false");
        }

        return Integer.parseInt((String) redisTemplate.opsForValue().get("user_count"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserCount(){
        if(redisTemplate.hasKey("user_ount")){
            redisTemplate.opsForValue().set("user_count", "0");
            redisTemplate.opsForValue().set("user_count_lock", "false");
        }
        if("false".equals(redisTemplate.opsForValue().get("user_count_lock"))){
            redisTemplate.opsForValue().set("user_count_lock", "true");
            redisTemplate.opsForValue().increment("user_count", 1);
            redisTemplate.opsForValue().set("user_count_lock", "false");
        }
    }

    @Override
    public void removeUserCount(){
        if("false".equals(redisTemplate.opsForValue().get("user_count_lock"))){
            redisTemplate.opsForValue().set("user_count_lock", "true");
            redisTemplate.opsForValue().decrement("user_count", 1);
            redisTemplate.opsForValue().set("user_count_lock", "false");
        }
    }




}
