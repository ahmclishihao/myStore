package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.HttpUtils;
import com.taotao.portal.pojo.SolrInfoResult;
import com.taotao.portal.pojo.SolrItemInfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Value("${search_server_address}")
    private String SEARCH_SERVER_ADDRESS;

    @Override
    public SolrInfoResult getSolrInfoResult(String keywords, Integer pageNum, Integer rows) {

        Map<String, Object> params = new HashMap<>();
        params.put("keywords", keywords);
        params.put("pageNum", pageNum);
        params.put("rows", rows);

        String jsonData = HttpUtils.GET(SEARCH_SERVER_ADDRESS, params);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(jsonData, SolrInfoResult.class);
        return (SolrInfoResult) taotaoResult.getData();
    }
}
