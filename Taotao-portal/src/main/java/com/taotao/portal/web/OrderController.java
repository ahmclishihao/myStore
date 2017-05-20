package com.taotao.portal.web;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.pojo.TbItemExt;
import com.taotao.portal.service.OrderService;

import org.apache.commons.lang3.text.FormattableUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Formatter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService mOrderService;

    @RequestMapping("/order-cart")
    public String showOrderPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<TbItemExt> itemData = mOrderService.getItemData(request);
            model.addAttribute("cartList", itemData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "order-cart";
    }

    @RequestMapping("/create")
    public String createOrder(OrderInfo orderInfo,HttpServletRequest request,HttpServletResponse response,Model model){
        try {
            TaotaoResult order = mOrderService.createOrder(orderInfo, request,response);
            Object data = order.getData();
            model.addAttribute("orderId",data);
            model.addAttribute("payment",orderInfo.getPayment());
            Date date = DateUtils.addDays(new Date(), 3);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            model.addAttribute("date", dateFormat.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


}
