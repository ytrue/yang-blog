package com.yang.blog.search.repository;

import com.yang.blog.search.entity.EsArticle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<EsArticle, Long> {
}