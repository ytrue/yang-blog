package com.yang.blog.exeption;

/**
 * @author yangyi
 * @date 2020/6/16 15:59
 * @description：
 */
public class UniqueException extends RuntimeException {
    public UniqueException() {
        super();
    }

    public UniqueException(String message) {
        super(message);
    }
}
