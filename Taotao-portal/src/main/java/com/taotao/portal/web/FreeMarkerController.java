package com.taotao.portal.web;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.portal.service.FreeMarkerService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class FreeMarkerController {

    @Resource
    private FreeMarkerService mFreeMarkerService;

    @RequestMapping("/gen/item/{itemId}")
    @ResponseBody
    public Object generatorItemHtml(@PathVariable("itemId") Long itemId){
        try {
            return mFreeMarkerService.generateItemHtml(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
    }

}
