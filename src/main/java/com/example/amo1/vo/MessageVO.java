package com.example.amo1.vo;

import com.example.amo1.model.User;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

/**
 * 客服在线聊天用的vo
 */
@Data
@ToString
public class MessageVO {

    private String title;

    private String content;

    private String type;

    private String code;

    private Date pubDate;

    private Integer userId;

    private Set<User> users;

    private Integer onlineNum;

    private String time;

    private String sender;
}
