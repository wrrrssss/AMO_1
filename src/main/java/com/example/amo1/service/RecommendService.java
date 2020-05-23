package com.example.amo1.service;

import com.example.amo1.model.Manuscript;

import java.util.List;

public interface RecommendService {

    //个性化推荐
    List<Manuscript> personalizedRecommend(int user_id);

    //距离匹配推荐
    List<Manuscript> distanceMatchingRecommend(int user_id);

    List<Manuscript> getAll();
}
