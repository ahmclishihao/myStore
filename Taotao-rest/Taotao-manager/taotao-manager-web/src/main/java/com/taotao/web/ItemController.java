package com.taotao.web;

import com.taotao.common.bean.EasyUIPageBean;
import com.taotao.common.bean.EasyUITreeNode;
import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.JackSonUtil;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Resource
    private ItemService itemService;
    @Resource
    private ItemParamService itemParamService;

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIPageBean<TbItem> getItemList(Integer page, Integer rows) {
        EasyUIPageBean<TbItem> itemPage = null;
        try {
            itemPage = itemService.getItemPage(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemPage;
    }

    @RequestMapping("/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getCatList(@RequestParam(value = "id", defaultValue = "0") Integer parentId) {
        List<EasyUITreeNode> treeNodes = null;
        try {
            treeNodes = itemService.getCatList(parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeNodes;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveItem(TbItem item, String desc, @RequestParam("itemParams") String itemParamsJson) {
        try {
            itemService.saveItem(item, desc, itemParamsJson);
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, "添加商品失败!");
        }
    }

    @RequestMapping("/itemparams/{itemid}")
    @ResponseBody
    public String getItemParamsByItemId(@PathVariable("itemid") Long id) {
        try {
            String itemParamItem = itemParamService.getItemParamsByItemId(id);
            if (StringUtils.isNotEmpty(itemParamItem)) {
                List<Map> maps = JackSonUtil.jsonToList(itemParamItem, Map.class);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("<table style=\"width:100%;\"cellpadding=\"2\"cellspacing=\"0\"border=\"1\"bordercolor=\"#000000\">");
                stringBuffer.append("<tbody>");
                for (Map map : maps) {
                    String group = (String) map.get("group");
                    List<Map> params = (List<Map>) map.get("params");
                    Map param = params.get(0);
                    stringBuffer.append("<tr><td rowspan='" + params.size() + "'>" + group + "</td><td>" + param.get("k") + "</td>" + "<td>" + param.get("v") + "</td></tr>");
                    for (int i = 1; i < params.size(); i++) {
                        param = params.get(i);
                        stringBuffer.append("<tr><td>" + param.get("k") + "</td>" + "<td>" + param.get("v") + "</td></tr>");
                    }
                }
                stringBuffer.append("</tbody>");
                stringBuffer.append("</table>");
                return stringBuffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
