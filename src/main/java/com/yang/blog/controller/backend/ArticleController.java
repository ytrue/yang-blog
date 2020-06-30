package com.yang.blog.controller.backend;

import com.yang.blog.entity.Article;
import com.yang.blog.search.entity.EsArticle;
import com.yang.blog.search.repository.ArticleRepository;
import com.yang.blog.service.IArticleService;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
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

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ArticleRepository articleRepository;

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


        return null;
    }

    @GetMapping("add")
    @ResponseBody
    public String saveView() {


        Iterable<EsArticle> all = articleRepository.findAll();

        System.out.println(all);

        return null;
        // return "backend/content/article/add";
    }
}
