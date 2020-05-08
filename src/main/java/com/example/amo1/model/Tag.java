package com.example.amo1.model;

public class Tag {

    private int id;

    private int list_id;

    private String tag;

    public Tag(){}

    public Tag(int id, String s) {
        this.setList_id(id);
        this.setTag(s);
    }

    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
