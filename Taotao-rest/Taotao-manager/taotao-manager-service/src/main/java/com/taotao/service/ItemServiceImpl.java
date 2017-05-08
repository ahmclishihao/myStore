package com.taotao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIPageBean;
import com.taotao.common.bean.EasyUITreeNode;
import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemMapper itemMapper;

    @Resource
    private TbItemDescMapper itemDescMapper;

    @Resource
    private TbItemCatMapper itemCatMapper;

    @Resource
    private TbItemParamItemMapper mItemParamItemMapper;

    /**
     * 使用分页插件获取分页
     */
    @Override
    public EasyUIPageBean<TbItem> getItemPage(Integer pageNum, Integer pageSize) {
        // 1.开启分页将对下一次查询有效
        PageHelper.startPage(pageNum, pageSize);

        // 2.使用example进行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> dataList = itemMapper.selectByExample(example);

        // 3.进行分页包装
        PageInfo<TbItem> pageInfo = new PageInfo<>(dataList);
        long total = pageInfo.getTotal();
        EasyUIPageBean<TbItem> easyUIPageBean = new EasyUIPageBean<>();
        easyUIPageBean.setTotal(total);
        easyUIPageBean.setRows(pageInfo.getList());
        return easyUIPageBean;
    }

    /**
     * 根据parentId从数据库中获取对应的节点列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITreeNode> getCatList(Integer parentId) {
        // 1.查询对应parentId的节点
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId.longValue());
        List<TbItemCat> tbItemCats =
                itemCatMapper.selectByExample(example);
        // 2.转换为前台数据
        List<EasyUITreeNode> treeNodes = new ArrayList<>();
        EasyUITreeNode treeNode = null;
        for (TbItemCat tbItemCat : tbItemCats) {
            treeNode = new EasyUITreeNode();
            treeNode.setId(tbItemCat.getId());
            treeNode.setText(tbItemCat.getName());
            treeNode.setState(tbItemCat.getIsParent());
            treeNodes.add(treeNode);
        }

        return treeNodes;
    }

    /**
     * 添加商品
     *
     * @param item
     * @param desc
     * @throws Exception
     */
    @Override
    public void saveItem(TbItem item, String desc,String itemParamsJson) throws Exception {
        Date date = new Date();
        // 随机生成编号
        long randomId = IDUtils.genItemId();
        item.setId(randomId);
        item.setStatus((byte) 1);
        item.setCreated(date);
        item.setUpdated(date);
        itemMapper.insert(item);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(randomId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        itemDescMapper.insert(tbItemDesc);

        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(randomId);
        itemParamItem.setParamData(itemParamsJson);
        itemParamItem.setUpdated(date);
        itemParamItem.setCreated(date);
        mItemParamItemMapper.insert(itemParamItem);

    }

}
