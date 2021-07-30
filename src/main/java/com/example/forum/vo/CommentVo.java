package com.example.forum.vo;


import java.sql.Timestamp;
import java.util.Date;

/**
 * @author genghaoran
 */
public class CommentVo {

    private int commentId;
    private String content;
    private int userId;
    private String name;
    private Timestamp createTime;

    public CommentVo(int commentId, String content, int userId, String name, Timestamp createTime) {
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
        this.name = name;
        this.createTime = createTime;
    }

    public CommentVo(int commentId, String content, int id, String name, Date createTime) {
        this.commentId = commentId;
        this.content = content;
        this.userId = id;
        this.name = name;
        this.createTime = new Timestamp(createTime.getTime());
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
