package com.yang.blog.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "mall", type = "product", createIndex = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Long id;

    @Field
    private String name;

    @Field
    private String desc;

    @Field(type = FieldType.Double)
    private Double price;

    @Field
    private String producer;

    @Field
    private List<String> tags;

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
