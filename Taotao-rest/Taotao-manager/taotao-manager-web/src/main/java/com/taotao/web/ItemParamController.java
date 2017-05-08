package com.taotao.web;

import com.taotao.common.bean.EasyUIPageBean;
import com.taotao.common.bean.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExt;
import com.taotao.service.ItemParamService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Resource
    private ItemParamService mItemParamService;

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIPageBean<TbItemParamExt> paramList(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        EasyUIPageBean<TbItemParamExt> pageList = null;
        try {
            pageList = mItemParamService.findPageList(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageList;
    }

    @RequestMapping("/query/itemcatid/{catid}")
    @ResponseBody
    public TaotaoResult queryByCatId(@PathVariable(value = "catid") Long catid) {
        try {
            TbItemParam itemParam = mItemParamService.selectByCatId(catid);
            return TaotaoResult.ok(itemParam);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, "查询失败！！");
        }
    }

    @RequestMapping("/save/{catid}")
    @ResponseBody
    public TaotaoResult saveByCatId(@PathVariable("catid") Long catid, String paramData) {
        try {
            mItemParamService.saveByCatId(catid, paramData);
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaotaoResult.build(-1, "参数添加失败！");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteById(@RequestParam("ids") Long[] ids) {
        // TODO 删除规格参数
        try {
            mItemParamService.deleteByIds(ids);
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, "规格参数删除失败！");
        }
    }



}
