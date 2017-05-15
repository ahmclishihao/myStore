package com.taotao.search.service;

import com.taotao.search.dao.ItemInfoMapper;
import com.taotao.search.dao.SolrQueryDao;
import com.taotao.search.pojo.SolrItemInfo;
import com.taotao.search.pojo.SolrInfoResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

@Service
public class ItemInfoServiceImpl implements ItemInfoService {

    @Resource
    private ItemInfoMapper mItemInfoMapper;

    @Resource(name = "solrQueryDaoImpl")
    private SolrQueryDao mSolrQueryDao;

    @Resource
    private SolrServer mSolrServer;

    @Override
    public void resolveItemInfoToSolr() throws Exception {
        List<SolrItemInfo> solrItemInfos = mItemInfoMapper.selectItemInfo();
        for (SolrItemInfo solrItemInfo : solrItemInfos) {
            SolrInputDocument solrInputFields = new SolrInputDocument();
            solrInputFields.setField("id", solrItemInfo.getId());
            solrInputFields.addField("item_title", solrItemInfo.getItem_title());
            solrInputFields.addField("item_sell_point", solrItemInfo.getItem_sell_point());
            solrInputFields.addField("item_price", solrItemInfo.getItem_price());
            solrInputFields.addField("item_desc", solrItemInfo.getItem_desc());
            solrInputFields.addField("item_category_name", solrItemInfo.getItem_category_name());
            solrInputFields.addField("item_image", solrItemInfo.getItem_image());
            mSolrServer.add(solrInputFields);
        }
        mSolrServer.commit();
    }

    @Override
    public SolrInfoResult selectBySolrQuery(String keywords, Integer pageNum, Integer rows) throws Exception {
        if (StringUtils.isBlank(keywords)) {
            return null;
        }
        //1.配置查询条件
        SolrQuery solrQuery = new SolrQuery();
        //  1. 设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
        solrQuery.setHighlightSimplePost("</font>");
        //  2. 设置查询个数
        solrQuery.setStart((pageNum - 1) * rows);
        solrQuery.setRows(rows);
        //  3.设置查询条件和默认搜索域
        solrQuery.set("df", "item_title");
        solrQuery.setQuery(keywords);

        //2.查询
        SolrInfoResult solrInfoResult = mSolrQueryDao.selectBySolrQuery(solrQuery);
        //3.结果中的参数
        solrInfoResult.setCurrentPage(pageNum);
        solrInfoResult.setCurrentRows(rows);

        return solrInfoResult;
    }

    @Override
    public void insertToSolr(Long itemId) throws IOException, SolrServerException {
        // 1.从数据库中查询出数据
        SolrItemInfo solrItemInfo = mItemInfoMapper.findSolrItemInfoById(itemId);
        // 2.转换到solr中
        SolrInputDocument solrInputFields = new SolrInputDocument();
        solrInputFields.addField("id", solrItemInfo.getId());
        solrInputFields.addField("item_category_name", solrItemInfo.getItem_category_name());
        solrInputFields.addField("item_image", solrItemInfo.getItem_image());
        solrInputFields.addField("item_desc", solrItemInfo.getItem_desc());
        solrInputFields.addField("item_price", solrItemInfo.getItem_price());
        solrInputFields.addField("item_sell_point", solrItemInfo.getItem_sell_point());
        solrInputFields.addField("item_title", solrItemInfo.getItem_title());
        mSolrServer.add(solrInputFields);

        mSolrServer.commit();
    }


}
