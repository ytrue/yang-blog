package com.yang.blog.security.login;


import com.google.gson.Gson;
import com.yang.blog.entity.User;
import com.yang.blog.security.dto.SecurityUser;
import com.yang.blog.util.ResponseData;
import com.yang.blog.util.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * <p> 认证成功处理 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 15:31
 */
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        User user = new User();
        SecurityUser securityUser = ((SecurityUser) auth.getPrincipal());
        user.setToken(securityUser.getCurrentUserInfo().getToken());
        //返回数据
        ResponseUtils.out(response,ResponseData.success(user));
    }
}
