package com.example.amo1.service;

import com.example.amo1.model.ForwardData;

import java.util.List;


public interface ForwardDataService {

    ForwardData selectByUser(ForwardData forwardData);

    void publishDynamic(ForwardData forwardData);

    void delete(Integer id);

    List<ForwardData> selAll(Integer userId);
}
