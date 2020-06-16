package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;


import lombok.Data;

import java.util.Date;


/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：角色-权限关联实体类
 */
@Data

@TableName("sys_role_permission")
public class RoleMenu {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色ID
     */

    @TableField("role_id")
    private Integer roleId;
    /**
     * 权限ID
     */

    @TableField("permission_id")
    private Integer permissionId;

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
