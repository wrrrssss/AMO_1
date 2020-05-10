package com.example.amo1.mapper;

import com.example.amo1.model.ManuscriptData;
import java.util.List;

public interface ManuscriptDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManuscriptData record);

    ManuscriptData selectByPrimaryKey(Integer id);

    List<ManuscriptData> selectAll();

    int updateByPrimaryKey(ManuscriptData record);
}