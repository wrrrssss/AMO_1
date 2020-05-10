package com.example.amo1.mapper;

import com.example.amo1.model.Manuscript;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 实现索引是对应的索引的文档增删改查操作
 */
public interface ManuscriptRepository extends ElasticsearchRepository<Manuscript, Integer> {
}
