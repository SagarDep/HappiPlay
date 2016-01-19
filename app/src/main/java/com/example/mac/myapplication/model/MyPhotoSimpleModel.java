package com.example.mac.myapplication.model;

/**
 * Created by happi on 16/1/13.
 */
public class MyPhotoSimpleModel {

    private String bimg;
    private int comments;
    private int h;
    private int likes;
    private String pid;
    private String simg;
    private  int w;

    public String getBimg() {
        return bimg;
    }

    public void setBimg(String bimg) {
        this.bimg = bimg;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSimg() {
        return simg;
    }

    public void setSimg(String simg) {
        this.simg = simg;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return "MyPhotoSimpleModel{" +
                "bimg='" + bimg + '\'' +
                ", comments=" + comments +
                ", h=" + h +
                ", likes=" + likes +
                ", pid='" + pid + '\'' +
                ", simg='" + simg + '\'' +
                ", w=" + w +
                '}';
    }
}
