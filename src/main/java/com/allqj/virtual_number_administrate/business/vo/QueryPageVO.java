package com.allqj.virtual_number_administrate.business.vo;


public class QueryPageVO<T> {
    private T query;
    private PageVO pageInfo;

    public QueryPageVO() {
    }

    public QueryPageVO(T query, PageVO pageInfo) {
        this.query = query;
        this.pageInfo = pageInfo;
    }

    public QueryPageVO(T query, Integer page, Integer size) {
        this.query = query;
        this.pageInfo = new PageVO(page, size);
    }

    public QueryPageVO(Integer page, Integer size) {
        this.pageInfo = new PageVO(page, size);
    }

    public T getQuery() {
        return this.query;
    }

    public PageVO getPageInfo() {
        return this.pageInfo;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    public void setPageInfo(PageVO pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Integer page() {
        return this.pageInfo == null ? 1 : this.pageInfo.getPage();
    }

    public Integer size() {
        return this.pageInfo == null ? 10 : this.pageInfo.getSize();
    }
}
