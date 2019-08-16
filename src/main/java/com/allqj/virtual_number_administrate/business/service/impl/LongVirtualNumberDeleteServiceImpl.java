package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IQueryBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberDeleteService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBase;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.Lock;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.UseDistributedLocks;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@AddLog
@UseDistributedLocks
public class LongVirtualNumberDeleteServiceImpl implements IVirtualNumberDeleteService<LongVirtualNumberDeleteRequest, LongVirtualNumberDeleteResult> {

    @Autowired
    @Qualifier("virtualNumberQueryFromMysqlBaseServiceImpl")
    private IQueryBaseService<VirtualNumberBase, VirtualNumberMysqlEntity> virtualNumberQueryFromMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberModifyToEsBaseServiceImpl")
    private IModifyBaseService<VirtualNumberMysqlEntity, VirtualNumberEsEntity> virtualNumberModifyToEsBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberDeleteToMysqlBaseServiceImpl")
    private IModifyBaseService<VirtualNumberMysqlEntity, VirtualNumberMysqlEntity> virtualNumberDeleteToMysqlBaseServiceImpl;

    /**
     * 删除长虚拟号
     *
     * @param deleteVirtualNumberRequest
     * @return
     */
    @Transactional
    @Override
    @LogDescribe("删除长虚拟号")
    @Lock
    public LongVirtualNumberDeleteResult deleteVirtualNumber(LongVirtualNumberDeleteRequest deleteVirtualNumberRequest) {

        List<VirtualNumberMysqlEntity> virtualNumberMysqlEntityList = new ArrayList<>();

        deleteVirtualNumberRequest.getVirtualNumberList().forEach(o ->
        {
            VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                    virtualNumberQueryFromMysqlBaseServiceImpl.query(new VirtualNumberBase(o, VirtualNumberTypeEnum.LONG.getCode()), false, true);
            virtualNumberMysqlEntity = virtualNumberDeleteToMysqlBaseServiceImpl.modify(virtualNumberMysqlEntity, VirtualNumberTypeEnum.LONG.getCode());

            virtualNumberMysqlEntityList.add(virtualNumberMysqlEntity);

        });

        //同步到es
        virtualNumberMysqlEntityList.forEach(virtualNumberMysqlEntity ->
                virtualNumberModifyToEsBaseServiceImpl.modify(virtualNumberMysqlEntity, VirtualNumberTypeEnum.LONG.getCode()));

        return new LongVirtualNumberDeleteResult();
    }
}
