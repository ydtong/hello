package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberHistoryService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortVirtualNumberHistoryServiceImpl implements IVirtualNumberHistoryService<ShortVirtualNumberHistoryRequest, ShortVirtualNumberHistoryResult> {

    @Autowired
    @Qualifier("shortVirtualNumberHistoryPageFromMysqlBaseServiceImpl")
    private IPageBaseService<ShortVirtualNumberHistoryRequest, PageResult<ShortVirtualNumberHistoryResult>> shortVirtualNumberHistoryPageServiceImpl;
    @Autowired
    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;

//    @Override
//    public List<ShortVirtualNumberHistoryResult> historyVirtualNumber(ShortVirtualNumberHistoryRequest shortVirtualNumberHistoryRequest) {
//        List<VirtualNumberBindingInfoMysqlEntity> bindingRelationshipEntities= virtualNumberBindingInfoMysqlRepository.findAllByVirtualNumberAndIsdeleteTrue(shortVirtualNumberHistoryRequest.getVirtualNumber());
//        List<ShortVirtualNumberHistoryResult> shortVirtualNumberHistoryResults=new ArrayList<>();
//        bindingRelationshipEntities.forEach(bindingRelationshipEntity -> {
//            ShortVirtualNumberHistoryResult longVirtualNumberHistoryResult=new ShortVirtualNumberHistoryResult();
//            BeanUtils.copyProperties(bindingRelationshipEntity,longVirtualNumberHistoryResult);
//            shortVirtualNumberHistoryResults.add(longVirtualNumberHistoryResult);
//        });
//        return shortVirtualNumberHistoryResults;
//    }

    /**
     * 短虚拟号历史记录列表
     *
     * @param shortVirtualNumberHistoryRequest
     * @param pageable
     * @return
     */
    @Override
    public PageResult<ShortVirtualNumberHistoryResult> page(ShortVirtualNumberHistoryRequest shortVirtualNumberHistoryRequest, PageVO pageable) {
        return shortVirtualNumberHistoryPageServiceImpl.page(shortVirtualNumberHistoryRequest, pageable);
    }

    @Override
    public List<PageHeadersResult> pageHeaders() {
        return HeadersUtil.getHeaders(ShortVirtualNumberHistoryResult.class);
    }
}
