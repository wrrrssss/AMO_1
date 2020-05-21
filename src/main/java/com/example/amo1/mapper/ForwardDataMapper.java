package com.example.amo1.mapper;

import com.example.amo1.model.ForwardData;
import java.util.List;

public interface ForwardDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ForwardData record);

    ForwardData selectByPrimaryKey(Integer id);

    List<ForwardData> selectAll();

    int updateByPrimaryKey(ForwardData record);

    int deleteByUser(ForwardData forwardData);

    ForwardData selectByUser(ForwardData forwardData);
}