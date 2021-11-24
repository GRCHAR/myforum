package com.example.forum.mongodbDao.impl;

import com.example.forum.mongodbEntity.Tie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * @author genghaoran
 */
@Component
public class TieMongoDaoImpl {


    @Autowired
    private MongoTemplate mongoTemplate;


    public void saveTie(Tie tie) {
        mongoTemplate.save(tie);
    }


    public void removeTie(int id) {
        mongoTemplate.remove(id);
    }


    public void updateTie(Tie tie) {
        Query query = new Query(Criteria.where("commentId").is(tie.getTieId()));
        Update update = new Update();
        update.addToSet("content", tie.getContent());
        update.addToSet("title", tie.getTitle());
        mongoTemplate.updateFirst(query, update, Tie.class);
    }


    public Tie findTieById(int id) {
        return mongoTemplate.findById(id, Tie.class);
    }
}
