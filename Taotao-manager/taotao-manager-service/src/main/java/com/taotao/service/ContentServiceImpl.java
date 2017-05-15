package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIPageBean;
import com.taotao.common.util.HttpUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentExample;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private TbContentMapper mContentMapper;

    @Override
    public EasyUIPageBean getContentListByCategoryId(Long categoryId, Integer pageNum, Integer rows) {

        PageHelper.startPage(pageNum,rows);

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        // 有多页时，返回的是Page
        List<TbContent> tbContents = mContentMapper.selectByExample(example);

        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
        EasyUIPageBean<TbContent> pageBean = new EasyUIPageBean<>();
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setRows(pageInfo.getList());

        return pageBean;
    }

    @Override
    public void saveContent(TbContent content) throws Exception {
        Date date = new Date();
        content.setCreated(date);
        content.setUpdated(date);
        mContentMapper.insert(content);
        // 同步redis数据
        HttpUtils.GET("http://localhost:8081/rest/redis/content/sync/"+content.getCategoryId());

    }
}
