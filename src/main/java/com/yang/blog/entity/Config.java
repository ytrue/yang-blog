package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yangyi
 * @date 2020/6/17 16:20
 * @description：
 */
@Data
@TableName("sys_config")
public class Config extends BaseEntity {

    @TableField("config_name")
    @NotBlank(message = "configName不得为空！")
    private String configName;

    @TableField("config_value")
    @NotBlank(message = "configValue不得为空！")
    private String configValue;
}
