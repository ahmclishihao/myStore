package com.taotao.search.dao;

import com.taotao.search.pojo.SolrItemInfo;

import java.util.List;

public interface ItemInfoMapper {
    List<SolrItemInfo> selectItemInfo();

    SolrItemInfo findSolrItemInfoById(Long id);

}
