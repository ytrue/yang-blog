package com.yang.blog.validate;

import com.yang.blog.exeption.UniqueException;
import com.yang.blog.mapper.CommonMapper;
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
    }


    @SneakyThrows
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {


        throw new UniqueException("字段重复！！！");
    }


}
