package com.yang.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Category;
import com.yang.blog.dto.BaseQueryParam;
import com.yang.blog.util.ResponseData;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/15 16:36
 * @description：
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 分页查询
     *
     * @param queryCondition
     * @return
     */
    Map<String, Object> queryPage(BaseQueryParam queryCondition);

    /**
     * 新增
     *
     * @param category
     * @param bindingResult
     * @return
     */
    ResponseData<Object> add(Category category, BindingResult bindingResult);

    /**
     * id查找
     *
     * @param id
     * @return
     */
    ResponseData<Map<String, Object>> find(Long id);

    /**
     * 修改
     *
     * @param category
     * @param bindingResult
     * @return
     */
    ResponseData<Object> update1(Category category, BindingResult bindingResult);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    ResponseData<Object> del(List<Long> ids);
}
