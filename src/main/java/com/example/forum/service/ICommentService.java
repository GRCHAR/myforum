package com.example.forum.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.forum.bo.Comment;
import com.example.forum.mongodbEntity.TieComment;
import com.example.forum.mongodbEntity.TieCommentVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author genghaoran
 */

public interface ICommentService {

    /**
     * 获取评论
     * @param commentId 评论ID
     * @return 返回评论对象
     */
    public Comment getComment(int commentId);

    /**
     * 创建评论
     * @param userId 用户ID
     * @param tieId 帖子ID
     * @param content 评论内容
     * @return 返回评论对象
     */
    public int createComment(int userId, int tieId, String content);

    /**
     * 获取一个帖子下的所有评论
     * @param tieId 帖子ID
     * @param pageIndex 页码
     * @param pageSize 页面规格
     * @return 返回评论LIST
     */
    public IPage<Comment> getCommentListTie(int tieId, int pageIndex, int pageSize);

    /**
     * 获取一个用户的所有评论
     * @param userId 用户ID
     * @return 返回评论LIST
     */
    public List<Comment> getCommentListUser(int userId);

    List<TieCommentVo> getCommentListByMongo(int tieId);
}
