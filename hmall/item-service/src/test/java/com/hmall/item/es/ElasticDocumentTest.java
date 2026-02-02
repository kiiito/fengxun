package com.hmall.item.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.item.domain.po.Item;
import com.hmall.item.domain.po.ItemDoc;
import com.hmall.item.service.IItemService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;

@SpringBootTest(properties = "spring.profiles.active=local")
@TestPropertySource(properties = {
        "seata.enabled=false"
})
public class ElasticDocumentTest {
    private RestHighLevelClient client;
    @Autowired
    private IItemService itemService;


    @Test
    void testIndexDoc() throws IOException {
        //准备文档数据
        Item item = itemService.getById(100000011127L);
        ItemDoc itemDoc = BeanUtil.copyProperties(item, ItemDoc.class);
        // 1 准备客户端request对象
        IndexRequest request = new IndexRequest("items").id(itemDoc.getId());
        // 2 准备请求参数
        request.source(JSONUtil.toJsonStr(itemDoc), XContentType.JSON);
        // 3 发送请求
        client.index(request, RequestOptions.DEFAULT);
    }


    @Test
    void testGetDoc() throws IOException {
        // 1 准备客户端request对象
        GetRequest request = new GetRequest("items", "100000011127");
        // 3 发送请求
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        String json = response.getSourceAsString();
        ItemDoc itemDoc = JSONUtil.toBean(json, ItemDoc.class);
        System.out.println("itemDoc = "  + itemDoc);
    }

    @Test
    void testDeleteDoc() throws IOException {
        // 1 准备客户端request对象
        DeleteRequest request = new DeleteRequest("items", "100000011127");
        // 3 发送请求
        client.delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void testUpdateDoc() throws IOException {
        // 1 准备客户端request对象
        UpdateRequest request = new UpdateRequest("items", "100000011127");
        request.doc(
                "price", 256000
        );
        // 3 发送请求
        client.update(request, RequestOptions.DEFAULT);
    }


    /**
     * 批处理
     */
    @Test
    void testBulkDoc() throws IOException {
        int pageNo = 1, pageSize = 500;
      while ( true){
          // 1 准备数据
          Page<Item> page = itemService.lambdaQuery()
                  .eq(Item::getStatus, 1)
                  .page(new Page<>(pageNo, pageSize));
          List<Item> itemList = page.getRecords();
          if (itemList == null || itemList.isEmpty()){
              return;
          }
          // 2 创建批量请求
          BulkRequest request = new BulkRequest();
          for (Item item : itemList){
              request.add(new IndexRequest("items")
                      .id(item.getId().toString())
                      .source(JSONUtil.toJsonStr(BeanUtil.copyProperties(item, ItemDoc.class)),XContentType.JSON));
          }
          // 3 发送请求
          client.bulk(request, RequestOptions.DEFAULT);
          // 4 翻页
          pageNo++;
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
