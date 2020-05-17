package com.yang.blog.validate;

import com.yang.blog.mapper.CommonMapper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author：yangyi
 * @date：2020/5/16 12:03
 * @description：验证唯一性实现类
 */
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private CommonMapper commonMapper;

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
    }

    /**
     * 实现验证
     *
     * @param string
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        int count = commonMapper.exist(table, field, string);
        return count <= 0;
    }


}
