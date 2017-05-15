package com.taotao.rest.service;

import com.taotao.common.util.JackSonUtil;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.jedis.JedisGlobalClient;
import com.taotao.rest.pojo.ItemCatJsonBean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * 商品类目参数的服务类
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private TbItemCatMapper mItemCatMapper;
    @Resource
    private JedisGlobalClient mJedisGlobalClient;

    @Value("${redis.item_category_key}")
    private String REDIS_ITEM_CATEGORY_KEY;

    @Override
    public ItemCatJsonBean getItemCatList() {
        // 1.从redis中取出数据
        try {
            String jsonData = mJedisGlobalClient.get(REDIS_ITEM_CATEGORY_KEY);
            if (StringUtils.isNotBlank(jsonData))
                return JackSonUtil.jsonToPojo(jsonData,ItemCatJsonBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 递归调取商品种类数据
        List<Object> itemCatList = getItemCatListByParentId(0l);
        ItemCatJsonBean itemCatJsonBean = new ItemCatJsonBean();
        itemCatJsonBean.setData(itemCatList);

        // 3.缓存到Redis中去
        try {
            mJedisGlobalClient.set(REDIS_ITEM_CATEGORY_KEY, JackSonUtil.objectToJson(itemCatJsonBean));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemCatJsonBean;
    }

    /**
     * 递归获取列表数据
     *
     * @param parentId
     * @return
     */
    public List<Object> getItemCatListByParentId(Long parentId) {
        // 查询获取对应父节点为parentId的数据列表
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = mItemCatMapper.selectByExample(tbItemCatExample);

        ArrayList<Object> retList = new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCats) {

            // 父节点时需要继续递归获取items
            if (tbItemCat.getIsParent()) {
                ItemCatJsonBean itemCatJsonBean = new ItemCatJsonBean();
                itemCatJsonBean.setUrl("/products/" + tbItemCat.getId() + ".html");
                // 首节点需要特殊处理
                if (parentId == 0l)
                    itemCatJsonBean.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                else
                    itemCatJsonBean.setName(tbItemCat.getName());
                itemCatJsonBean.setItems(getItemCatListByParentId(tbItemCat.getId()));
                retList.add(itemCatJsonBean);
            } else {
                // 子节点时直接添加
                retList.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
            }
        }
        return retList;
    }

}
