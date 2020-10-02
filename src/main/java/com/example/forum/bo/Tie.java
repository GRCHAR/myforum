package com.example.forum.bo;

import java.sql.Timestamp;

/**
 * @author genghaoran
 */
public class Tie {
    private int id;
    private Timestamp createTime;
    private int createUserId;
    private String title;
    private String content;

    public Tie(){
        this.id = -1;
        this.createTime = null;
        this.createUserId = -1;
        this.content = null;
    }

    public Tie(int id, Timestamp createTime, int createUserId, String title, String content) {
        this.id = id;
        this.createTime = createTime;
        this.createUserId = createUserId;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public String toString() {
        return "Tie{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", createUserId=" + createUserId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
