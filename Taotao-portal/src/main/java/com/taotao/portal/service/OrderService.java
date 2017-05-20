package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.pojo.TbItemExt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderService {
    List<TbItemExt> getItemData(HttpServletRequest request) throws Exception;

    TaotaoResult createOrder(OrderInfo orderInfo, HttpServletRequest request,HttpServletResponse response)  throws Exception;
}
