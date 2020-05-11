package com.example.amo1.model;

import java.util.Date;

public class SecondComment {
    private Integer id;

    //对应的一级评论id
    private Integer firstCommentId;

    //评论人
    private Integer userId;

    //内容
    private String content;

    //点赞数
    private Integer love;

    //评论日期
    private Date commentdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFirstCommentId() {
        return firstCommentId;
    }

    public void setFirstCommentId(Integer firstCommentId) {
        this.firstCommentId = firstCommentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }
}