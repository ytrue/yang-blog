package com.yang.blog.elasticsearch.controller;

import com.yang.blog.elasticsearch.entity.Product;
import com.yang.blog.elasticsearch.repository.ProductRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("test")
    public void test() {

        Document document = Product.class.getAnnotation(Document.class);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                .withIndices(document.indexName()).withTypes(document.type())
                .addAggregation(AggregationBuilders.terms("group_by_tag").field("tags"))
                .build();


        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, SearchResponse::getAggregations);

        Map<String, Aggregation> results  = aggregations.asMap();

        StringTerms stringTerms= (StringTerms) results.get("group_by_tag");

        List<StringTerms.Bucket> buckets = stringTerms.getBuckets();

        buckets.forEach(new Consumer<StringTerms.Bucket>() {
            @Override
            public void accept(StringTerms.Bucket bucket) {
                System.out.println(bucket.getKey());
                System.out.println(bucket.getDocCount());
            }
        });



    }
}
