package com.example.amo1.model;

public class Message {
    private Integer id;

    private String sendTime;

    private Integer sendedrId;

    private Integer recipientId;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public Integer getSendedrId() {
        return sendedrId;
    }

    public void setSendedrId(Integer sendedrId) {
        this.sendedrId = sendedrId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}