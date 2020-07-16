package com.yang.blog.security.login;

import com.yang.blog.util.IsAjaxUtils;
import com.yang.blog.util.ResponseData;
import com.yang.blog.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
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
@Slf4j
public class AdminAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        if (IsAjaxUtils.isAjax(request)) {
            //返回错误提示
            log.info("AdminAuthenticationEntryPoint触发：返回json！");
            ResponseUtil.out(response, ResponseData.fail(403, e.getMessage()));
        } else {
            //挑战到登录页面
            log.info("AdminAuthenticationEntryPoint触发：跳转登录页！");
            response.sendRedirect("/admin/login");
        }
    }
}
