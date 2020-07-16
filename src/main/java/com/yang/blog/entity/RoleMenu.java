package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：角色-权限关联实体类
 */
@Data

@TableName("sys_role_permission")
public class RoleMenu extends BaseEntity {
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
