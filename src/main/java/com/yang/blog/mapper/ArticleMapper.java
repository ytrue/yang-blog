package com.yang.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.blog.dto.ArticleWithCategory;
import com.yang.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：文章Mapper接口
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleWithCategory> articleWithCategory(
            @Param("start") Long start,
            @Param("end") Long end
    );
}
