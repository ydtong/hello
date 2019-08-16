package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberSubPageService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 虚拟号子列表
 */
@Service
public class LongVirtualNumberFreeSubPageServiceImpl implements IVirtualNumberSubPageService<LongVirtualNumberFreeSubPageRequest, LongVirtualNumberFreeSubPageResult> {

    @Autowired
    @Qualifier("longVirtualNumberFreeSubPageFromMysqlBaseServiceImpl")
    private IPageBaseService<LongVirtualNumberFreeSubPageRequest, PageResult<LongVirtualNumberFreeSubPageResult>> longVirtualNumberFreeSubPageFromMysqlBaseServiceImpl;

    /**
     * 长虚拟号空闲子列表
     *
     * @param request  查询条件
     * @param pageInfo 分页信息
     * @return
     */
    @Override
    public PageResult<LongVirtualNumberFreeSubPageResult> subPage(LongVirtualNumberFreeSubPageRequest request, PageVO pageInfo) {
        return longVirtualNumberFreeSubPageFromMysqlBaseServiceImpl.page(request, pageInfo);
    }

    @Override
    public List<PageHeadersResult> pageHeaders() {
        return HeadersUtil.getHeaders(LongVirtualNumberFreeSubPageResult.class);
    }
}
