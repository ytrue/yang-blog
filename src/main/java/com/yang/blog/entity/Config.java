package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author yangyi
 * @date 2020/6/17 16:20
 * @description：
 */
@Data
@TableName("sys_config")
public class Config {

    @TableField("config_name")
    @NotBlank(message = "configName不得为空！")
    private String configName;

    @TableField("config_value")
    @NotBlank(message = "configValue不得为空！")
    private String configValue;


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
