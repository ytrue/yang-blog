package com.yang.blog.exeption;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangyi
 * @date 2020/6/16 15:52
 * @description：捕获特定的异常
 */
@ControllerAdvice
public class OtherException {

    /**
     * 捕获自定义验证的异常
     *
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({CustomVerificationException.class})
    public Object handleArithmeticException(
            CustomVerificationException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        System.out.println(exception.getMessage());
        return null;
    }
}
