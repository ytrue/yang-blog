package com.yang.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Article;
import com.yang.blog.dto.QueryParam;
import com.yang.blog.util.ResponseData;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface IArticleService extends IService<Article> {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Map<String, Object> queryPage(QueryParam params);

    /**
     * 新增
     *
     * @param article
     * @param bindingResult
     * @return
     */
    ResponseData<Object> add(Article article, BindingResult bindingResult);


    /**
     * 修改
     *
     * @param article
     * @param bindingResult
     * @return
     */
    ResponseData<Object> update1(Article article, BindingResult bindingResult);


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    ResponseData<Object> del(List<Long> ids);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    ResponseData<Article> findById(Long id);

}
