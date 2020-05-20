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
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        Map<String, Object> body = new HashMap<>();
        body.put("error", "not found");
        body.put("statusCode", statusCode);
        return body;
    }
}
