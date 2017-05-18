package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.pojo.TbItemExt;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class FreeMarkerServiceImpl implements FreeMarkerService {

    @Resource
    private ItemService mItemService;

    @Resource
    private Configuration mConfiguration;

    @Override
    public TaotaoResult generateItemHtml(Long itemId) throws Exception {
        TbItemExt item = mItemService.getItemById(itemId);
        TbItemDesc itemDesc = mItemService.getItemDescById(itemId);
        String itemParams = mItemService.getItemParamsById(itemId);

        Template template = mConfiguration.getTemplate("item.ftl");
        String path = "H:\\temp\\" + itemId + ".html";
        Writer out = new FileWriter(new File(path));

        Map<Object, Object> dataModel = new HashMap<>();
        dataModel.put("item",item);
        dataModel.put("itemDesc",itemDesc.getItemDesc());
        dataModel.put("itemParams",itemParams);

        template.process(dataModel, out);

        out.flush();
        out.close();

        return TaotaoResult.ok(path);
    }
}
