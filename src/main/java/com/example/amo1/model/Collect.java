package com.example.amo1.model;

public class Collect {

    private int manuscript_id;

    private int list_id;

    private int user_id;

    public Collect(){}

    public Collect(int manuscript_id, int list_id, int user_id) {
        this.setList_id(list_id);
        this.setManuscript_id(manuscript_id);
        this.setUser_id(user_id);
    }

    public Collect(int manuscript_id, int user_id) {
        this.setManuscript_id(manuscript_id);
        this.setUser_id(user_id);
    }

    public int getManuscript_id() {
        return manuscript_id;
    }

    public void setManuscript_id(int manuscript_id) {
        this.manuscript_id = manuscript_id;
    }

    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
