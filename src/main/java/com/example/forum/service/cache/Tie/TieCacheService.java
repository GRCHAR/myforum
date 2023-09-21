package com.example.forum.service.cache.Tie;

import com.example.forum.mongodbDao.TieMongoDao;
import com.example.forum.mongodbEntity.Tie;
import com.example.forum.mongodbEntity.TieVo;
import com.example.forum.service.cache.Tie.ITieCacheService;
import com.rabbitmq.tools.json.JSONUtil;
import org.slf4j.LoggerFactory;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author genghaoran
 */
@Service
public class TieCacheService implements ITieCacheService {

    private final Logger log = LoggerFactory.getLogger(TieCacheService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TieMongoDao tieMongoDao;

    private final static String TIE_PAGE = "TiePage";




    @Override
    public List<TieVo> getTiePage(int pageNumber, int pageSize) {
        List<TieVo> tieVoList = new ArrayList<>();
        try {
            if (redisTemplate.hasKey(TIE_PAGE)) {
                Objects.requireNonNull(redisTemplate.opsForList().range(TIE_PAGE, (long) pageNumber * (pageSize - 1),
                        (long) pageSize * pageNumber)).forEach((tie) -> tieVoList.add((TieVo) tie));
            }
            return tieVoList;
        } catch (NullPointerException e) {
            log.error("redis getTiePage error:"+ e.getMessage());
            return null;
        }
    }

    @Override
    public void updateTiePage() {
        List<TieVo> tieVoList = new ArrayList<>();
        try {
            tieVoList = tieMongoDao.pageTie(1, 5000);
            redisTemplate.opsForList().set(TIE_PAGE, 5000, tieVoList);
        } catch (Exception e) {
            log.error("redis updateTiePage error:" + e.getMessage());
        }
    }


}
