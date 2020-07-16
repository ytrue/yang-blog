package com.yang.blog.dto;

import lombok.Data;

/**
 * @author：yangyi
 * @date：2020/4/16 18:03
 * @description：spring 基础查询参数
 */
@Data
public class BaseQueryParam {

    /**
     * 页码
     */
    private Long page = 1L;

    /**
     * 每页大小
     */
    private Long limit = 10L;

    /**
     * 返回拼接的sql语句
     *
     * @return String
     */
    public String getPageSql() {
        return "limit " + (page - 1) * limit + "," + limit;
    }

}
