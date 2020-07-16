package com.yang.blog.util;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> 判断是否是Ajax请求</p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 15:42
 */
public class IsAjaxUtils {

    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
