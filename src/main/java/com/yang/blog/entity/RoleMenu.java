package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import lombok.Data;



/**
 * <p>  系统管理 - 角色-权限关联表  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
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


}
