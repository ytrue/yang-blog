package com.yang.blog.entity;

import com.yang.blog.validate.Captcha;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
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
