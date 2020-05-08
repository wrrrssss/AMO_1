package com.example.amo1.mapper;

import com.example.amo1.model.Tag;

import java.util.List;

public interface TagMapper {

    void insert(Tag tag);

    void deleteAll(Integer list_id);

    void deleteOne(Tag tag);

    List<String> getAllTag(Integer list_id);
}
