package com.allqj.virtual_number_administrate.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页统一结果
 *
 * @param <T>
 */
@Getter
@Setter
public class PageResult<T> {
    @ApiModelProperty(value = "数据")
    private List<T> content;

    @ApiModelProperty(value = "当前的页数")
    private Integer page;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "一共有多少条数据")
    private Integer count;

    @ApiModelProperty(value = "一共有多少页")
    private Integer pages;

    public PageResult() {
        content = null;
        page = 0;
        pageSize = 0;
        count = 0;
        pages = 0;
    }

    public PageResult(List<T> content, Integer page, Integer pageSize, Integer count, Integer pages) {
        set(content, page, pageSize, count, pages);
    }

    public void set(List<T> content, Integer page, Integer pageSize, Integer count, Integer pages) {
        this.content = content;
        this.page = page;
        this.pageSize = pageSize;
        this.count = count;
        this.pages = pages;
    }
}
