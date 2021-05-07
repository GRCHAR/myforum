package com.example.forum.service.cache;

import com.example.forum.bo.User;
import com.example.forum.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author genghaoran
 */
@Service
@CacheConfig(cacheNames = "users")
public class UserCacheService {
    @Autowired
    private UserDao userDao;

    private final Logger logger = LoggerFactory.getLogger(UserCacheService.class);

    @Cacheable(key = "#p0")
    public User selectUser(int id){
        logger.info("redis select user.id:" + id);
        return userDao.getUser(id);
    }


}
