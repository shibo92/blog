package com.shibo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author shibo
 */
@Controller
@Slf4j
@RequestMapping("solr")
public class SolrController {
    @Autowired
    private SolrClient solrClient;
    private final String db_core = "new_core";


    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     *
     * @return
     */
    @RequestMapping("search")
    @ResponseBody
    public Map<String, Map<String, List<String>>> search() {
        try {
            SolrQuery params = new SolrQuery();
            //查询条件
            params.set("q", "labels:java");
            //过滤条件
//            params.set("fq", "product_price:[100 TO 100000]");
            //排序
//            params.addSort("product_price", SolrQuery.ORDER.asc);
            //分页
            params.setStart(0);
            params.setRows(20);
            //默认域
//            params.set("df", "product_title");
            //只查询指定域
//            params.set("fl", "id,product_title,product_price");
            //高亮
            //打开开关
            params.setHighlight(true);
            //指定高亮域
            params.addHighlightField("labels");
            //设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            //设置后缀
            params.setHighlightSimplePost("</span>");

            QueryResponse queryResponse = solrClient.query(db_core,params);
            SolrDocumentList results = queryResponse.getResults();
            long numFound = results.getNumFound();
            System.out.println(numFound);

            //获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
            Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();

            for (SolrDocument result : results) {
                System.out.println(result.get("id"));
                System.out.println(result.get("title"));
                System.out.println(result.get("labels"));

                Map<String, List<String>> map = highlight.get(result.get("id"));
                List<String> list = map.get("labels");
                System.out.println(list.get(0));
                System.out.println("------------------");
                System.out.println();
            }
            return highlight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
