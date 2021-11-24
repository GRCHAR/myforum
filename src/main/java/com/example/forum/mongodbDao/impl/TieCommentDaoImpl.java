package com.example.forum.mongodbDao.impl;

import com.example.forum.mongodbDao.TieCommentDao;
import com.example.forum.mongodbEntity.Tie;
import com.example.forum.mongodbEntity.TieComment;
import com.example.forum.mongodbEntity.TieCommentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author genghaoran
 */
@Component
public class TieCommentDaoImpl implements TieCommentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final Logger logger = LoggerFactory.getLogger(TieCommentDaoImpl.class);

    @Override
    public TieComment saveTieComment(TieComment tieComment) {
        TieComment result = mongoTemplate.save(tieComment);
        logger.info("mongoDB save TieComment:{}",result);
        return result;
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
    public List<TieCommentVo> findCommentById(int tieId) {
        Query query = new Query(Criteria.where("tieId").is(tieId));
        return mongoTemplate.find(query, TieCommentVo.class);
    }

    @Override
    public List<TieCommentVo> pageComment(int tieId, int pageNum, int pageSize){
        Query query  = new Query(Criteria.where("tieId").is(tieId));
        if(pageNum == 1){
            query.limit(pageSize);
            return mongoTemplate.find(query, TieCommentVo.class);
        } else if(pageNum > 1){
            int localNumber = (pageNum - 1)*pageSize;
            query.limit(localNumber);
            List<TieComment> tieComments = mongoTemplate.find(query, TieComment.class);
            int lastCommentId = tieComments.get(tieComments.size() - 1).getCommentId();
            query.addCriteria(Criteria.where("_id").gt(lastCommentId));
            query.limit(pageSize);
            return mongoTemplate.find(query, TieCommentVo.class);
        }
        return null;
    }



}
