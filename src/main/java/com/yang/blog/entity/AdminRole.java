package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;


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
    @TableField("create_time")
    private Long createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Long updateTime;

}
