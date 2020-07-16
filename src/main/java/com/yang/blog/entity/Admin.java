package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yang.blog.validator.IsEmptyPwd;
import com.yang.blog.validator.Unique;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：管理员实体类
 */
@Data
@TableName("sys_admin")
@Unique(table = "sys_admin", field = "username", message = "此账号已存在！")
@IsEmptyPwd(message = "密码长度至少是6位，同时有数字和字母！")
public class Admin extends BaseEntity {

    /**
     * 主键ID
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    @TableField("username")
    @NotBlank(message = "账户不得为空！")
    private String username;
    /**
     * 登录密码
     */
    @TableField("password")
    private String password;
    /**
     * 昵称
     */

    @TableField("nick_name")
    @NotBlank(message = "昵称不得为空！")
    private String nickName;
    /**
     * 盐值
     */

    @TableField("salt")
    private String salt;
    /**
     * token
     */

    @TableField("token")
    private String token;


}
