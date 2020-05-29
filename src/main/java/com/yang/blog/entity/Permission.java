package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.TableName;


import com.yang.blog.validate.Scene;
import com.yang.blog.validate.Unique;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>  系统管理-权限权限表  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */

@TableName("sys_permission")
@Data
public class Permission {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * url
     */

    @TableField("url")
    @Unique(table = "sys_permission", field = "url", message = "菜单规则已存在！", groups = {Scene.Add.class})
    @NotBlank(message = "菜单规则不能为空")
    @Length(max = 125, message = "菜单规则不能超过125个字符")
    private String url;

    /**
     * 名称
     */
    @TableField("name")
    @NotBlank(message = "菜单名称不能为空")
    @Length(max = 20, message = "菜单名称不能超过20个字符")
    private String name;

    /**
     * 父id
     */
    @TableField("pid")
    @NotBlank(message = "pid不能为空")
    private Integer pid;

    @TableField("icon")
    private String icon;

    /**
     * 是否菜单
     */
    @TableField("ismenu")
    @NotBlank(message = "isMenu不能为空")
    private Integer ismenu;


    @TableField(exist = false) //表示该属性不为数据库表字段，但又是必须使用的。
    private List<Permission> childrenList;

    @TableField(exist = false) //表示该属性不为数据库表字段，但又是必须使用的。
    private Integer level;

    /**
     * 创建时间
     */

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;
}
