package com.taotao.web;

import com.taotao.common.bean.EasyUIPageBean;
import com.taotao.common.bean.EasyUITreeNode;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.bean.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentCategoryService;
import com.taotao.service.ContentService;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Resource
    private ContentCategoryService mContentCategoryService;
    @Resource
    private ContentService mContentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public Object queryContentListByCategoryId(@RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
                                               @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum, Integer rows) {
        try {
            EasyUIPageBean easyUIPageBean = mContentService.getContentListByCategoryId(categoryId, pageNum, rows);
            return easyUIPageBean;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, "查询失败 ： " + ExceptionUtil.exception2String(e));
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object saveContent(TbContent content) {
        try {
            mContentService.saveContent(content);
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, "添加内容失败！" + ExceptionUtil.exception2String(e));
        }
    }


    @RequestMapping("/category/list")
    @ResponseBody
    public Object categoryList(@RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
        try {
            List<EasyUITreeNode> conCateListByParentId = mContentCategoryService.findConCateListByParentId(id);
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(conCateListByParentId);
            return mappingJacksonValue;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, "内容种类查询错误!");
        }
    }

    @RequestMapping("/category/create")
    @ResponseBody
    public Object createContentCategory(Long parentId, String name) {
        try {
            Long id = mContentCategoryService.createContentCategory(parentId, name);
            return TaotaoResult.ok(id);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, "创建失败!");
        }
    }

    @RequestMapping("/category/update")
    @ResponseBody
    public Object updateContentCategory(Long id, String name) {
        try {
            mContentCategoryService.updateContentCategory(id, name);
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, "修改失败!");
        }
    }

    @RequestMapping("/category/delete")
    @ResponseBody
    public Object deleteContentCategory(Long id) {
        try {
            mContentCategoryService.deleteContentCategory(id);
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            String s = ExceptionUtil.exception2String(e);
            return TaotaoResult.build(-1, "删除失败\r\n" + s);
        }
    }


}
