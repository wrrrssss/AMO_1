package com.example.amo1.service;

import com.example.amo1.model.Manuscript;
import com.github.pagehelper.PageInfo;

public interface ManuscriptService {
    void publishManuscript(Manuscript manuscript);

    Manuscript select(Integer id);

    void delete(Integer id);

    void update(Manuscript manuscript);

    PageInfo<Manuscript> search(Integer currPage, Integer pageSize, String keyword);

}
