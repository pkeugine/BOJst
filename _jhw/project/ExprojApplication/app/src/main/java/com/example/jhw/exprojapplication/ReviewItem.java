package com.example.jhw.exprojapplication;

import android.graphics.drawable.Drawable;


public class ReviewItem {

    private String imagePath;
    private String place;
    private String userId;
    private Integer grade;
    private String opinion;
    private String date;

    public ReviewItem(){}

    public ReviewItem(String userId, String opinion, Integer grade, String date) {
        super();
        this.userId = userId;
        this.opinion = opinion;
        this.grade = grade;
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return this.imagePath;
    }
}
