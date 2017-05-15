package com.taotao.service;

import com.taotao.common.bean.EasyUIPageBean;
import com.taotao.pojo.TbContent;

public interface ContentService {

    EasyUIPageBean getContentListByCategoryId(Long categoryId, Integer pageNum, Integer rows) throws Exception;

    void saveContent(TbContent content) throws Exception;
}
