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
public class LongVirtualNumberBindingSubPageServiceImpl implements IVirtualNumberSubPageService<LongVirtualNumberBindingSubPageRequest, LongVirtualNumberBindingSubPageResult> {

    @Autowired
    @Qualifier("longVirtualNumberBindingSubPageFromMysqlBaseServiceImpl")
    private IPageBaseService<LongVirtualNumberBindingSubPageRequest, PageResult<LongVirtualNumberBindingSubPageResult>> longVirtualNumberBindingSubPageFromMysqlBaseServiceImpl;

    /**
     * 长虚拟号绑定子列表
     *
     * @param request  查询条件
     * @param pageInfo 分页信息
     * @return
     */
    @Override
    public PageResult<LongVirtualNumberBindingSubPageResult> subPage(LongVirtualNumberBindingSubPageRequest request, PageVO pageInfo) {

        return longVirtualNumberBindingSubPageFromMysqlBaseServiceImpl.page(request, pageInfo);
    }

    @Override
    public List<PageHeadersResult> pageHeaders() {
        return HeadersUtil.getHeaders(LongVirtualNumberBindingSubPageResult.class);
    }
}
