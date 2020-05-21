package com.example.amo1.mapper;

import com.example.amo1.model.CollectionData;
import java.util.List;

public interface CollectionDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CollectionData record);

    CollectionData selectByPrimaryKey(Integer id);

    List<CollectionData> selectAll();

    int updateByPrimaryKey(CollectionData record);

    int deleteByUser(CollectionData collectionData);

    CollectionData selectByUser(CollectionData collectionData);
}