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
     * 字段重复异常
     *
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({UniqueException.class})
    public Object handleArithmeticException(
            UniqueException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        System.out.println(exception.getMessage());
        return null;
    }
}
