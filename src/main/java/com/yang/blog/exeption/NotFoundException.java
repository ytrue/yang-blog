package com.yang.blog.exeption;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 捕获404
 */
@Controller
public class NotFoundException implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public Object error(HttpServletRequest request, HttpServletResponse response) {
        // //获取异常返回
        //获得状态码
        /**
         * javax.servlet.error.status_code             类型为Integer        错误状态代码
         * javax.servlet.error.exception_type          类型为Class          异常的类型
         * javax.servlet.error.message                 类型为String         异常的信息
         * javax.servlet.error.exception               类型为Throwable      异常类
         * javax.servlet.error.request_uri             类型为String         异常出现的页面
         * javax.servlet.error.servlet_name            类型为String         异常出现的servlet名
         */
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        if (statusCode == 500){
            message = "服务器内部错误！";
        }
        Map<String, Object> body = new HashMap<>();
        body.put("code", statusCode);
        body.put("msg", message);
        body.put("data", null);
        return body;
    }
}
