package com.allqj.virtual_number_administrate.business.service;

import com.allqj.virtual_number_administrate.business.vo.*;

import java.util.List;


/**
 * 部门管理
 */
public interface IDeptService {

    /**
     * 列表查询
     *
     * @param deptPageRequest 查询条件
     * @param pageInfo        分页信息
     * @return
     */
    PageResult<DeptPageResult> page(DeptPageRequest deptPageRequest, PageVO pageInfo);

    /**
     * 获取表头
     *
     * @return
     */
    List<PageHeadersResult> pageHeaders();
}
