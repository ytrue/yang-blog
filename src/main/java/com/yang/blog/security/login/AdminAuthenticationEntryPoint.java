package com.yang.blog.security.login;

import com.yang.blog.util.ResponseData;
import com.yang.blog.util.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录访问权限控制
 *
 * @author：yangyi
 * @date：2020/5/16 17:58
 * @description：这里是认证权限入口 -> 即在未登录的情况下访问所有接口都会拦截到此（除了放行忽略接口）
 * <p>
 * 没有登录触发这个类
 */

@Component
public class AdminAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtils.out(httpServletResponse, ResponseData.fail("未登录！"));
    }
}
