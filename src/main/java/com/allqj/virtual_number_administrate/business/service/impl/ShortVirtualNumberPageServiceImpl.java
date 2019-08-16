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
public class ShortVirtualNumberPageServiceImpl implements IVirtualNumberPageService<ShortVirtualNumberPageRequest, ShortVirtualNumberPageResult> {

    @Autowired
    @Qualifier("shortVirtualNumberPageFromMysqlBaseServiceImpl")
    private IPageBaseService<ShortVirtualNumberPageRequest, PageResult<ShortVirtualNumberPageResult>> shortVirtualNumberPageFromMysqlBaseServiceImpl;

    /**
     * 短隐号列表
     *
     * @param shortVirtualNumberPageRequest 查询条件
     * @param pageInfo                      分页信息
     * @return
     */
    @Override
    public PageResult<ShortVirtualNumberPageResult> page(ShortVirtualNumberPageRequest shortVirtualNumberPageRequest, PageVO pageInfo) {
        return shortVirtualNumberPageFromMysqlBaseServiceImpl.page(shortVirtualNumberPageRequest, pageInfo);
    }

    /**
     * 短隐号表头
     *
     * @return
     */
    @Override
    public List<PageHeadersResult> pageHeaders() {
        return HeadersUtil.getHeaders(ShortVirtualNumberPageResult.class);
    }

}
