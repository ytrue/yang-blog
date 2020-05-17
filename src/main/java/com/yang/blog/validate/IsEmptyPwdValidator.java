package com.yang.blog.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author：yangyi
 * @date：2020/5/16 13:30
 * @description：密码判断是否为空实现类
 */
public class IsEmptyPwdValidator implements ConstraintValidator<IsEmptyPwd, String> {

    private boolean flag;


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
        flag = constraintAnnotation.flag();
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if (flag) {
            return true;
        } else {
            if (string == null || string.isEmpty()) {
                return true;
            } else {
                return getBoolMatcher(string);
            }
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
