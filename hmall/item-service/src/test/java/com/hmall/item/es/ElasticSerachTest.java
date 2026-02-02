package com.hmall.item.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.item.domain.po.Item;
import com.hmall.item.domain.po.ItemDoc;
import com.hmall.item.service.IItemService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//@SpringBootTest(properties = "spring.profiles.active=local")
@TestPropertySource(properties = {
        "seata.enabled=false"
})
public class ElasticSerachTest {
    private RestHighLevelClient client;

    @Test
    void testMatchAll() throws IOException {
        //创建request对象
        SearchRequest request = new SearchRequest("items");
        // 2 配置request参数
        request.source()
                .query(QueryBuilders.matchAllQuery());
        // 3 发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response = " + response);
        parseResponseResult(response);
    }

    /**
     *构建查询条件
     */
    @Test
    void testSearch() throws IOException {
        SearchRequest request = new SearchRequest("items");
        request.source()
                .query(
                        QueryBuilders.boolQuery()
                                .must(QueryBuilders.matchQuery("name","脱脂牛奶"))
                                .filter(QueryBuilders.termQuery("brand","德亚"))
                                .filter(QueryBuilders.rangeQuery("price").lt(10000))
                );
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        parseResponseResult(response);
    }

    @Test
    void testSortAndPage() throws IOException {
        int pageNo = 1, pageSize = 5;
        SearchRequest request = new SearchRequest("items");
        // 1 配置查询条件
        // 1.1 query 查询条件
        request.source()
                .query(QueryBuilders.matchAllQuery());
        // 1.2 分页条件
        request.source()
                .from((pageNo - 1) * pageSize).size(pageSize);
        // 1.3 排序条件
        request.source()
                .sort("price", SortOrder.ASC)
                .sort("sold",SortOrder.DESC);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        parseResponseResult(response);
    }

    @Test
    void testHighlight() throws IOException {

        SearchRequest request = new SearchRequest("items");
        // 1 配置查询条件
        // 1.1 query 查询条件
        request.source()
                .query(QueryBuilders.matchQuery("name","脱脂牛奶"));
        // 1.2 高亮条件
        request.source()
                .highlighter(SearchSourceBuilder.highlight().field("name").preTags("<em>").postTags("</em>"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        parseResponseResult(response);
    }

    @Test
    void testAgg() throws IOException {

        SearchRequest request = new SearchRequest("items");
        // 1 配置查询条件
        // 1.1 分页查询条件
        request.source().size(0);
        String brandAggName = "bandAgg";
        // 1.2 高亮条件
        request.source()
                .aggregation(
                        AggregationBuilders.terms(brandAggName)
                                .field("brand").size(10)
                );
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //解析结果
        Aggregations aggregations = response.getAggregations();
        //根据聚合名称去获取对应的聚合 注意这里是父接口 需要用子类才会有获取桶的方法
//        Aggregation aggregation = aggregations.get(brandAggName);
        Terms terms = aggregations.get(brandAggName);
        //获取buckets
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        //遍历桶
        for (Terms.Bucket bucket : buckets) {
            // 获取桶中的key
            String key = bucket.getKeyAsString();
            System.out.println("key = " + key);
            // 获取桶中的数量
            long docCount = bucket.getDocCount();
            System.out.println("docCount = " + docCount);
        }
    }

    private void parseResponseResult(SearchResponse response) {
        // 4 解析结果
        SearchHits searchHits = response.getHits();
        // 总条数
        long value = searchHits.getTotalHits().value;
        System.out.println("总共查询到" + value  + "条数据");
        // 命中的数据
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            // 获取文档内容
            String json = hit.getSourceAsString();
            // 转换成对象
            ItemDoc itemDoc = JSONUtil.toBean(json, ItemDoc.class);
            System.out.println("itemDoc = " + itemDoc);

            // 4.3 处理高亮结果
            Map<String, HighlightField> his = hit.getHighlightFields();
            if (his != null && !his.isEmpty()){
                // 根据高亮字段获取高亮结果
                HighlightField name = his.get("name");
                //获取高亮结果 覆盖非高亮结果
                String string = name.getFragments()[0].string();
                itemDoc.setName(string);
            }
        }
    }

    @BeforeEach
    void setUp() {
        client = new RestHighLevelClient(
                RestClient.builder(
                      HttpHost.create(
                              "http://192.168.253.136:9200"
                      )
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        if (client != null){
            client.close();
        }
    }
}
