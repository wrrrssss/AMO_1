package com.example.amo1.service;

import com.example.amo1.model.LoveData;
import com.example.amo1.model.Manuscript;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ManuscriptService {
    void publishManuscript(Manuscript manuscript);

    Manuscript select(Integer id);


    void delete(Integer id);

    void update(Manuscript manuscript);

    //分页查询所有稿件
    PageInfo<Manuscript> list(Integer currPage, Integer pageSize, Integer userId);

    //全文检索稿件
    PageInfo<Manuscript> search(Integer currPage, Integer pageSize, String keyword);

    //点赞加一
    void loveAddOne(LoveData loveData);

    //取消点赞
    void cancelLove(LoveData loveData);
}
