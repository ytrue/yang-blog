package com.yang.blog.controller.backend;

import com.yang.blog.entity.Article;
import com.yang.blog.service.IArticleService;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yangyi
 * @date 2020/6/17 17:01
 * @description：
 */
@Controller
@RequestMapping("admin/content/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    /**
     * 新增
     *
     * @param article
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @PostMapping("add")
    public ResponseData<Object> save(
            @RequestBody
            @Valid Article article,
            BindingResult bindingResult
    ) {
        return articleService.add(article, bindingResult);
    }

    @GetMapping("add")
    public String saveView() {
        return "backend/content/article/add";
    }
}
