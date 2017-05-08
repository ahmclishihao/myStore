package com.taotao.service;

import com.taotao.common.bean.EasyUIPageBean;
import com.taotao.common.bean.EasyUITreeNode;
import com.taotao.common.bean.TaotaoResult;
import com.taotao.pojo.TbItem;

import java.util.List;

public interface ItemService {

    EasyUIPageBean<TbItem> getItemPage(Integer pageNum, Integer pageSize) throws Exception;

    List<EasyUITreeNode> getCatList(Integer parentId) throws Exception;

    void saveItem(TbItem item, String desc,String itemParamsJson) throws Exception;
}
