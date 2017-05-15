package com.taotao.rest.service;

import com.taotao.common.util.JackSonUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.jedis.JedisGlobalClient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper mContentMapper;

    @Autowired
    private JedisGlobalClient mJedisGlobalClient;

    @Value("${redis.content_key}")
    private String REDIS_CONTENT_KEY;

    @Override
    public List<TbContent> getContentList(Long categoryId) {

        // 1.从redis中取出数据
        try {
            String jsonData = mJedisGlobalClient.hget(REDIS_CONTENT_KEY, categoryId.toString());
            if (StringUtils.isNotBlank(jsonData))
                return JackSonUtil.jsonToList(jsonData, TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2.查询数据库中的数据
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = mContentMapper.selectByExample(example);

        // 3.缓存到Redis中去
        try {
            mJedisGlobalClient.hset(REDIS_CONTENT_KEY, categoryId.toString(), JackSonUtil.objectToJson(tbContents));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbContents;
    }

}
