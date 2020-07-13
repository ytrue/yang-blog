package com.yang.blog.controller.frontend;


import com.yang.blog.mapper.ArticleMapper;
import com.yang.blog.search.repository.EsArticleRepository;
import com.yang.blog.service.IArticleService;
import com.yang.blog.service.ITagService;
import com.yang.blog.util.QueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MyBlogController {

    @Autowired
    private ITagService tagService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private EsArticleRepository esArticleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping({"index", "/"})
    public String index(QueryCondition params, Map<String, Object> map) {
        //获得标签
        // tagService.list(new QueryWrapper<Tag>().select("id", "name", "number").orderByDesc("number", "id"));

        //获得最新发布的文章
        //articleService.list(new QueryWrapper<Article>().select("id", "title").orderByDesc("id"));

        //获得点击最多的文章
        //articleService.list(new QueryWrapper<Article>().select("id", "title").orderByDesc("views"));


        //获得页码
        Long page = params.getPage();
        //获得每页条数
        Long limit = params.getLimit();
        //获得startLimit
        Long startLimit = (page - 1) * limit;
        //这里要设置一下分页查询
        List<Map<String, Object>> articleList = articleMapper.articleWithCategory(0L, limit);

        System.out.println(articleList);
        return "frontend/index";
    }

    /**
     * 文章详情页
     */

    /**
     *友情链接
     */

    /**
     * 关于
     */
}
