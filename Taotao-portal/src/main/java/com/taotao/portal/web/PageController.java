package com.taotao.portal.web;

import com.taotao.portal.service.ContentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class PageController {

    @Resource
    private ContentService mContentService;

    @RequestMapping("/index")
    public String showIndex(Model model){
        String jsonData = "";
        try {
            jsonData = mContentService.getIndexSlideBannerInfos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("indexSlideBanner",jsonData);
        return "index";
    }

}
