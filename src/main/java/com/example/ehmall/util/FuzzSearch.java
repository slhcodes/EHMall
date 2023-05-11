package com.example.ehmall.util;


import io.swagger.models.auth.In;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zhumeng
 * @time 2023 04/30/21:30
 *
 */
public class FuzzSearch {
    /**
     * 线程安全且高效的单例模式实例
     */
    private static volatile RestHighLevelClient client;
    /**
     * 连接es集群
     * @return 成功了就返回true
     */
    public  static RestHighLevelClient getInstence(){
        if(client==null){
            synchronized (FuzzSearch.class){
                if (client==null){

                    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic","CUGerhuo444"));

                    client = new RestHighLevelClient(
                            RestClient.builder(
                                    new HttpHost("es-8xsozgcd.public.tencentelasticsearch.com", 9200, "https")).setHttpClientConfigCallback(f -> f.setDefaultCredentialsProvider(credentialsProvider)));
                }
            }
        }
        return client;
    }
    /**
     * 向es集群中模糊查询
     * @return json格式的查询结果
     */
    public static List<Integer> getUser(String userName) throws IOException {
        client=getInstence();
        SearchRequest searchRequest = new SearchRequest("username_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("username", userName);
        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        return getSC1(response,"user_id");

    }
    /**
     * 向es集群中模糊查询
     * @return json格式的查询结果
     */
    public static List<Integer> getCommodity1(String comName) throws IOException {
        client=getInstence();
        SearchRequest searchRequest = new SearchRequest("goods_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("description", comName);
        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        return getSC1(response,"id");

    }


    public static List<Integer> getSC1(SearchResponse sr,String id1) {
        List<Integer> res=new ArrayList<>();
        if(sr==null)
        {
            return null;
        }
        for (SearchHit hit : sr.getHits().getHits()) {
            int id = Integer.parseInt(hit.getId());
            res.add(id);
//            if (!source.isEmpty()) {
//                for (SearchHit hit : sr.getHits().getHits()) {
//                    String id = hit.getSourceAsMap().get("id").toString();
//                    System.out.println("ID: " + id);
//                }
//                for(Iterator<Map.Entry<String, Object>> it =
//                    source.entrySet().iterator(); it.hasNext();) {
//                    Map.Entry<String, Object> entry = it.next();
//                    a+=entry.getKey();
//                    a+=entry.getValue();
//                    System.out.println(entry.getKey());
//                    if ("_id".equals(entry.getKey())) {
//                        System.out.println("title: " + entry.getValue());
//                    }
//                }
//            }
        }
        return res;
    }}
