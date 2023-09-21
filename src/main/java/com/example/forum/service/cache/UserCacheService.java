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
    private static final String USER_COUNT = "user_count";
    private static final String LOCK = "true";
    private static final String UNLOCK = "false";

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
        redisTemplate.opsForValue().set(String.valueOf(id), LOCK);
        return user;
    }



    @Override
    public boolean deleteUser(int id){
        logger.info("redis delete user:" + id);
        boolean deleted = false;
        try{
            if (redisTemplate.hasKey(String.valueOf(id)) != null) {
                deleted = redisTemplate.delete(String.valueOf(id));
            }
        } catch (NullPointerException e){
            logger.info("redis doesn't have user:" + id);
        }
        return deleted;
    }


    @Override
    public int countUser(){
        if(redisTemplate.hasKey(USER_COUNT)){
            redisTemplate.opsForValue().set(USER_COUNT, "2");
            redisTemplate.opsForValue().set("user_count_lock", UNLOCK);
        }

        return Integer.parseInt((String) redisTemplate.opsForValue().get(USER_COUNT));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserCount(){
        logger.info("addUserCount0");
        try{
            if(!redisTemplate.hasKey(USER_COUNT)){
                logger.info("addUserCount1");
                logger.info("addUserCount has key:user_count");
                redisTemplate.opsForValue().set(USER_COUNT, "1");
                redisTemplate.opsForValue().set("user_count_lock", UNLOCK);
            }
        } catch (NullPointerException e){
            logger.info("addUserCount2");
            redisTemplate.opsForValue().set(USER_COUNT, "1");
            redisTemplate.opsForValue().set("user_count_lock", UNLOCK);
        }
        String user_count_lock = (String) redisTemplate.opsForValue().get("user_count_lock");
        try{
            if(UNLOCK.equals(user_count_lock)){
                logger.info("addUserCount user_count_lock is false");
                redisTemplate.opsForValue().set("user_count_lock", LOCK);
                redisTemplate.opsForValue().increment(USER_COUNT, 1);
                redisTemplate.opsForValue().set("user_count_lock", UNLOCK);
            } else if(LOCK.equals(user_count_lock)){
                logger.info("addUserCount user_count_lock is true");
                //TODO 更换为线程队列进行异步用户统计
                while(LOCK.equals(redisTemplate.opsForValue().get("user_count_lock"))){
                    Thread.sleep(1000);
                }
                redisTemplate.opsForValue().set("user_count_lock", LOCK);
                redisTemplate.opsForValue().increment(USER_COUNT, 1);
                redisTemplate.opsForValue().set("user_count_lock", UNLOCK);
            }
        } catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }



    }



    @Override
    public void removeUserCount(){
        if(UNLOCK.equals(redisTemplate.opsForValue().get("user_count_lock"))){
            redisTemplate.opsForValue().set("user_count_lock", LOCK);
            redisTemplate.opsForValue().decrement(USER_COUNT, 1);
            redisTemplate.opsForValue().set("user_count_lock", UNLOCK);
        }
    }

    @Override
    public boolean getUserById(int userId){
        try{
            if(LOCK.equals(Objects.requireNonNull(redisTemplate.opsForValue().get(String.valueOf(userId))))){
                return true;
            }
        } catch (NullPointerException e){
            return false;
        }
        return false;
    }





}
