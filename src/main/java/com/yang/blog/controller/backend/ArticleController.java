package com.yang.blog.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangyi
 * @date 2020/6/17 17:01
 * @description：
 */
@Controller
@RequestMapping("admin/content/article")
public class ArticleController {

    @GetMapping("add")
    public String saveView() {
        return "backend/content/article/add";
    }
}
