package com.example.amo1.mapper;

import com.example.amo1.model.SecondComment;
import java.util.List;

public interface SecondCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecondComment record);

    SecondComment selectByPrimaryKey(Integer id);

    List<SecondComment> selectAll();

    /**
     * 根据一级评论id查找二级评论
     * @param id
     * @return
     */
    List<SecondComment> selectByFirstCommentId(Integer id);

    int updateByPrimaryKey(SecondComment record);
}