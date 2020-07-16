package com.yang.blog.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author：yangyi
 * @date：2020/4/17 10:11
 * @description：mybatisplus配置类
 */

@Configuration
public class MybatisPlusConfiguration {

    /**
     * 配置分页
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }


    /**
     * 自定义mybatisPlus填充类
     */
    @Component
    static class MyMetaObjectHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            setFieldValByName("updateTime", new Date(), metaObject);
            setFieldValByName("createTime", new Date(), metaObject);

        }

        @Override
        public void updateFill(MetaObject metaObject) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }
}
