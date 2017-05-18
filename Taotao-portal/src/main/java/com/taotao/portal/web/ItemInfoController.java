package com.taotao.portal.web;

import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.pojo.TbItemExt;
import com.taotao.portal.service.ItemService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;

@Controller
public class ItemInfoController {

    @Resource
    private ItemService mItemService;

    @RequestMapping("/item/{itemId}")
    public String queryItem(@PathVariable("itemId") Long itemId, Model model) {
        try {
            TbItemExt itemById = mItemService.getItemById(itemId);
            model.addAttribute("item", itemById);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "item";
    }

    @RequestMapping("/item/desc/{itemId}")
    @ResponseBody
    public Object queryItemDesc(@PathVariable("itemId") Long itemId) {
        try {
            TbItemDesc itemDesc = mItemService.getItemDescById(itemId);
            return itemDesc.getItemDesc();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("/item/params/{itemId}")
    @ResponseBody
    public Object queryItemParams(@PathVariable("itemId") Long itemId) {
        try {
            String itemParams = mItemService.getItemParamsById(itemId);
            return itemParams;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
