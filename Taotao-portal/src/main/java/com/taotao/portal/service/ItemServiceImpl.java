package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.HttpUtils;
import com.taotao.common.util.JackSonUtil;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.SolrInfoResult;
import com.taotao.portal.pojo.SolrItemInfo;
import com.taotao.portal.pojo.TbItemExt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    //搜索服务的地址
    @Value("${search_server_address}")
    private String SEARCH_SERVER_ADDRESS;
    // rest服务器地址
    @Value("${restAddress}")
    private String restAddress;
    // 商品item数据的获取
    @Value("${rest_item}")
    private String rest_item;
    // 商品描述的获取
    @Value("${rest_desc}")
    private String rest_desc;
    // 商品参数的获取
    @Value("${rest_param}")
    private String rest_param;


    @Override
    public SolrInfoResult getSolrInfoResult(String keywords, Integer pageNum, Integer rows) {

        Map<String, Object> params = new HashMap<>();
        params.put("keywords", keywords);
        params.put("pageNum", pageNum);
        params.put("rows", rows);

        String jsonData = HttpUtils.GET(SEARCH_SERVER_ADDRESS, params);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(jsonData, SolrInfoResult.class);
        return (SolrInfoResult) taotaoResult.getData();
    }

    @Override
    public TbItemExt getItemById(Long id) throws Exception {
        String jsonData = HttpUtils.GET(restAddress + rest_item + id);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(jsonData, TbItemExt.class);
        TbItemExt data = (TbItemExt) taotaoResult.getData();
        return data;
    }
    @Override
    public TbItemDesc getItemDescById(Long id) throws Exception {
        String jsonData = HttpUtils.GET(restAddress + rest_desc + id);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(jsonData, TbItemDesc.class);

        TbItemDesc data = (TbItemDesc) taotaoResult.getData();
        return data;
    }
    @Override
    public String getItemParamsById(Long id) throws Exception {
        String jsonData = HttpUtils.GET(restAddress + rest_param + id);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(jsonData, TbItemParamItem.class);
        TbItemParamItem data = (TbItemParamItem) taotaoResult.getData();
        if (data != null) {
            String paramData = data.getParamData();
            if (StringUtils.isNotEmpty(paramData)) {
                List<Map> maps = JackSonUtil.jsonToList(paramData, Map.class);
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
        }
        return "";
    }

}
