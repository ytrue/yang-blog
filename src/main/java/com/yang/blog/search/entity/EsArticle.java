package com.yang.blog.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "article", type = "article", createIndex = true)
public class EsArticle {

    /**
     * 标题，图片，发布时间，栏目和栏目id
     */

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Object)
    private EsCategory category;
}
