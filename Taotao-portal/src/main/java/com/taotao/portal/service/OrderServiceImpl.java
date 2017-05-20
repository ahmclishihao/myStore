package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.HttpUtils;
import com.taotao.common.util.JackSonUtil;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.pojo.TbItemExt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private CartService mCartService;

    @Value("${ORDER_CREATE_ADDRESS}")
    private String ORDER_CREATE_ADDRESS;

    @Value("${TT_CART}")
    private String TT_CART;

    @Override
    public List<TbItemExt> getItemData(HttpServletRequest request) throws Exception {
        List<TbItemExt> cartList = mCartService.getCartList(request);
        return cartList;
    }

    @Override
    public TaotaoResult createOrder(OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 从拦截器中去除用户数据
        TbUser currentUser = (TbUser) request.getAttribute("currentUser");
        orderInfo.setUserId(currentUser.getId());
        orderInfo.setBuyerNick(currentUser.getUsername());

        String jsonData = JackSonUtil.objectToJson(orderInfo);

        String retJsonData = HttpUtils.JsonPOST(ORDER_CREATE_ADDRESS, jsonData);

        if (StringUtils.isNotBlank(retJsonData)) {
            CookieUtils.setCookie(request, response, TT_CART, "");
            return TaotaoResult.format(retJsonData);
        }


        return TaotaoResult.build(-1, "订单创建失败!");
    }
}
