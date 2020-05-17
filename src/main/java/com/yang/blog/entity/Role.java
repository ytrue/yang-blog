package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>  系统管理-角色表  </p>
 *
 * @author: zhengqing
 * @date: 2019-08-20
 */
@Data
@TableName("sys_role")
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
    private String name;
    /**
     * 角色描述
     */
    @TableField("remarks")
    private String remarks;


}
