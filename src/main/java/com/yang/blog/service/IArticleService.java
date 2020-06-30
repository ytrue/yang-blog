package com.yang.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Article;
import com.yang.blog.util.ResponseData;
import org.springframework.validation.BindingResult;

public interface IArticleService extends IService<Article> {

    /**
     * 新增
     *
     * @param article
     * @param bindingResult
     * @return
     */
    ResponseData<Object> add(Article article, BindingResult bindingResult);
}
