package com.yang.blog.util;


public class QueryCondition {


    /**
     * 页码
     */
    private Long page;

    /**
     * 每页大小
     */
    private Long limit;

    /**
     * 排序的字段
     */
    private String sidx;

    /**
     * 排序的顺序
     */
    private String order;

    /**
     * 系统默认排序
     */
    private String asc = "desc";

    /**
     * 搜索字段
     */
    private String key;

    public QueryCondition() {
    }

    public QueryCondition(Long page, Long limit, String sidx, String order, String asc, String key) {
        this.page = page;
        this.limit = limit;
        this.sidx = sidx;
        this.order = order;
        this.asc = asc;
        this.key = key;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getAsc() {
        return asc;
    }

    public void setAsc(String asc) {
        this.asc = asc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "QueryCondition{" +
                "page=" + page +
                ", limit=" + limit +
                ", sidx='" + sidx + '\'' +
                ", order='" + order + '\'' +
                ", asc='" + asc + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
