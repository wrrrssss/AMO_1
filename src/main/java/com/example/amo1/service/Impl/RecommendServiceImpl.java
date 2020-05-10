package com.example.amo1.service.Impl;

import com.example.amo1.mapper.ManuscriptMapper;
import com.example.amo1.model.Manuscript;
import com.example.amo1.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private ManuscriptMapper manuscriptMapper;

    @Override
    public List<Manuscript> personalizedRecommend(int user_id) {
        return manuscriptMapper.selectFive();
    }

    @Override
    public List<Manuscript> distanceMatchingRecommend(int user_id) {
        return manuscriptMapper.selectFive();
    }
}
