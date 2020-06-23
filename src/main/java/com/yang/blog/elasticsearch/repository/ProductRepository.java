package com.yang.blog.elasticsearch.repository;

import com.yang.blog.elasticsearch.entity.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product,Long> {


    @Query("{\n" +
            "    \"match_all\": {}\n" +
            "   }")
    Object tagsNum();


}
