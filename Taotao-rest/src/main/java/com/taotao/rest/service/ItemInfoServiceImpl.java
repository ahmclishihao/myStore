package com.taotao.rest.service;

import com.taotao.common.util.JackSonUtil;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.jedis.JedisGlobalClient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class ItemInfoServiceImpl implements ItemInfoService {

    @Resource
    private TbItemMapper mItemMapper;
    @Resource
    private TbItemDescMapper mItemDescMapper;
    @Resource
    private TbItemParamItemMapper mItemParamItemMapper;
    @Resource
    private JedisGlobalClient jedisGlobalClient;

    // 商品信息key
    @Value("${redis.item_info_key}")
    private String REDIS_ITEM_INFO_KEY;
    // 商品itemkey
    @Value("${redis.item_key}")
    private String ITEM_KEY;
    // 商品描述key
    @Value("${redis.desc_key}")
    private String ITEM_DESC_KEY;
    // 商品参数key
    @Value("${redis.params_key}")
    private String ITEM_PARAMS_KEY;
    // 商品信息的过期时间 (秒)
    @Value("${redis.item_expire_time}")
    private Long ITEM_EXPIRE_TIME;

    @Override
    public TbItem getItemById(Long id) throws Exception {
        // 从redis中获取数据
        try {
            String jsonData = jedisGlobalClient.get(REDIS_ITEM_INFO_KEY + ":" + id + ":" + ITEM_KEY);
            if (StringUtils.isNotBlank(jsonData)) {
                TbItem tbItem = JackSonUtil.jsonToPojo(jsonData, TbItem.class);
                return tbItem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 查询数据
        TbItem tbItem = mItemMapper.selectByPrimaryKey(id);


        // 将数据存储到redis中
        try {
            jedisGlobalClient.set(REDIS_ITEM_INFO_KEY + ":" + id + ":" + ITEM_KEY, JackSonUtil.objectToJson(tbItem));
            jedisGlobalClient.expire(REDIS_ITEM_INFO_KEY + ":" + id + ":" + ITEM_KEY, ITEM_EXPIRE_TIME.intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbItem;
    }

    @Override
    public TbItemDesc getDescByItemId(Long id) throws Exception {
        // 从redis中获取数据
        try {
            String jsonData = jedisGlobalClient.get(REDIS_ITEM_INFO_KEY + ":" + id + ":" + ITEM_DESC_KEY);
            if (StringUtils.isNotBlank(jsonData)) {
                TbItemDesc tbItemDesc = JackSonUtil.jsonToPojo(jsonData, TbItemDesc.class);
                return tbItemDesc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 查询数据
        TbItemDesc tbItemDesc = mItemDescMapper.selectByPrimaryKey(id);


        // 将数据存储到redis中
        try {
            jedisGlobalClient.set(REDIS_ITEM_INFO_KEY + ":" + id + ":" + ITEM_DESC_KEY, JackSonUtil.objectToJson(tbItemDesc));
            jedisGlobalClient.expire(REDIS_ITEM_INFO_KEY + ":" + id + ":" + ITEM_DESC_KEY, ITEM_EXPIRE_TIME.intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbItemDesc;
    }

    @Override
    public TbItemParamItem getItemParamByItemId(Long id) throws Exception {
        // 从redis中获取数据
        try {
            String jsonData = jedisGlobalClient.get(REDIS_ITEM_INFO_KEY + ":" + id + ":" + ITEM_PARAMS_KEY);
            if (StringUtils.isNotBlank(jsonData)) {
                TbItemParamItem tbItemParamItem = JackSonUtil.jsonToPojo(jsonData, TbItemParamItem.class);
                return tbItemParamItem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 查询数据
        TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = tbItemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(id);
        List<TbItemParamItem> tbItemParamItems = mItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);

        if (tbItemParamItems != null && tbItemParamItems.size() > 0) {
            TbItemParamItem itemParamItem = tbItemParamItems.get(0);
            // 将数据存储到redis中
            try {
                jedisGlobalClient.set(REDIS_ITEM_INFO_KEY + ":" + id + ":" + ITEM_PARAMS_KEY, JackSonUtil.objectToJson(itemParamItem));
                jedisGlobalClient.expire(REDIS_ITEM_INFO_KEY + ":" + id + ":" + ITEM_PARAMS_KEY, ITEM_EXPIRE_TIME.intValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return itemParamItem;
        }
        return null;
    }

}
