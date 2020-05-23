package com.example.amo1.service.Impl;

import com.example.amo1.mapper.SecondCommentMapper;
import com.example.amo1.model.SecondComment;
import com.example.amo1.service.SecondCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SecondCommentServiceImpl implements SecondCommentService {

    @Autowired
    private SecondCommentMapper secondCommentMapper;

    @Override
    public void add(SecondComment secondComment) {
        secondComment.setCommentdate(new Date());
        secondCommentMapper.insert(secondComment);
    }

    @Override
    public void delete(Integer id) {
        secondCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SecondComment select(Integer id) {
        return secondCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SecondComment> selectByFirstCommentId(Integer id) {
        return secondCommentMapper.selectByFirstCommentId(id);
    }
}
