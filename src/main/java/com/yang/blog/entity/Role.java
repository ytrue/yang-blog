package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yang.blog.validator.Unique;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：角色实体类
 */
@Data
@TableName("sys_role")
@Unique(table = "sys_role", field = "code", message = "角色编码已存在！")
public class Role extends BaseEntity {

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
}
