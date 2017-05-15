package com.taotao.service;

import com.taotao.common.bean.EasyUITreeNode;

import java.util.List;

public interface ContentCategoryService {

    List<EasyUITreeNode> findConCateListByParentId(Long id) throws Exception;

    Long createContentCategory(Long parentId, String name) throws Exception;

    void updateContentCategory(Long id, String name) throws Exception;

    void deleteContentCategory(Long id)  throws Exception;
}
