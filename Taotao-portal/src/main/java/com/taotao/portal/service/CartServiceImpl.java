package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.JackSonUtil;
import com.taotao.portal.pojo.TbItemExt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private ItemService mItemService;

    @Value("${TT_CART}")
    private String TT_CART;
    @Value("${TT_CART_MAXAGE}")
    private Integer TT_CART_MAXAGE;

    @Override
    public TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (itemId == null || num < 1) {
            return TaotaoResult.build(-1, "添加购物车失败，错误的购物信息！");
        }

        // 1.从已有的购物车中获取数据
        List<TbItemExt> tbItemExts = getCartByCookie(request);
        // 2.添加到购物车列表中
        try {
            //      1.判断是否已经存在对应的商品
            boolean existsFlag = false;
            for (TbItemExt tbItemExt : tbItemExts) {
                if (tbItemExt.getId().equals(itemId)) {
                    tbItemExt.setCartItemNum(tbItemExt.getCartItemNum() + num);
                    existsFlag = true;
                }
            }
            //      2.如果不存在对应的商品
            if (!existsFlag) {
                TbItemExt tbItemExt = mItemService.getItemById(itemId);
                if (tbItemExt != null) {
                    tbItemExt.setCartItemNum(num);
                    tbItemExts.add(tbItemExt);
                }
            }
            // 3.将购物车信息填写入cookie中
            saveCartToCookie(request, response, tbItemExts);
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
    }

    public List<TbItemExt> getCartByCookie(HttpServletRequest request) {
        String jsonData = CookieUtils.getCookieValue(request, TT_CART, true);
        try {
            if (StringUtils.isNotBlank(jsonData))
                return JackSonUtil.jsonToList(jsonData, TbItemExt.class);
            else
                return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveCartToCookie(HttpServletRequest request, HttpServletResponse response, List<TbItemExt> tbItemExts) {
        if (tbItemExts != null)
            CookieUtils.setCookie(request, response, TT_CART, JackSonUtil.objectToJson(tbItemExts), TT_CART_MAXAGE, true);
    }

    @Override
    public List<TbItemExt> getCartList(HttpServletRequest request) throws Exception {
        return getCartByCookie(request);
    }

    @Override
    public TaotaoResult updateCart(Long itemId, Integer itemNum, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<TbItemExt> cartByCookie = getCartByCookie(request);
        // 更新为指定的个数
        for (TbItemExt tbItemExt : cartByCookie) {
            if (tbItemExt.getId().equals(itemId)) {
                tbItemExt.setCartItemNum(itemNum);
            }
        }
        saveCartToCookie(request, response, cartByCookie);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteCart(Long itemId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<TbItemExt> cartByCookie = getCartByCookie(request);

        TbItemExt tbItemExt;
        for (int i = 0, size = cartByCookie.size(); i < size; i++) {
            tbItemExt = cartByCookie.get(i);
            if (tbItemExt.getId().equals(itemId)) {
                cartByCookie.remove(tbItemExt);
                i--;
                size--;
            }
        }
        saveCartToCookie(request, response, cartByCookie);
        return TaotaoResult.ok();
    }

}
