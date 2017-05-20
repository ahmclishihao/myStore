package com.taotao.order.web;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService mOrderService;

    @RequestMapping("/create")
    @ResponseBody
    public Object createOrder(@RequestBody OrderInfo orderInfo){
        try {
            TaotaoResult taotaoResult = mOrderService.saveOrder(orderInfo);
            return taotaoResult;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
    }

}
