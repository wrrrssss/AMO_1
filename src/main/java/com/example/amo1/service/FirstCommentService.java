package com.example.amo1.service;

import com.example.amo1.model.FirstComment;

import java.util.List;

public interface FirstCommentService {

    void add(FirstComment firstComment);

    void delete(Integer id);

    FirstComment select(Integer id);

    List<FirstComment> selectByManuscriptId(Integer id);
}
