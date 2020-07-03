package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yang.blog.validator.Unique;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：文章栏目实体类
 */
@TableName("blog_category")
@Unique(table = "blog_category", field = "name", message = "此栏目名称已存在！")
@Data
public class Category {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    @NotBlank(message = "栏目名称不得为空！")
    private String name;


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
