package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：权限实体类
 */
@TableName("sys_permission")
@Data
public class Permission extends BaseEntity {

    /**
     * 主键
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * url
     */

    @TableField("url")
    //@Unique(table = "sys_permission", field = "url", message = "菜单规则已存在！", groups = {Scene.Add.class})
    @NotBlank(message = "菜单规则不能为空")
    @Length(max = 125, message = "菜单规则不能超过125个字符")
    private String url;

    /**
     * 名称
     */
    @TableField("name")
    @NotBlank(message = "菜单名称不能为空")
    @Length(max = 20, message = "菜单名称不能超过20个字符")
    private String name;

    /**
     * 父id
     */
    @TableField("pid")
    @NotBlank(message = "pid不能为空")
    private Integer pid;

    @TableField("icon")
    private String icon;

    /**
     * 是否菜单
     */
    @TableField("ismenu")
    @NotBlank(message = "isMenu不能为空")
    private Integer ismenu;


    @TableField(exist = false) //表示该属性不为数据库表字段，但又是必须使用的。
    private List<Permission> childrenList;

    @TableField(exist = false) //表示该属性不为数据库表字段，但又是必须使用的。
    private Integer level;

}
