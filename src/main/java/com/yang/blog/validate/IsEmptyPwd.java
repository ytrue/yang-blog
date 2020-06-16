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
 * @date：2020/5/16 13:29
 * @description：判断密码是否为空
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = IsEmptyPwdValidator.class)
public @interface IsEmptyPwd {

    //指定多个时使用
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        IsEmptyPwd[] value();
    }

    String filed() default "password";

    String id() default "id";

    //提示内容
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
