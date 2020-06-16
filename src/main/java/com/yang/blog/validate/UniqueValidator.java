package com.yang.blog.validate;

import com.yang.blog.exeption.CustomVerificationException;
import com.yang.blog.mapper.CommonMapper;
import com.yang.blog.util.ReflectionUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author：yangyi
 * @date：2020/5/16 12:03
 * @description：验证唯一性实现类
 */
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    private CommonMapper commonMapper;

    private String id;

    private String table;

    private String field;

    private String message;

    /**
     * 初始化
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(Unique constraintAnnotation) {
        table = constraintAnnotation.table();
        field = constraintAnnotation.field();
        id = constraintAnnotation.id();
        message = constraintAnnotation.message();
    }


    @SneakyThrows
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        //反射获得字段的值
        Object reflectionField = ReflectionUtils.getValueOfGetIncludeObjectFeild(o, field);
        Object reflectionId = ReflectionUtils.getValueOfGetIncludeObjectFeild(o, id);
        //判断id是为空，为空的话就是新增，不为空就是新增
        if (null == reflectionId) {
            int exist = commonMapper.exist(table, field, (String) reflectionField);
            if (exist > 0) {
                throw new CustomVerificationException(message);
            } else {
                return true;
            }
        } else {
            int i = commonMapper.existNotId(table, field, (String) reflectionField, id, (Integer) reflectionId);
            if (i > 0) {
                throw new CustomVerificationException(message);
            } else {
                return true;
            }
        }

    }

}
