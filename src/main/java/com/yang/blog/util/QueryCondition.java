package com.yang.blog.util;

import lombok.Data;

@Data
public class QueryCondition {


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
      /*  if (null == page) {
            page = 1L;
        }

        if (null == limit) {
            limit = 10L;
        }*/
        return "limit " + (page - 1) * limit + "," + limit;
    }

}
