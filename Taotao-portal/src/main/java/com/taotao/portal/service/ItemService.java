package com.taotao.portal.service;

import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.pojo.SolrInfoResult;
import com.taotao.portal.pojo.TbItemExt;

public interface ItemService {

    SolrInfoResult getSolrInfoResult(String keywords, Integer pageNum, Integer rows) throws Exception;

    TbItemExt getItemById(Long id) throws Exception;

    TbItemDesc getItemDescById(Long id) throws Exception;

    String getItemParamsById(Long id) throws Exception;
}
