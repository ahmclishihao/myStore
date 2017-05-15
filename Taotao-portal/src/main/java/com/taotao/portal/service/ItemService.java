package com.taotao.portal.service;

import com.taotao.portal.pojo.SolrInfoResult;

public interface ItemService {

    SolrInfoResult getSolrInfoResult(String keywords, Integer pageNum, Integer rows) throws Exception;

}
