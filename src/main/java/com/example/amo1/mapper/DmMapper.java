package com.example.amo1.mapper;

import com.example.amo1.model.Demo;

public interface DmMapper {
    int insert(Demo demo);

    int del(Integer id);

    int update(Demo demo);

    int sel(Integer id);
}
