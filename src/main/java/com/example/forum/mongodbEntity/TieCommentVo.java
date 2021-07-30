package com.example.forum.mongodbEntity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author genghaoran
 */
@Document(collection = "tie_comment")
public class TieCommentVo implements Serializable {

    private int commentId;
    private int tieId;
    private int userId;
    private String content;
    private Date createTime;


    public TieCommentVo(){

    }

    public TieCommentVo(int commentId, int tieId, int userId, String content, Date createTime){
        this.commentId = commentId;
        this.tieId = tieId;
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
    }




    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setTieId(int tieId) {
        this.tieId = tieId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getTieId() {
        return tieId;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
