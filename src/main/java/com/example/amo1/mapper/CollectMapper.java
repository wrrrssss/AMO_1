package com.example.amo1.mapper;

import com.example.amo1.model.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper {

    void insert(Collect collect);

    void delete(Collect collect);

    void update(Collect collect);

    List<String> selectAll();

    Collect ifExist(Collect collect);

    void deleteList(Integer list_id);

    List<Integer> getAllOfList(Integer list_id);
}
