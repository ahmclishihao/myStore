package com.taotao.search.service;


import com.taotao.search.pojo.SolrInfoResult;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface ItemInfoService {

    void resolveItemInfoToSolr() throws Exception;

    SolrInfoResult selectBySolrQuery(String keywords,Integer pageNum,Integer rows) throws Exception;

    /**
     * 添加新的信息到solr服务器
     * @param itemId
     */
    void insertToSolr(Long itemId) throws IOException, SolrServerException;
}
