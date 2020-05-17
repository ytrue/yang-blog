package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;


/**
 * <p>  系统管理-用户基础信息表 </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Data

@TableName("sys_user")
public class User {

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
