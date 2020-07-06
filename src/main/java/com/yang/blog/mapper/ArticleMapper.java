package com.yang.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：文章Mapper接口
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Map<String, Object>> articleWithCategory(
            @Param("start") Long start,
            @Param("end") Long end
    );
}
