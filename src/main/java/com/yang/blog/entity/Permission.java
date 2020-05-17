package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.TableName;


import lombok.Data;

import java.io.Serializable;

/**
 * <p>  系统管理-权限权限表  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
@Data

@TableName("sys_permission")
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


}
