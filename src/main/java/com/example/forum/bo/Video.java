package com.example.forum.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author genghaoran
 */
@Entity(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int userId;
    private String url;
    private String state;

    public Video(){
        this.id = 0;
        this.title = null;
        this.userId = 0;
        this.url = null;
    }


    public Video(String title, int userId){
        this.title = title;
        this.userId = userId;
        this.url = null;
    }

    public Video(int id, String title, int userId, String state){
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                ", url='" + url + '\'' +
                '}';
    }
}
