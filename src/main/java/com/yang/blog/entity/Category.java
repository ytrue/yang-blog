package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yang.blog.validator.Unique;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：文章栏目实体类
 */
@TableName("blog_category")
@Unique(table = "blog_category", field = "name", message = "此栏目名称已存在！")
@Data
public class Category extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    @NotBlank(message = "栏目名称不得为空！")
    private String name;
}
