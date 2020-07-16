package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author yangyi
 * @date 2020/6/17 14:46
 * @description：友情链接实体类
 */
@TableName("blog_link")
@Data
public class Link extends BaseEntity {


    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型
     */
    @TableField("type")
    @NotNull(message = "类型不得为空！")
    private Integer type;

    /**
     * 名称
     */
    @TableField("name")
    @NotBlank(message = "链接名称不得为空！")
    private String name;

    /**
     * url
     */
    @NotBlank(message = "链接url不得为空！")
    @TableField("url")
    private String url;

    /**
     * 说明
     */
    @NotBlank(message = "网站说明不得为空！")
    @TableField("description")
    private String description;

    /**
     * 排序
     */
    @TableField("sort")
    @NotNull(message = "排序不得为空！")
    private Integer sort;
}
