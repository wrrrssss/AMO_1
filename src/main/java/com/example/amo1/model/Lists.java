package com.example.amo1.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 收藏夹
 * by wrs
 */

public class Lists {

    //id
    private int id;

    //用户id
    private int user_id;

    //收藏夹名
    private String name;

    //标签
    private List<String> tag;

    //创建时间
    private String create_time;

    public Lists(){

    }

    //前后端交互使用
    public Lists(String user_id,String name,List<String> tag){
        this.setUser_id(Integer.parseInt(user_id));
        this.setName(name);
        this.setTag(tag);
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        this.setCreate_time(df.format(new Date()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
}
