package com.yang.blog.entity;

import com.yang.blog.validate.Captcha;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：登录实体类
 */
public class AdminLogin {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @Captcha
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
