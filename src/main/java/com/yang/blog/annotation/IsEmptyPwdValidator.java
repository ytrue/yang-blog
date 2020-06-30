package com.yang.blog.annotation;

import com.yang.blog.exeption.CustomVerificationException;
import com.yang.blog.util.ReflectionUtils;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author：yangyi
 * @date：2020/5/16 13:30
 * @description：密码判断是否为空实现类
 */
public class IsEmptyPwdValidator implements ConstraintValidator<IsEmptyPwd, Object> {

    private String message;

    private String id;

    private String field;

    private static final String check =
            "^[A-Za-z].*[0-9]|[0-9].*[A-Za-z]{6,16}$";

    static final Pattern regex = Pattern.compile(check);

    /**
     * 初始化
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IsEmptyPwd constraintAnnotation) {
        message = constraintAnnotation.message();
        id = constraintAnnotation.id();
        field = constraintAnnotation.filed();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {

        Integer reflectionId = (Integer) ReflectionUtils.getValueOfGetIncludeObjectFeild(object, "id");

        String reflectionPassword = (String) ReflectionUtils.getValueOfGetIncludeObjectFeild(object, field);

        if (null == reflectionId) {
            //等于空就是新增，新增要验证一下
            if (StringUtils.isEmpty(reflectionPassword)) {
                throw new CustomVerificationException(message);
            }
        } else {
            if (StringUtils.isEmpty(reflectionPassword)) {
                return true;
            }
        }

        if (getBoolMatcher(reflectionPassword)) {
            return true;
        } else {
            throw new CustomVerificationException(message);
        }
    }

    /**
     * 正则表达式匹配。
     *
     * @param str 需要匹配的字符串
     * @return 如果匹配成功返回true，否则返回false。
     */
    private boolean getBoolMatcher(String str) {
        boolean flag = false;
        Matcher matcher = regex.matcher(str);
        flag = matcher.matches();
        return flag;
    }
}
