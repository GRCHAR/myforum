package com.example.forum.mongodbEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author genghaoran
 */
@Document(collection = "tie")
public class TieVo implements Serializable {


    @Id
    private String tieId;
    private Date createTime;
    private int createUserId;
    private String title;
    private String content;
    public TieVo(){

    }

    public TieVo(String tieId, Date createTime, int createUserId, String title, String content){
        this.content = content;
        this.createTime = createTime;
        this.tieId = tieId;
        this.createUserId = createUserId;
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setTieId(String tieId) {
        this.tieId = tieId;
    }

    public String getTitle() {
        return title;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public String getTieId() {
        return tieId;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
