package com.yang.blog.exeption;

import com.yang.blog.util.IsAjaxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 捕获404
 */
@Controller
@Slf4j
public class GlobalException implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    //@ExceptionHandler({Exception.class, RuntimeException.class})
    public Object error(HttpServletRequest request, HttpServletResponse response, Exception exception, WebRequest req) {

        //获得状态码
        /**
         * javax.servlet.error.status_code             类型为Integer        错误状态代码
         * javax.servlet.error.exception_type          类型为Class          异常的类型
         * javax.servlet.error.message                 类型为String         异常的信息
         * javax.servlet.error.exception               类型为Throwable      异常类
         * javax.servlet.error.request_uri             类型为String         异常出现的页面
         * javax.servlet.error.servlet_name            类型为String         异常出现的servlet名
         *
         * Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
         * String message = (String) request.getAttribute("javax.servlet.error.message");
         */

        //{timestamp=Fri May 29 09:29:14 CST 2020, status=500, error=Internal Server Error, message=/ by zero, path=/admin/auth/menu/all}
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(req, false);
        log.error(errorAttributes.toString());
        if (IsAjaxUtils.isAjax(request)) {
            Map<String, Object> body = new HashMap<>();
            body.put("code", errorAttributes.get("status"));
            body.put("msg", errorAttributes.get("error") + "：" + errorAttributes.get("message"));
            body.put("data", null);
            log.error("NotFoundException触发啦，返回的是json数据格式！！！");
            return body;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error"); //这里需要在templates文件夹下新建一个error.html文件用作错误页面
            modelAndView.addObject("code", errorAttributes.get("status"));
            modelAndView.addObject("error", errorAttributes.get("error"));
            modelAndView.addObject("message", errorAttributes.get("message"));
            log.error("NotFoundException触发啦，返回的是html页面！！！");
            return modelAndView;
        }
    }


}
