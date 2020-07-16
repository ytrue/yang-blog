package com.yang.blog.controller.backend;

import com.yang.blog.entity.Category;
import com.yang.blog.service.ICategoryService;
import com.yang.blog.dto.BaseQueryParam;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/15 16:38
 * @description：文章栏目控制器
 */
@RequestMapping("admin/content/category")
@Controller
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * 排序分页查询
     *
     * @param queryCondition
     * @return
     */
    @ResponseBody
    @PostMapping("index")
    public Map<String, Object> index(@RequestBody BaseQueryParam queryCondition) {
        return categoryService.queryPage(queryCondition);
    }


    /**
     * 添加数据
     *
     * @param category
     * @return
     */
    @ResponseBody
    @PostMapping("add")
    public ResponseData<Object> save(
            @RequestBody
            @Validated Category category,
            BindingResult bindingResult
    ) {
        return categoryService.add(category, bindingResult);
    }


    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("update")
    public ResponseData<Map<String, Object>> find(@RequestParam(value = "id") Long id) {
        return categoryService.find(id);
    }

    /**
     * 修改数据
     *
     * @param category
     * @return
     */
    @ResponseBody
    @PutMapping("update")
    public ResponseData<Object> update(
            @Validated
            @RequestBody Category category,
            BindingResult bindingResult
    ) {
        return categoryService.update1(category, bindingResult);
    }

    /**
     * id 删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("delete")
    public ResponseData<Object> delete(@RequestBody List<Long> ids) {
        return categoryService.del(ids);
    }

    @GetMapping("index")
    public String indexView() {
        return "backend/content/category/index";
    }

    @GetMapping("add")
    public String saveView() {
        return "backend/content/category/add";
    }

    @GetMapping("update")
    public String editView() {
        return "backend/content/category/edit";
    }
}

