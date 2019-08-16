package com.allqj.virtual_number_administrate.business.vo;

public class SortVO {
    private String sort;
    private String sortBy;

    public SortVO(String sort, String sortBy) {
        this.sort = sort;
        this.sortBy = sortBy;
    }

    public SortVO() {
    }

    public String getSort() {
        return this.sort;
    }

    public String getSortBy() {
        return this.sortBy;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
