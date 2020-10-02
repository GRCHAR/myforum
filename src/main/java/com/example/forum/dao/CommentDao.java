package com.example.forum.dao;

import com.example.forum.bo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author genghaoran
 */
public interface CommentDao {

    /**
     * 根据跟帖评论的ID查找对象
     * @param commentId 跟帖评论ID
     * @return 返回跟帖评论对象
     */
    @Select("SELECT * FROM comment WHERE commentId=#{commentId}")
    public Comment getCommentById(int commentId);


    /**
     * 创建评论
     * @param userId 用户ID
     * @param tieId 贴吧ID
     * @param content 评论内容
     * @param createTime 创建时间
     * @return 返回评论ID
     */
    @Insert("INSERT INTO comment(userId, tieId, content, createTime) VALUES(#{userId}, #{tieId}, #{content}, #{createTime})")
    public int createComment(int userId, int tieId, String content, Timestamp createTime);

    /**
     * 根据帖子ID获取该帖子下所有评论
     * @param tieId 帖子ID
     * @param start 起始ID位置
     * @param size 评论页面规格
     * @return 返回帖子下所有评论LIST
     */
    @Select("SELECT * FROM comment WHERE commentId >= (SELECT commentId FROM comment WHERE tieId=#{tieId} ORDER BY commentId LIMIT #{start}, 1) LIMIT #{size}")
    public List<Comment> getCommentByTieId(int tieId, int start, int size);

    /**
     * 根据用户ID获取该用户的所有评论
     * @param userId 用户ID
     * @return 返回该用户的所有评论LIST
     */
    @Select("SELECT * FROM comment WHERE userId=#{userId}")
    public List<Comment> getCommentByUserId(int userId);



}
