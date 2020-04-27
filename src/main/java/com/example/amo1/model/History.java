package com.example.amo1.model;


import java.util.Date;

public class History {

    //用户id
    private int user_id;

    //稿件id
    private int manuscript_id;

    //浏览时间
    private Date check_time;

    public History(){

    }

    //前后端交互使用
    public History(String user_id, String manuscript_id){
        this.setUser_id(Integer.parseInt(user_id));
        this.setManuscript_id(Integer.parseInt(manuscript_id));
        this.setCheck_time(new Date());
//        //设置日期格式
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //获取当前时间
//        System.out.println(df.format(new Date()));
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getManuscript_id() {
        return manuscript_id;
    }

    public void setManuscript_id(int manuscript_id) {
        this.manuscript_id = manuscript_id;
    }

    public Date getCheck_time() {
        return check_time;
    }

    public void setCheck_time(Date check_time) {
        this.check_time = check_time;
    }
}
