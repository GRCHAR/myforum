package com.example.forum.bo;

public class Live {

    private int id;

    private int userId;

    private String title;

    public Live(){

    }

    public Live(int userId, String title){
        this.userId = userId;
        this.title = title;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
