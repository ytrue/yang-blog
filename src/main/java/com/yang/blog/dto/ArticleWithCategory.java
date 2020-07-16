package com.yang.blog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author：yangyi
 * @date：2020/4/16 18:03
 * @description：spring 文章关联标题
 */
@Data
public class ArticleWithCategory {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片地址
     */
    private String image;

    /**
     * 栏目id
     */
    private Integer categoryId;

    /**
     * 标签
     */
    private String tag;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否评论
     */
    private Integer enableComment;

    /**
     * 栏目名称
     */
    private String categoryName;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 阅读量
     */
    private Long views;
}
