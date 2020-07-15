package com.yang.blog.controller.frontend;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.blog.entity.Article;
import com.yang.blog.entity.Tag;
import com.yang.blog.mapper.ArticleMapper;
import com.yang.blog.search.entity.EsArticle;
import com.yang.blog.service.IArticleService;
import com.yang.blog.service.ITagService;
import com.yang.blog.util.QueryCondition;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private ITagService tagService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    /**
     * 公共部分
     *
     * @param map
     */
    @ModelAttribute
    public void common(Map<String, Object> map) {
        List<Tag> tags = tagService.list(
                new QueryWrapper<Tag>()
                        .select("id", "name", "number")
                        .orderByDesc("number", "id")
        );

        List<Article> newArticles = articleService.list(
                new QueryWrapper<Article>()
                        .select("id", "title")
                        .orderByDesc("id")
                        .last("limit 0, 4")
        );


        List<Article> hostArticles = articleService.list(
                new QueryWrapper<Article>()
                        .select("id", "title")
                        .orderByDesc("views", "id")
                        .last("limit 0, 4")
        );

        map.put("newArticles", newArticles);
        map.put("hostArticles", hostArticles);
        map.put("tags", tags);
    }

    /**
     * 首页
     *
     * @return
     */
    @GetMapping({"index", "/"})
    public String index(QueryCondition params, Map<String, Object> map) {

        return "frontend/index";
    }


    /**
     * tag标签的搜索
     *
     * @param value
     * @return
     */
    @GetMapping(value = "tag/{value}")
    public String tag(@PathVariable("value") String value) {
        //elasticsearch获得数据
        Document document = EsArticle.class.getAnnotation(Document.class);
        //构建查询对象
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                //设置搜索类型，默认值就是QUERY_THEN_FETCH
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                //指定索引库和文档类型
                .withIndices(document.indexName()).withTypes(document.type())
                .withQuery(
                        QueryBuilders.boolQuery()
                                .filter(
                                        QueryBuilders.termQuery("tag", value)
                                )
                )
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))
                //设置分页
                .withPageable(PageRequest.of(0, 10))
                .build();

        List<EsArticle> esArticles = elasticsearchTemplate.queryForList(searchQuery, EsArticle.class);

        return "frontend/index";
    }

    /**
     * 表单的搜索
     *
     * @param value
     * @return
     */
    @GetMapping(value = "search/{value}")
    public String search(@PathVariable("value") String value) {
        //elasticsearch获得数据
        Document document = EsArticle.class.getAnnotation(Document.class);
        //构建查询对象
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                //设置搜索类型，默认值就是QUERY_THEN_FETCH
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                //指定索引库和文档类型
                .withIndices(document.indexName()).withTypes(document.type())
                .withQuery(
                        QueryBuilders.matchQuery("title", value)
                )
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))
                //设置分页
                .withPageable(PageRequest.of(0, 10))
                .build();

        List<EsArticle> esArticles = elasticsearchTemplate.queryForList(searchQuery, EsArticle.class);

        return "frontend/index";
    }





}
