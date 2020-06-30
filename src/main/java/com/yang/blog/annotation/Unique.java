package com.yang.blog.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author：yangyi
 * @date：2020/5/16 12:01
 * @description：唯一性
 */
// 元注解: 给其他普通的标签进行解释说明 【@Retention、@Documented、@Target、@Inherited、@Repeatable】
@Documented
/**
 * 指明生命周期:
 *      RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
 *      RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
 *      RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * 指定注解运用的地方:
 *      ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 *      ElementType.CONSTRUCTOR 可以给构造方法进行注解
 *      ElementType.FIELD 可以给属性进行注解
 *      ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 *      ElementType.METHOD 可以给方法进行注解
 *      ElementType.PACKAGE 可以给一个包进行注解
 *      ElementType.PARAMETER 可以给一个方法内的参数进行注解
 *      ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 */
@Target(ElementType.TYPE)
@Constraint(validatedBy = UniqueValidator.class)
public @interface Unique {

    /**
     * 实体类id字段 - 默认为id （该值可无）
     * @return
     */
    String id() default "id";

    /**
     * 表
     *
     * @return
     */
    String table();


    /**
     * 字段
     *
     * @return
     */
    String field();


    //提示内容
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
