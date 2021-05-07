package com.example.forum.service.impl;

import com.example.forum.bo.Comment;
import com.example.forum.dao.CommentDao;
import com.example.forum.service.ICommentService;
import org.apache.ibatis.annotations.Select;
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
            return commentDao.createComment(userId, tieId, content, createTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Comment> getCommentListTie(int tieId, int pageIndex, int pageSize) {
        try{
            int start = pageIndex * pageSize;
            return commentDao.getCommentByTieId(tieId, start, pageSize);
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



}
