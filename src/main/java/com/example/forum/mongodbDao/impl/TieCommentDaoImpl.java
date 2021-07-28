package com.example.forum.mongodbDao.impl;

import com.example.forum.mongodbDao.TieCommentDao;
import com.example.forum.mongodbEntity.TieComment;
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
public class TieCommentDaoImpl implements TieCommentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveTieComment(TieComment tieComment) {
        mongoTemplate.save(tieComment);
    }

    @Override
    public void removeTieComment(int id) {
        mongoTemplate.remove(id);
    }

    @Override
    public void updateComment(TieComment tieComment) {
        Query query = new Query(Criteria.where("commentId").is(tieComment.getCommentId()));
        Update update = new Update();
        update.addToSet("content", tieComment.getContent());
        mongoTemplate.updateFirst(query, update, TieComment.class);
    }

    @Override
    public TieComment findCommentById(int id) {
        return mongoTemplate.findById(id, TieComment.class);
    }
}
