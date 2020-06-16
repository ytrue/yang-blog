package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.Category;
import com.yang.blog.mapper.CategoryMapper;
import com.yang.blog.service.CategoryService;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/15 16:36
 * @description：
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public Map<String, Object> queryPage(QueryCondition queryCondition) {
        return null;
    }

    @Override
    public ResponseData<Object> add(Category category, BindingResult bindingResult) {
        return null;
    }

    @Override
    public ResponseData<Map<String, Object>> find(Long id) {
        return null;
    }

    @Override
    public ResponseData<Object> update1(Category category, BindingResult bindingResult) {
        return null;
    }

    @Override
    public ResponseData<Object> del(List<Long> ids) {
        return null;
    }
}
