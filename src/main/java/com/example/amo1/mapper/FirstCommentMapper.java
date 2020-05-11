package com.example.amo1.mapper;

import com.example.amo1.model.FirstComment;
import java.util.List;

public interface FirstCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FirstComment record);

    FirstComment selectByPrimaryKey(Integer id);

    List<FirstComment> selectAll();

    /**
     * 根据稿件id查找一级评论
     * @param id
     * @return
     */
    List<FirstComment> selectByManuscriptId(Integer id);

    int updateByPrimaryKey(FirstComment record);
}