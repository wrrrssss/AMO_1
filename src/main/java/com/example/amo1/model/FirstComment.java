package com.example.amo1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FirstComment {
    private Integer id;

    //对应的稿件id
    private Integer manuscriptId;

    //评论人id
    private Integer userId;

    //用户名
    private String username;

    //评论内容
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

    public Integer getManuscriptId() {
        return manuscriptId;
    }

    public void setManuscriptId(Integer manuscriptId) {
        this.manuscriptId = manuscriptId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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