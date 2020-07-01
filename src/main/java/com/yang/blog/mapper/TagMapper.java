package com.yang.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.blog.entity.Tag;
import org.apache.ibatis.annotations.Param;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：标签Mapper接口
 */
public interface TagMapper extends BaseMapper<Tag> {

    void setIncByNumber(@Param("name") String name);

    void setDecByNumber(@Param("name") String name);
}
