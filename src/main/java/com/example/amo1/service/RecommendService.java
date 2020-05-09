package com.example.amo1.service;

import com.example.amo1.model.Manuscript;

import java.util.List;

public interface RecommendService {

    //个性化推荐
    List<Manuscript> personalizedRecommend();

    //距离匹配推荐
    List<Manuscript> distanceMatchingRecommend();
}
