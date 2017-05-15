package com.taotao.search.dao;

import com.taotao.search.pojo.SolrInfoResult;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocument;

public interface SolrQueryDao {

    SolrInfoResult selectBySolrQuery(SolrQuery solrQuery) throws Exception;



}
