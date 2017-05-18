package com.taotao.rest.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

public interface ItemInfoService {
    TbItem getItemById(Long id) throws Exception;

    TbItemDesc getDescByItemId(Long id) throws Exception;

    TbItemParamItem getItemParamByItemId(Long id) throws Exception;
}
