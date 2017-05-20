package com.taotao.portal.web;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.portal.pojo.TbItemExt;
import com.taotao.portal.service.CartService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService mCartService;

    @RequestMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable Long itemId,
                                @RequestParam(value = "itemNum", defaultValue = "1", required = false) Integer num,
                                HttpServletRequest request, HttpServletResponse response) {
        try {
            TaotaoResult result = mCartService.addCart(itemId, num, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cart-success";
    }

    @RequestMapping("/cart")
    public String cartList(HttpServletRequest request, Model model) {
        try {
            List<TbItemExt> cartList = mCartService.getCartList(request);
            model.addAttribute("cartList", cartList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cart";
    }

    @RequestMapping("/update/num/{itemId}/{itemNum}")
    @ResponseBody
    public TaotaoResult updateCart(@PathVariable Long itemId, @PathVariable Integer itemNum, HttpServletRequest request, HttpServletResponse response) {
        try {
            return mCartService.updateCart(itemId, itemNum, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
    }

    @RequestMapping("/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        try {
            mCartService.deleteCart(itemId, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cart/cart";
    }
}
