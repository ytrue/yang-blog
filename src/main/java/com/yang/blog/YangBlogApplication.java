package com.yang.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author：yangyi
 * @date：2020/5/16 8:18
 * @description：启动类
 */
@SpringBootApplication
@MapperScan("com.yang.blog.mapper")
public class YangBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangBlogApplication.class, args);
    }
}
