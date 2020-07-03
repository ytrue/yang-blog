package com.yang.blog.validator;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @author：yangyi
 * @date：2020/5/16 13:30
 * @description：判断验证码是否正确实现类
 */
public class CaptchaValidator implements ConstraintValidator<Captcha, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.isEmpty(s)) {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String requestCaptcha = (String) request.getSession().getAttribute("RANDOMREDISKEY");
            return requestCaptcha.equalsIgnoreCase(s);
        }
        return true;
    }
}
