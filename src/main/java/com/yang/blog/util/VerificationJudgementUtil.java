package com.yang.blog.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;

/**
 * <p> 错误遍历</p>
 *
 * @author：yangyi
 * @date：2020/4/19 17:06
 * @description：错误遍历
 */
public class VerificationJudgementUtil {

    public static ArrayList<String> hasErrror(BindingResult bindingResult) {

        ArrayList<String> errorList = new ArrayList<>();
        // 判断是否有异常
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorList.add(fieldError.getDefaultMessage());
            }
        }
        return errorList;
    }
}
