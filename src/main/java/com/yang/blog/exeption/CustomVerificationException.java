package com.yang.blog.exeption;

/**
 * @author yangyi
 * @date 2020/6/16 15:59
 * @description：
 */
public class CustomVerificationException extends RuntimeException {
    public CustomVerificationException() {
        super();
    }

    public CustomVerificationException(String message) {
        super(message);
    }
}
