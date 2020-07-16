package com.yang.blog.dto;

import lombok.Data;

/**
 * @author：yangyi
 * @date：2020/4/16 18:03
 * @description：spring admin查询参数
 */
@Data
public class AdminQueryParam extends BaseQueryParam {

    private String username;

    private String nickName;
}
