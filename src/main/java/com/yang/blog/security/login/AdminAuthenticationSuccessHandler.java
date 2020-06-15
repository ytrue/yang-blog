package com.yang.blog.security.login;


import com.yang.blog.util.ResponseData;
import com.yang.blog.util.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        //返回数据
        //清除一下session
        HttpSession session = request.getSession();
        session.removeAttribute("RANDOMREDISKEY");

        ResponseUtils.out(response,ResponseData.success());
    }
}
