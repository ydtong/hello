package com.allqj.virtual_number_administrate.business.vo;


import java.util.List;

public class PageVO {
    private Integer page = 1;
    private Integer size = 10;
    private List<SortVO> sortVO;

    public PageVO() {
    }

    public PageVO(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public PageVO(Integer page, Integer size, List<SortVO> sortVO) {
        this.page = page;
        this.size = size;
        this.sortVO = sortVO;
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getSize() {
        return this.size;
    }

    public List<SortVO> getSortVO() {
        return this.sortVO;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSortVO(List<SortVO> sortVO) {
        this.sortVO = sortVO;
    }
}
