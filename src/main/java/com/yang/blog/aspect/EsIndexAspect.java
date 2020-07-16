package com.yang.blog.aspect;

import com.yang.blog.search.entity.EsArticle;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

/**
 * @author：yangyi
 * @date：2020/4/16 18:03
 * @description：spring 判断elasticsearch index 是否存在
 */
@Aspect
@Component
@Slf4j
public class EsIndexAspect {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 定义切入点，切入点为com.example.demo.aop.AopController中的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * com.yang.blog.controller.backend.ArticleController.*(..)))")
    public void EsIndexAspect() {

    }

    /**
     * @description 在连接点执行之前执行的通知
     */
    @Before("EsIndexAspect()")
    public void doBeforeGame() {
        /**
         * es索引不存在就创建
         */
        if (!elasticsearchTemplate.indexExists(EsArticle.class)) {
            elasticsearchTemplate.createIndex(EsArticle.class);
            elasticsearchTemplate.putMapping(EsArticle.class);
            log.info("elasticsearch索引创建成功！");
        }
    }

}
