package com.yang.blog.controller.backend;

import com.yang.blog.util.VerifyUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：登录控制器
 */
@Controller
@RequestMapping("admin")
public class LoginController {

    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping("login")
    public String login() {
        //判断一下是否已登录
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            return "backend/logged";
        }
        return "backend/login";
    }

    /**
     * 验证码图片
     */
    @RequestMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        VerifyUtil randomValidateCode = new VerifyUtil();
        randomValidateCode.getRandcode(request, response);//输出验证码图片

       // System.out.println((String)request.getSession().getAttribute("RANDOMREDISKEY"));
    }

}
