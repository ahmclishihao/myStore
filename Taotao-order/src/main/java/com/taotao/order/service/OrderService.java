package com.taotao.order.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;

public interface OrderService {
    TaotaoResult saveOrder(OrderInfo orderInfo) throws Exception;
}
