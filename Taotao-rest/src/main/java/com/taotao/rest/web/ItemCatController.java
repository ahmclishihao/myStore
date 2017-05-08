package com.taotao.rest.web;

import com.taotao.rest.pojo.ItemCatJsonBean;
import com.taotao.rest.service.ItemCatService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/item/cat/")
public class ItemCatController {

    @Resource
    private ItemCatService mItemCatService;

    /**
     * 获取商品类目列表
     * produces -->指定response的请求头
     *
     * @return
     */
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public MappingJacksonValue getItemCatList(String callback) {
        try {
            ItemCatJsonBean itemCatList = mItemCatService.getItemCatList();
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(itemCatList);
            // 设置为jsonP包装
            if (StringUtils.isNotBlank(callback)) {
                mappingJacksonValue.setJsonpFunction(callback);
            }
            return mappingJacksonValue;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String getItemCatList(String callback) {
        try {
            ItemCatJsonBean itemCatList = mItemCatService.getItemCatList();
            String jsonData = JackSonUtil.objectToJson(itemCatList);
            // 支持JSONP
            if(StringUtils.isBlank(callback)) {
                return jsonData;
            }else{
                return callback+"("+jsonData+");";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }*/

}
