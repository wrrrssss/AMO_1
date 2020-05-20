package com.example.amo1.service.Impl;

import com.example.amo1.mapper.CollectionDataMapper;
import com.example.amo1.model.CollectionData;
import com.example.amo1.service.CollectionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CollectionDataServiceImpl implements CollectionDataService {

    @Autowired
    private CollectionDataMapper collectionDataMapper;

    @Override
    public CollectionData selectByUser(CollectionData collectionData) {
        return collectionDataMapper.selectByUser(collectionData);
    }
}
