package com.example.forum.bo;



/**
 * @author genghaoran
 */
public class CsComment {

    private int id;
    private int commentId;
    private int userId;
    private String content;

    public CsComment(int id, int commentId, int userId, String content) {
        this.id = id;
        this.commentId = commentId;
        this.userId = userId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
