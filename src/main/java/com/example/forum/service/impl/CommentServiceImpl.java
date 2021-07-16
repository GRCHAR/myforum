package com.example.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
            return commentDao.insert(new Comment(tieId, userId, content, createTime));
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
            IPage<Comment> iPage = commentDao.selectPage(page, commentQueryWrapper);

            return iPage;
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
