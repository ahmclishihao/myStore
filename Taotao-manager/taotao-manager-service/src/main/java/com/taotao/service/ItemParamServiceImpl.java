package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIPageBean;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExt;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Resource
    private TbItemParamMapper mItemParamMapper;
    @Resource
    private TbItemParamItemMapper mItemParamItemMapper;

    @Override
    public EasyUIPageBean<TbItemParamExt> findPageList(int pageNum, int rows) throws Exception {
        // 分页插件
        PageHelper.startPage(pageNum, rows);
        // 查询
        List<TbItemParamExt> tbItemParams = mItemParamMapper.selectWithBLOBsAndItemCatName(new TbItemParamExample());
        PageInfo<TbItemParamExt> pageInfo = new PageInfo<>(tbItemParams);
        // 包装结果
        EasyUIPageBean<TbItemParamExt> pageBean = new EasyUIPageBean<>();
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setRows(pageInfo.getList());
        return pageBean;
    }

    /**
     * 根据商品类目id查询对应规格参数
     *
     * @param catid
     * @return
     */
    @Override
    public TbItemParam selectByCatId(Long catid) {
        TbItemParam itemParam = null;
        try {
            TbItemParamExample example = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = example.createCriteria();
            criteria.andItemCatIdEqualTo(catid);
            List<TbItemParam> tbItemParams = mItemParamMapper.selectByExampleWithBLOBs(example);
            itemParam = tbItemParams.size() == 1 ? tbItemParams.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemParam;
    }

    /**
     * 为对应的商品类目生成对应的规格参数模板
     *
     * @param catid
     * @param paramData
     */
    @Override
    public void saveByCatId(Long catid, String paramData) throws Exception {
        Date date = new Date();
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(catid);
        itemParam.setParamData(paramData);
        itemParam.setCreated(date);
        itemParam.setUpdated(date);
        mItemParamMapper.insert(itemParam);
    }

    /**
     * 批量删除已经规格参数
     *
     * @param ids
     */
    @Override
    public void deleteByIds(Long[] ids) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        mItemParamMapper.deleteByExample(example);
    }

    /**
     * 根据商品ID查找对应的规格参数
     *
     * @param id
     * @return
     */
    @Override
    public String getItemParamsByItemId(Long id) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(id);
        List<TbItemParamItem> itemParamItems = mItemParamItemMapper.selectByExampleWithBLOBs(example);
        return itemParamItems.size() == 1 ? itemParamItems.get(0).getParamData() : null;
    }
}
