package com.yang.blog.security;

import java.util.Arrays;
import java.util.List;

/**
 * @author：yangyi
 * @date：2020/5/17 10:25
 * @description：
 */
public class UrlAuthConfig {
    public static List<String> permitAll() {
        return Arrays.asList("/admin/login", "/admin/captcha");
    }

    public static List<String> authenticated() {
        return null;
    }
}
