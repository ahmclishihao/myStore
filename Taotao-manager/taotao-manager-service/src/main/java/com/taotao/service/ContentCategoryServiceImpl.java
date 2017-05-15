package com.taotao.service;

import com.taotao.common.bean.EasyUITreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Resource
    private TbContentCategoryMapper mContentCategoryMapper;

    @Override
    public List<EasyUITreeNode> findConCateListByParentId(Long id) {
        List<TbContentCategory> tbContentCategories = selectByParentId(id);

        List<EasyUITreeNode> treeNodes = new ArrayList<>();
        for (TbContentCategory category : tbContentCategories) {
            EasyUITreeNode treeNode = new EasyUITreeNode();

            treeNode.setId(category.getId());
            treeNode.setText(category.getName());
            //state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
            treeNode.setState(category.getIsParent());
            treeNodes.add(treeNode);
        }

        return treeNodes;
    }


    @Override
    public Long createContentCategory(Long parentId, String name) {
        // 1.插入操作
        Date date = new Date();
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setName(name);
        tbContentCategory.setCreated(date);
        tbContentCategory.setUpdated(date);
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setSortOrder(1);
        // 状态。可选值:1(正常),2(删除)
        tbContentCategory.setStatus(1);
        mContentCategoryMapper.insert(tbContentCategory);

        // 2.设置父项目isParent
        TbContentCategory parentContentCategory = mContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parentContentCategory.getIsParent()) {
            parentContentCategory.setIsParent(true);
            mContentCategoryMapper.updateByPrimaryKey(parentContentCategory);
        }

        return tbContentCategory.getId();
    }

    @Override
    public void updateContentCategory(Long id, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(id);
        tbContentCategory.setName(name);
        mContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }

    @Override
    public void deleteContentCategory(Long id) {
        TbContentCategory self = mContentCategoryMapper.selectByPrimaryKey(id);
        // 1.查看自己是否存在子节点
        if (self.getIsParent())
            deleteChildrenContentCategory(id);
        // 2.删除自己
        mContentCategoryMapper.deleteByPrimaryKey(id);
        // 3.判断父节点是否还有子节点
        List<TbContentCategory> tbContentCategories = selectByParentId(self.getParentId());
        if (tbContentCategories.size() == 0) {
            TbContentCategory tbContentCategory = new TbContentCategory();
            tbContentCategory.setId(self.getParentId());
            tbContentCategory.setIsParent(false);
            mContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
        }
    }

    /**
     * 删除子节点
     *
     * @param id
     */
    public void deleteChildrenContentCategory(Long id) {
        // 1.获取自己的子节点
        List<TbContentCategory> tbContentCategories = selectByParentId(id);
        // 2.遍历删除
        for (TbContentCategory contentCategory : tbContentCategories) {
            // 3.判断自己是否含有子节点，递归删除
            if (contentCategory.getIsParent()) {
                deleteChildrenContentCategory(contentCategory.getId());
            }
            // 4.删除自己
            mContentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
        }
    }


    /**
     * 根据parent_id进行查找
     *
     * @param parentId
     * @return
     */
    public List<TbContentCategory> selectByParentId(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return mContentCategoryMapper.selectByExample(example);
    }

}
