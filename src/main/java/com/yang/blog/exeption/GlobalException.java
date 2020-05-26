package com.yang.blog.exeption;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：yangyi
 * @date：2020/5/17 7:24
 * @description：
 */
//@ControllerAdvice
public class GlobalException {

//    @ExceptionHandler({Exception.class, RuntimeException.class})
//    public void handleArithmeticException(
//            Exception exception,
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//
//
//
//        System.out.println("触发全局异常！！！！！"+exception.getMessage());
//    }

}
