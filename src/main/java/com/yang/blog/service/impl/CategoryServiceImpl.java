package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.dto.QueryParam;
import com.yang.blog.entity.Category;
import com.yang.blog.mapper.CategoryMapper;
import com.yang.blog.service.ICategoryService;
import com.yang.blog.util.ResponseData;
import com.yang.blog.util.SearchUtil;
import com.yang.blog.util.VerificationJudgementUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/15 16:36
 * @description：
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> queryPage(QueryParam params) {
        QueryWrapper<Category> queryWrapper = SearchUtil.parseWhereSql(new QueryWrapper<>(), params.getCondition());
        int count = count(queryWrapper);
        List<Map<String, Object>> rows = listMaps(
                queryWrapper
                        .select("id", "name", "create_time")
                        .orderByDesc("id")
                        .last(params.getPageSql())
        );
        return ResponseData.list(count, rows);
    }

    /**
     * 新增
     *
     * @param category
     * @param bindingResult
     * @return
     */
    @Override
    public ResponseData<Object> add(Category category, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgementUtil.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }
        try {
            save(category);
            return ResponseData.success(null);
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }

    /**
     * id查找
     *
     * @param id
     * @return
     */
    @Override
    public ResponseData<Map<String, Object>> find(Long id) {
        Map<String, Object> map = getMap(
                new QueryWrapper<Category>().
                        eq("id", id).
                        select(
                                "id",
                                "name"
                        )
        );
        return ResponseData.success(map);
    }

    /**
     * 修改
     *
     * @param category
     * @param bindingResult
     * @return
     */
    @Override
    public ResponseData<Object> update1(Category category, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgementUtil.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }

        try {
            updateById(category);
            return ResponseData.success(null);
        } catch (Exception e) {
            return ResponseData.fail("数据修改失败！");
        }
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    public ResponseData<Object> del(List<Long> ids) {
        try {
            removeByIds(ids);
            return ResponseData.success("数据删除成功！");
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }
}
