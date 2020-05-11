package com.example.amo1.service;


import com.example.amo1.model.SecondComment;

import java.util.List;

public interface SecondCommentService {

    void add(SecondComment secondComment);

    void delete(Integer id);

    SecondComment select(Integer id);

    List<SecondComment> selectByFirstCommentId(Integer id);
}
