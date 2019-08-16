package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.service.IDeptService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    @Qualifier("deptPageFromMysqlBaseServiceImpl")
    private IPageBaseService<DeptPageRequest, PageResult<DeptPageResult>> deptPageFromMysqlBaseServiceImpl;

    @Override
    public PageResult<DeptPageResult> page(DeptPageRequest deptPageRequest, PageVO pageInfo) {
        return deptPageFromMysqlBaseServiceImpl.page(deptPageRequest, pageInfo);
    }

    @Override
    public List<PageHeadersResult> pageHeaders() {
        List<PageHeadersResult> result = HeadersUtil.getHeaders(DeptPageResult.class);
        return result;
    }
}
