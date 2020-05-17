package com.yang.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author：yangyi
 * @date：2020/5/16 13:20
 * @description：公共mapper
 */
public interface CommonMapper extends BaseMapper {
    int exist(
            @Param("table") String table,
            @Param("field") String field,
            @Param("val") String val);
}
