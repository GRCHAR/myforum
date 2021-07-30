package com.example.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.bo.Comment;
import com.example.forum.dao.CommentDao;
import com.example.forum.mongodbDao.TieCommentDao;
import com.example.forum.mongodbEntity.TieComment;
import com.example.forum.mongodbEntity.TieCommentVo;
import com.example.forum.service.ICommentService;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author genghaoran
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    CommentDao commentDao;

    @Autowired
    TieCommentDao tieCommentDao;

    private Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public Comment getComment(int commentId) {
        try{
            return commentDao.getCommentById(commentId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int createComment(int userId, int tieId, String content) {
        try{
            Timestamp createTime = new Timestamp(System.currentTimeMillis());
            Comment comment = new Comment(tieId, userId, content, createTime);
            int result = commentDao.createComment(comment);
            logger.info("commentId:{}", comment.getCommentId());
            tieCommentDao.saveTieComment(new TieComment(comment.getCommentId(), tieId, userId, content, createTime));
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public IPage<Comment> getCommentListTie(int tieId, int pageIndex, int pageSize) {
        try{
            int start = pageIndex * pageSize;
            QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
            Page<Comment> page = new Page<>(1, 5);
            commentQueryWrapper.eq("tie_id", tieId);
            return commentDao.selectPage(page, commentQueryWrapper);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comment> getCommentListUser(int userId) {
        try{
            return commentDao.getCommentByUserId(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TieCommentVo> getCommentListByMongo(int tieId){
        return tieCommentDao.findCommentById(tieId);
    }



}
