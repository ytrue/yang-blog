package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.Article;
import com.yang.blog.mapper.ArticleMapper;
import com.yang.blog.search.entity.EsArticle;
import com.yang.blog.service.IArticleService;
import com.yang.blog.util.ResponseData;
import com.yang.blog.util.VerificationJudgementUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 新增
     *
     * @param article
     * @param bindingResult
     * @return
     */
    @Override
    public ResponseData<Object> add(Article article, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgementUtils.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }
        //把tag转化为list
        String tag = article.getTag();
        String[] tagArr = tag.split(",");
        List<String> tagList = Arrays.asList(tagArr);
        //获得tag里所有的tag，之后对比差集，之后把差集添加进去，之后获得添加的id
        //还有一种方案，就是加到elasticsearch里，还是要上

        boolean b = elasticsearchTemplate.indexExists(EsArticle.class);

        if (!b) {
            elasticsearchTemplate.createIndex(EsArticle.class);
            elasticsearchTemplate.putMapping(EsArticle.class);
        }
        boolean b1 = elasticsearchTemplate.indexExists(EsArticle.class);
        System.out.println("b--------" + b1);


        System.out.println(article);

        return null;
    }
}
