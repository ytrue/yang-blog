package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.TableName;


import lombok.Data;

import java.io.Serializable;
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
    private String url;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 父id
     */
    @TableField("pid")
    private Integer pid;

    @TableField("icon")
    private String icon;

    /**
     * 是否菜单
     */
    @TableField("ismenu")
    private Integer ismenu;

    @TableField(exist = false) //表示该属性不为数据库表字段，但又是必须使用的。
    private List<Permission> childrenList;


}
