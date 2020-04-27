package com.example.amo1.model;

/**
 * 返回信息类
 * by wrs
 */
public class ResultMessage {

    private String message;
    private boolean success;
    //登录的时候才用，返回当前登录的id
    private int userId;

    public ResultMessage(){}

    public ResultMessage(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    //登录时使用
    public ResultMessage(boolean success, String message, int userId){
        this.success = success;
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
