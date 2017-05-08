package com.taotao.service;


import com.taotao.common.bean.EasyUIPageBean;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExt;
import com.taotao.pojo.TbItemParamItem;

public interface ItemParamService {

    EasyUIPageBean<TbItemParamExt> findPageList(int pageNum, int rows) throws Exception;

    TbItemParam selectByCatId(Long catid) throws Exception;

    void saveByCatId(Long catid, String paramData) throws Exception;

    void deleteByIds(Long[] ids) throws Exception;

    String getItemParamsByItemId(Long id) throws Exception;
}
