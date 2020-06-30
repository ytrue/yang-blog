package com.yang.blog.search.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class EsCategory {

    @Field(type = FieldType.Long)
    private Integer id;

    @Field(type = FieldType.Keyword)
    private String title;
}
