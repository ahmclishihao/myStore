package com.taotao.rest.web;

import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.bean.TaotaoResult;
import com.taotao.rest.jedis.JedisGlobalClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private JedisGlobalClient mJedisGlobalClient;

    @Value("${redis.content_key}")
    private String CONTENT_KEY;

    @Value("${redis.item_category_key}")
    private String REDIS_ITEM_CATEGORY_KEY;

    @RequestMapping("/content/sync/{categoryId}")
    @ResponseBody
    public Object contentSync(@PathVariable Long categoryId){
        try {
            Long del = mJedisGlobalClient.hdel(CONTENT_KEY,categoryId+"");
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1,"同步失败 ! "+ ExceptionUtil.exception2String(e));
        }
    }

    @RequestMapping("/contentcategory/sync")
    @ResponseBody
    public Object contentCategorySync(){

        try {
            mJedisGlobalClient.del(REDIS_ITEM_CATEGORY_KEY);
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1,"同步失败 ! "+ ExceptionUtil.exception2String(e));
        }
    }

}
