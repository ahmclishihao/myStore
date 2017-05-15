package com.taotao.search.dao;

import com.taotao.search.pojo.SolrInfoResult;
import com.taotao.search.pojo.SolrItemInfo;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Repository
public class SolrQueryDaoImpl implements SolrQueryDao {

    @Resource
    private SolrServer mSolrServer;

    @Override
    public SolrInfoResult selectBySolrQuery(SolrQuery solrQuery) throws Exception {
        // 1. 根据条件查询
        QueryResponse query =
                mSolrServer.query(solrQuery);

        // 2. 获取转换结果
        SolrDocumentList results = query.getResults();
        // 3.获取高亮
        Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();

        List<SolrItemInfo> solrItemInfos = new ArrayList<>();
        for (SolrDocument result : results) {
            SolrItemInfo solrItemInfo = new SolrItemInfo();
            solrItemInfo.setId((String) result.get("id"));
            solrItemInfo.setItem_category_name((String) result.get("item_category_name"));
            solrItemInfo.setItem_image((String) result.get("item_image"));
            solrItemInfo.setItem_price((Long) result.get("item_price"));
            solrItemInfo.setItem_sell_point((String) result.get("item_sell_point"));

            // 获取高亮的title值
            Map<String, List<String>> stringListMap = highlighting.get(solrItemInfo.getId());
            if (stringListMap != null && stringListMap.size() > 0) {
                List<String> item_title = stringListMap.get("item_title");
                if (item_title != null && item_title.size() > 0)
                    solrItemInfo.setItem_title(item_title.get(0));
            } else
                solrItemInfo.setItem_title((String) result.get("item_title"));

            solrItemInfos.add(solrItemInfo);
        }

        // 4. 计算solrResult中的数据
        long numFound = results.getNumFound();
        Integer rows = solrQuery.getRows();
        // 5. 封装solrResult中的数据
        SolrInfoResult<SolrItemInfo> solrInfoResult = new SolrInfoResult<>();
        solrInfoResult.setRecordList(solrItemInfos);
        solrInfoResult.setRecordTotal(numFound);
        solrInfoResult.setPageTotal((int) ((numFound + rows - 1) / rows));

        return solrInfoResult;
    }

}
