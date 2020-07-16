package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：管理员关联角色实体类
 */
@Data
@TableName("sys_user_role")
public class AdminRole extends BaseEntity {

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

}
