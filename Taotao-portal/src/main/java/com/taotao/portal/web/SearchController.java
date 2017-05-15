package com.taotao.portal.web;

import com.taotao.portal.pojo.SolrInfoResult;
import com.taotao.portal.service.ItemService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class SearchController {

    @Resource
    private ItemService mItemService;

    @RequestMapping("/search")
    public String search(@RequestParam(defaultValue = "") String keywords,
                         @RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "30") Integer rows, Model model){
        try {
            SolrInfoResult solrInfoResult = mItemService.getSolrInfoResult(keywords, pageNum, rows);
            model.addAttribute("query",keywords);
            model.addAttribute("totalPages",solrInfoResult.getPageTotal());
            model.addAttribute("itemList",solrInfoResult.getRecordList());
            model.addAttribute("page",solrInfoResult.getCurrentPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "search";
    }

}
