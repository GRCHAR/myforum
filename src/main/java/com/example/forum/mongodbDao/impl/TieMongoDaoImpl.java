package com.example.forum.mongodbDao.impl;

import com.example.forum.mongodbDao.TieMongoDao;
import com.example.forum.mongodbEntity.Tie;
import com.example.forum.mongodbEntity.TieVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author genghaoran
 */
@Component
public class TieMongoDaoImpl implements TieMongoDao {


    private final Logger log = LoggerFactory.getLogger(TieMongoDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void saveTie(Tie tie) {
        log.info("save Tie {}", tie);
        mongoTemplate.save(tie);
    }

    @Override
    public void removeTie(int id) {
        log.info("remove Tie by id: {}", id);
        mongoTemplate.remove(id);
    }

    @Override
    public void updateTie(Tie tie) {
        log.info("update Tie {}", tie);
        Query query = new Query(Criteria.where("commentId").is(tie.getTieId()));
        Update update = new Update();
        update.addToSet("content", tie.getContent());
        update.addToSet("title", tie.getTitle());
        mongoTemplate.updateFirst(query, update, Tie.class);
    }

    @Override
    public Tie findTieById(int id) {
        log.info("find Tie by id: {}", id);
        return mongoTemplate.findById(id, Tie.class);
    }

    @Override
    public List<TieVo> pageTie(int pageNum, int pageSize){
        log.info("tie");
        Query query = new Query();
        if(pageNum == 1){
            query.limit(pageSize);
            List<TieVo> tieVoList = mongoTemplate.find(query, TieVo.class);
            log.info("mongo tie:{}", tieVoList);
            return tieVoList;
        } else if(pageNum > 1){
            int localNumber = (pageNum - 1)*pageSize;
            query.limit(localNumber);
            List<TieVo> tieVoList = mongoTemplate.find(query, TieVo.class);
            String lastTieId = tieVoList.get(tieVoList.size() - 1).getTieId();
            query.addCriteria(Criteria.where("_id").gt(lastTieId));
            query.limit(pageSize);
            return mongoTemplate.find(query, TieVo.class);
        }
        return new ArrayList<>();
    }
}
