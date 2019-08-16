package com.allqj.virtual_number_administrate.business.service;

import com.allqj.virtual_number_administrate.business.vo.*;

import java.util.List;

/**
 * 虚拟号历史
 */
public interface IVirtualNumberHistoryService<Request, Result> {

    /**
     * 虚拟号历史分页查询
     *
     * @param request
     * @param pageable
     * @return
     */
    PageResult<Result> page(Request request, PageVO pageable);

    /**
     * 获取表头
     *
     * @return
     */
    List<PageHeadersResult> pageHeaders();
}
