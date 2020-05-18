package com.example.amo1.mapper;

import com.example.amo1.model.LoveData;
import java.util.List;

public interface LoveDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoveData record);

    LoveData selectByPrimaryKey(Integer id);

    List<LoveData> selectAll();

    int updateByPrimaryKey(LoveData record);

    int deleteByUser(LoveData loveData);

    LoveData selectByUser(LoveData loveData);
}