package com.yang.blog.security.login;

import com.yang.blog.util.ResponseData;
import com.yang.blog.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 认证失败处理 - 前后端分离情况下返回json数据格式 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 15:33
 * <p>
 * 没有权限时触发这个类
 */
@Slf4j
@Component
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        ResponseData<Object> json;

        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            json = jsonData(6, e.getMessage());
        } else if (e instanceof LockedException) {
            json = jsonData(1, "账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            json = jsonData(2, "证书过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            json = jsonData(3, "账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            json = jsonData(4, "账户被禁用，请联系管理员!");
        } else {
            json = jsonData(5, "登录失败!");
        }
        //返回数据
        ResponseUtil.out(response, json);
    }
    private ResponseData<Object> jsonData(int code, String message) {
        return ResponseData.fail(code, message);
    }
}
