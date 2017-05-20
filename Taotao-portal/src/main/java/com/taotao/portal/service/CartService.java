package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.portal.pojo.TbItemExt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CartService {
    TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) throws Exception;

    List<TbItemExt> getCartList(HttpServletRequest request) throws  Exception;

    TaotaoResult updateCart(Long itemId, Integer itemNum, HttpServletRequest request, HttpServletResponse response) throws Exception;

    TaotaoResult deleteCart(Long itemId, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
