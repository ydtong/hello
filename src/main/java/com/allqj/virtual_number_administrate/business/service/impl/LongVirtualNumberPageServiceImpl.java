package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberPageService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LongVirtualNumberPageServiceImpl implements IVirtualNumberPageService<LongVirtualNumberPageRequest, LongVirtualNumberPageResult> {

    @Autowired
    @Qualifier("longVirtualNumberPageFromMysqlBaseServiceImpl")
    private IPageBaseService<LongVirtualNumberPageRequest, PageResult<LongVirtualNumberPageResult>> longVirtualNumberPageFromMysqlBaseServiceImpl;

    /**
     * 长隐号列表
     *
     * @param longVirtualNumberPageRequest 查询条件
     * @param pageInfo                     分页信息
     * @return
     */
    @Override
    public PageResult<LongVirtualNumberPageResult> page(LongVirtualNumberPageRequest longVirtualNumberPageRequest, PageVO pageInfo) {
        return longVirtualNumberPageFromMysqlBaseServiceImpl.page(longVirtualNumberPageRequest, pageInfo);
    }

    /**
     * 长隐号表头
     *
     * @return
     */
    @Override
    public List<PageHeadersResult> pageHeaders() {
        return HeadersUtil.getHeaders(LongVirtualNumberPageResult.class);
    }
}
