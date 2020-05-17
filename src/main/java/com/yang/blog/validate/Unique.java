package com.yang.blog.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author：yangyi
 * @date：2020/5/16 12:01
 * @description：唯一性
 */
@Documented
@Retention(RUNTIME)
@Target({PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = UniqueValidator.class)

public @interface Unique {

    //指定多个时使用
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Unique[] value();
    }

    /**
     * 表
     * @return
     */
    String table();

    /**
     * 字段
     * @return
     */
    String field();


    //提示内容
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
