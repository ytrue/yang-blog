package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import com.yang.blog.validate.IsEmptyPwd;
import com.yang.blog.validate.Scene;
import com.yang.blog.validate.Unique;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;


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
@TableName("sys_admin")
public class Admin {

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
    @NotBlank(message = "账户不得为空！", groups = {Scene.Add.class, Scene.Update.class})
    @Unique(table = "sys_admin", field = "username", message = "此账户已存在！", groups = {Scene.Add.class})
    private String username;
    /**
     * 登录密码
     */

    @TableField("password")
    @NotBlank(message = "密码不得为空！", groups = {Scene.Add.class})
    @Length(min = 6, message = "密码长度至少是6位！", groups = {Scene.Add.class})
    @IsEmptyPwd(message = "密码长度至少是6位，同时有数字和字母！", groups = {Scene.Update.class})
    private String password;
    /**
     * 昵称
     */

    @TableField("nick_name")
    @NotBlank(message = "昵称不得为空！", groups = {Scene.Add.class, Scene.Update.class})
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
