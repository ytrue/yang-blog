package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;

import java.util.Date;


/**
 * <p>  系统管理 - 用户角色关联表  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@Data

@TableName("sys_user_role")
public class AdminRole {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */

    @TableField("user_id")
    private Integer userId;
    /**
     * 角色ID
     */

    @TableField("role_id")
    private Integer roleId;

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
