package com.yang.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author：yangyi
 * @date：2020/5/16 8:18
 * @description：启动类
 */
@SpringBootApplication
@MapperScan("com.yang.blog.mapper")
@EnableTransactionManagement
//@EnableRedisHttpSession(redisNamespace = "login") // 自动化配置 Spring Session 使用 Redis 作为数据源
public class YangBlogApplication {

    public static void main(String[] args) {
        // 设置环境变量，解决Es的netty与Netty服务本身不兼容问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(YangBlogApplication.class, args);
    }
}
