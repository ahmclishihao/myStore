package com.taotao.search.contoller;

import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.bean.TaotaoResult;
import com.taotao.search.pojo.SolrInfoResult;
import com.taotao.search.service.ItemInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/iteminfo")
public class ItemInfoController {
    @Resource
    private ItemInfoService mItemInfoService;

    @RequestMapping("/resolve")
    @ResponseBody
    public Object resolveItemInfo() {
        try {
            mItemInfoService.resolveItemInfoToSolr();
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
    }

    @RequestMapping("/search")
    @ResponseBody
    public Object searchItemInfo(@RequestParam(defaultValue = "") String keywords,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "30") Integer rows) {
        try {
            SolrInfoResult solrInfoResult = mItemInfoService.selectBySolrQuery(keywords, pageNum, rows);
            return TaotaoResult.ok(solrInfoResult);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object addItemToSolr(@RequestParam Long id) {
        try {
            mItemInfoService.insertToSolr(id);
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
    }

}
