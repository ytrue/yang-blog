package com.yang.blog.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConditionDto implements Serializable {

    private static final long serialVersionUID = -5099378457111419832L;
    /**
     * 数据库字段名
     */
    private String column;
    /**
     * 字段值
     */
    private String value;
    /**
     * 连接类型，如llike,equals,gt,ge,lt,le
     */
    private String type;
}
