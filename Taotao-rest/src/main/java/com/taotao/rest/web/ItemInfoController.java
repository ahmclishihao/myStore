package com.taotao.rest.web;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.service.ItemInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/iteminfo")
public class ItemInfoController {

    @Resource
    private ItemInfoService mItemInfoService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public Object queryItem(@PathVariable("itemId") Long itemId) {
        try {
            TbItem tbItem = mItemInfoService.getItemById(itemId);
            return TaotaoResult.ok(tbItem);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public Object queryItemDesc(@PathVariable("itemId") Long itemId) {
        try {
            TbItemDesc itemDesc = mItemInfoService.getDescByItemId(itemId);
            return TaotaoResult.ok(itemDesc);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
    }

    @RequestMapping("/params/{itemId}")
    @ResponseBody
    public Object queryItemParams(@PathVariable("itemId") Long itemId) {
        try {
            TbItemParamItem itemParamItem = mItemInfoService.getItemParamByItemId(itemId);
            return TaotaoResult.ok(itemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
    }


}
