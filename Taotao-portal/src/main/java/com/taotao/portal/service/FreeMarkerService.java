package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;

public interface FreeMarkerService {
    TaotaoResult generateItemHtml(Long itemId) throws Exception;
}
