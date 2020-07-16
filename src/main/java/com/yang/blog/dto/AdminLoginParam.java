package com.yang.blog.dto;

import com.yang.blog.validator.Captcha;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：登录参数
 */
@Data
public class AdminLoginParam {
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
