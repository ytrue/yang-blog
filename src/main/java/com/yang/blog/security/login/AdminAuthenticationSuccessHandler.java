package com.yang.blog.security.login;


import com.yang.blog.util.ResponseData;
import com.yang.blog.util.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) {
        //返回数据
        ResponseUtils.out(response,ResponseData.success());
    }
}
