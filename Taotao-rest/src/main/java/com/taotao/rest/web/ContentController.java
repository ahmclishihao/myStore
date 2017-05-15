package com.taotao.rest.web;

import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.bean.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService mContentService;

    @RequestMapping("/query/{categoryId}")
    @ResponseBody
    public Object getContentByCategoryId(@PathVariable("categoryId") Long categoryId) {
        try {
            List<TbContent> contentList = mContentService.getContentList(categoryId);
            return contentList;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, "内容获取失败！" + ExceptionUtil.exception2String(e));
        }
    }

}
