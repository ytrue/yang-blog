package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@TableName("blog_article")
public class Article {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不得为空！")
    @TableField("title")
    private String title;

    /**
     * 图片地址
     */
    @NotBlank(message = "封面图不得为空！")
    @TableField("image")
    private String image;

    /**
     * 栏目id
     */
    @NotNull(message = "所属栏目不得为空！")
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 标签
     */
    @NotBlank(message = "标签不得为空！")
    @TableField("tag")
    private String tag;

    /**
     * 内容
     */
    @NotBlank(message = "内容不得为空！")
    @TableField("content")
    private String content;

    /**
     * 状态
     */
    @NotNull(message = "status不得为空！")
    @TableField("status")
    private Integer status;

    /**
     * 是否评论
     */
    @NotNull(message = "enableComment不得为空！")
    @TableField("enable_comment")
    private Integer enableComment;

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
