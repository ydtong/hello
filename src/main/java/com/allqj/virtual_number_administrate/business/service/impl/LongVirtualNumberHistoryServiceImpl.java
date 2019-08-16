package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberHistoryService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LongVirtualNumberHistoryServiceImpl implements IVirtualNumberHistoryService<LongVirtualNumberHistoryRequest, LongVirtualNumberHistoryResult> {

    //    @Autowired
//    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;
    @Autowired
    @Qualifier("longVirtualNumberHistoryPageFromMysqlBaseServiceImpl")
    private IPageBaseService<LongVirtualNumberHistoryRequest, PageResult<LongVirtualNumberHistoryResult>> longVirtualNumberHistoryPageServiceImpl;

//    @Override
//    public List<LongVirtualNumberHistoryResult> historyVirtualNumber(LongVirtualNumberHistoryRequest longVirtualNumberHistoryRequest) {
//        List<VirtualNumberBindingInfoMysqlEntity> bindingRelationshipEntities= virtualNumberBindingInfoMysqlRepository.findAllByVirtualNumberAndIsdeleteTrue(longVirtualNumberHistoryRequest.getVirtualNumber());
//        List<LongVirtualNumberHistoryResult> longVirtualNumberHistoryResults=new ArrayList<>();
//        bindingRelationshipEntities.forEach(bindingRelationshipEntity -> {
//            LongVirtualNumberHistoryResult longVirtualNumberHistoryResult=new LongVirtualNumberHistoryResult();
//            BeanUtils.copyProperties(bindingRelationshipEntity,longVirtualNumberHistoryResult);
//            longVirtualNumberHistoryResults.add(longVirtualNumberHistoryResult);
//        });
//        return longVirtualNumberHistoryResults;
//    }

    /**
     * 长虚拟号分页列表
     *
     * @param longVirtualNumberHistoryRequest
     * @param pageable
     * @return
     */
    @Override
    public PageResult<LongVirtualNumberHistoryResult> page(LongVirtualNumberHistoryRequest longVirtualNumberHistoryRequest, PageVO pageable) {
        return longVirtualNumberHistoryPageServiceImpl.page(longVirtualNumberHistoryRequest, pageable);
    }

    @Override
    public List<PageHeadersResult> pageHeaders() {
        return HeadersUtil.getHeaders(LongVirtualNumberHistoryResult.class);
    }
}
