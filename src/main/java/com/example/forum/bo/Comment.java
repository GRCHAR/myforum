package com.example.forum.bo;


import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author genghaoran
 */
@Entity(name = "comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private int tieId;
    private int userId;
    private String content;
    private Timestamp createTime;

    public Timestamp getTimestamp() {
        return createTime;
    }


    public Comment(int commentId, int tieId, int userId, String content, Timestamp createTime) {
        this.commentId = commentId;
        this.tieId = tieId;
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
    }

    public Comment(int tieId, int userId, String content, Timestamp createTime){
        this.tieId = tieId;
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
    }

    public Comment(){
        this.commentId = 0;
        this.content = null;
        this.tieId = 0;
        this.userId = 0;
        this.createTime = null;
    }


    public int getCommentId() {
        return commentId;
    }

    public int getTieId() {
        return tieId;
    }

    public int getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setTieId(int tieId) {
        this.tieId = tieId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
}
