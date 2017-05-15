package com.taotao.portal.pojo;


import java.util.List;

public class SolrInfoResult {

    private List<SolrItemInfo> recordList; // 获取的数据
    private Long recordTotal; // 总记录数
    private int pageTotal; // 总页数
    private int currentPage; // 当前页
    private int currentRows; // 当前行数

    public List<SolrItemInfo> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<SolrItemInfo> recordList) {
        this.recordList = recordList;
    }

    public Long getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(Long recordTotal) {
        this.recordTotal = recordTotal;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentRows() {
        return currentRows;
    }

    public void setCurrentRows(int currentRows) {
        this.currentRows = currentRows;
    }
}
