package com.taotao.order.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.jedis.JedisGlobalClient;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private TbOrderMapper mOrderMapper;

    @Resource
    private TbOrderItemMapper mOrderItemMapper;

    @Resource
    private TbOrderShippingMapper mOrderShippingMapper;

    @Resource
    private JedisGlobalClient mJedisGlobalClient;


    @Value("${redis_order_key}")
    private String REDIS_ORDER_KEY;

    @Value("${redis_order_item_key}")
    private String REDIS_ORDER_ITEM_KEY;

    @Value("${redis_order_key_initValue}")
    private Long REDIS_ORDER_KEY_INITVALUE;


    @Override
    public TaotaoResult saveOrder(OrderInfo orderInfo) throws Exception {

        String orderId = mJedisGlobalClient.get(REDIS_ORDER_KEY);
        if (StringUtils.isBlank(orderId)) {
            mJedisGlobalClient.set(REDIS_ORDER_KEY, REDIS_ORDER_KEY_INITVALUE.toString());
            orderId = REDIS_ORDER_KEY_INITVALUE.toString();
        }
        mJedisGlobalClient.incr(REDIS_ORDER_KEY).toString();

        Date date = new Date();
        // 1.存储订单
        // 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        orderInfo.setOrderId(orderId);
        orderInfo.setCreateTime(date);
        orderInfo.setUpdateTime(date);
        mOrderMapper.insert(orderInfo);

        // 2.存储订单详情
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem orderItem : orderItems) {
            Long orderItemKey = mJedisGlobalClient.incr(REDIS_ORDER_ITEM_KEY);
            orderItem.setId(orderItemKey.toString());
            orderItem.setOrderId(orderInfo.getOrderId());
            mOrderItemMapper.insert(orderItem);
        }

        // 3.存储订单和客户的关系表
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderInfo.getOrderId());
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        mOrderShippingMapper.insert(orderShipping);

        return TaotaoResult.ok(orderId);
    }
}
