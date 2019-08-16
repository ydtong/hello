package com.allqj.virtual_number_administrate.business.service;

import com.allqj.virtual_number_administrate.business.vo.*;

import java.util.List;


/**
 * 虚拟号子列表
 */
public interface IVirtualNumberSubPageService<Request, Result> {
    /**
     * 列表查询
     *
     * @param request  查询条件
     * @param pageInfo 分页信息
     * @return
     */
    PageResult<Result> subPage(Request request, PageVO pageInfo);

    /**
     * 获取表头
     *
     * @return
     */
    List<PageHeadersResult> pageHeaders();
}
