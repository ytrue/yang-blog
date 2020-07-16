package com.yang.blog.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * 使用response输出JSON
 *
 * @author：yangyi
 * @date：2020/5/17 8:09
 * @description：
 */
public class ResponseUtil {

    public static void out(ServletResponse response, ResponseData<Object> result) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(result));
        } catch (Exception e) {
            System.out.println(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
