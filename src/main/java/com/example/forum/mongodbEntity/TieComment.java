package com.example.forum.mongodbEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author genghaoran
 */
@Document(collection = "tie_comment")
public class TieComment implements Serializable {

    @Id
    private String commentId;
    private int tieId;
    private int userId;
    private String content;
    private Timestamp createTime;


    public TieComment(){

    }

    public TieComment(String commentId, int tieId, int userId, String content, Timestamp createTime){
        this.commentId = commentId;
        this.tieId = tieId;
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
    }

    public TieComment(int tieId, int userId, String content, Timestamp createTime){
        this.tieId = tieId;
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
    }




    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setTieId(int tieId) {
        this.tieId = tieId;
    }

    public int getUserId() {
        return userId;
    }

    public String getCommentId() {
        return commentId;
    }

    public int getTieId() {
        return tieId;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
}
