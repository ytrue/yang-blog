package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yang.blog.validate.IsEmptyPwd;
import com.yang.blog.validate.Unique;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：管理员实体类
 */
@Data
@TableName("sys_admin")
@Unique(table = "sys_admin", field = "username", message = "此账号已存在！")
@IsEmptyPwd(message = "密码长度至少是6位，同时有数字和字母！")
public class Admin extends Test {

    private static final long serialVersionUID = 1L;

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
