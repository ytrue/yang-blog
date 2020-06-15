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
 * @description：判断验证码是否正确
 */
@Documented
@Retention(RUNTIME)
@Target({PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = CaptchaValidator.class)
public @interface Captcha {
    //指定多个时使用
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Captcha[] value();
    }


    //提示内容
    String message() default "验证码不正确！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
