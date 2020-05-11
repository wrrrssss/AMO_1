package com.example.amo1.service.Impl;

import com.example.amo1.mapper.FirstCommentMapper;
import com.example.amo1.model.FirstComment;
import com.example.amo1.service.FirstCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstCommentServiceImpl implements FirstCommentService {

    @Autowired
    private FirstCommentMapper firstCommentMapper;

    @Override
    public void add(FirstComment firstComment) {
        firstCommentMapper.insert(firstComment);
    }

    @Override
    public void delete(Integer id) {
        firstCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public FirstComment select(Integer id) {
        return firstCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FirstComment> selectByManuscriptId(Integer id) {
        return firstCommentMapper.selectByManuscriptId(id);
    }
}
