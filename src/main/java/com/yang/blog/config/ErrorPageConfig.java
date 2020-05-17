package com.yang.blog.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author：yangyi
 * @date：2020/4/3 15:43
 * @description：错误定制页面
 */
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
//        ErrorPage[] errorPages = new ErrorPage[1];
//        errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
//        //errorPages[1] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
//        registry.addErrorPages(errorPages);
    }
}
