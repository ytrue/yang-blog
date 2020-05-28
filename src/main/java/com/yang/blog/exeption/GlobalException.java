package com.yang.blog.exeption;

import com.google.gson.Gson;
import com.yang.blog.util.IsAjaxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * @author：yangyi
 * @date：2020/5/17 7:24
 * @description：
 */
@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public Object handleArithmeticException(
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) {


        if (IsAjaxUtils.isAjax(request)) {
            Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
            String message = (String) request.getAttribute("javax.servlet.error.message");
            if (statusCode == 500) {
                message = exception.getMessage();
            }
            Map<String, Object> body = new HashMap<>();
            body.put("code", statusCode);
            body.put("msg", message);
            body.put("data", null);

            response.setContentType("application/json; charset=utf-8");
            PrintWriter out;
            Gson gson = new Gson();

            try {
                out = response.getWriter();
                out.print(gson.toJson(body));
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.error("全局错误触发啦，返回的是json数据格式！！！");
            return body;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error"); //这里需要在templates文件夹下新建一个error.html文件用作错误页面
            log.error("全局错误触发啦，返回的是html页面！！！");
            return modelAndView;
        }
    }
}
