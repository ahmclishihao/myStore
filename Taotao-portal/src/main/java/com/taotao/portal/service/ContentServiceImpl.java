package com.taotao.portal.service;

import com.taotao.common.util.HttpUtils;
import com.taotao.common.util.JackSonUtil;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AdPicJsonBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Value("${restAddress}")
    private String REST_ADDRESS;

    @Value("${slideBannerPath}")
    private String SLIDE_BANNER_PATH;

    @Value("${slideBannerContentCategoryId}")
    private String SLIDE_BANNER_CONTENT_CATEGORY_ID;

    @Override
    public String getIndexSlideBannerInfos() {
        String jsonData = HttpUtils.GET(REST_ADDRESS + SLIDE_BANNER_PATH + SLIDE_BANNER_CONTENT_CATEGORY_ID);
        List<TbContent> tbContents = JackSonUtil.jsonToList(jsonData, TbContent.class);
        List<AdPicJsonBean> adPicJsonBeans = new ArrayList<>();
        for (TbContent tbContent : tbContents) {
            AdPicJsonBean adPicJsonBean = new AdPicJsonBean();

            adPicJsonBean.setAlt(tbContent.getTitleDesc());
            adPicJsonBean.setHeight(240);
            adPicJsonBean.setHeightB(240);
            adPicJsonBean.setHref(tbContent.getUrl());
            adPicJsonBean.setSrc(tbContent.getPic());
            adPicJsonBean.setSrcB(tbContent.getPic2());
            adPicJsonBean.setWidth(670);
            adPicJsonBean.setWidthB(550);

            adPicJsonBeans.add(adPicJsonBean);
        }

        return JackSonUtil.objectToJson(adPicJsonBeans);
    }
}
