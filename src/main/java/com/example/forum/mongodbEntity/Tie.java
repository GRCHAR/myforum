package com.example.forum.mongodbEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author genghaoran
 */
@Document(collection = "tie")
public class Tie implements Serializable {

    @Id
    private String tieId;
    private Timestamp createTime;
    private int createUserId;
    private String title;
    private String content;
    public Tie(){

    }

    public Tie(String title, String content, int createUserId, Timestamp createTime){
        this.title = title;
        this.content = content;
        this.createUserId = createUserId;
        this.createTime = createTime;

    }


    public void setTieId(String tieId) {
        this.tieId = tieId;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public String getContent() {
        return content;
    }

    public String getTieId() {
        return tieId;
    }

    public String getTitle() {
        return title;
    }

    public int getCreateUserId() {
        return createUserId;
    }
}
