package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author yangyi
 * @date 2020/6/17 14:46
 * @description：友情链接实体类
 */
@TableName("blog_link")
@Data
public class Link {

    private static final long serialVersionUID = 1L;

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

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
