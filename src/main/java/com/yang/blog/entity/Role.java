package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yang.blog.validate.Unique;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：角色实体类
 */
@Data
@TableName("sys_role")
@Unique(table = "sys_role", field = "code", message = "角色编码已存在！")
public class Role {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色编码
     */
    @TableField("code")
    @NotBlank(message = "角色编码不能为空")
    @Length(max = 20, message = "角色编码不能超过20个字符")
    private String code;
    /**
     * 角色名称
     */
    @TableField("name")
    @NotBlank(message = "角色名称不能为空")
    @Length(max = 20, message = "角色编码不能超过20个字符")
    private String name;


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
