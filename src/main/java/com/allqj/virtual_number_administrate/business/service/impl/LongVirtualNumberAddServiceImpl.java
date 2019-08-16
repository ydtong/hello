package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IAddBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberAddService;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberAddRequest;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberAddResult;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberExcelAddRequest;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.Lock;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.UseDistributedLocks;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@AddLog
@UseDistributedLocks
@Service
public class LongVirtualNumberAddServiceImpl implements IVirtualNumberAddService<LongVirtualNumberAddRequest, LongVirtualNumberExcelAddRequest, LongVirtualNumberAddResult> {

    @Autowired
    @Qualifier("longVirtualNumberAddToMysqlBaseServiceImpl")
    private IAddBaseService<LongVirtualNumberAddRequest, VirtualNumberMysqlEntity> longVirtualNumberAddToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberAddToEsBaseServiceImpl")
    private IAddBaseService<VirtualNumberMysqlEntity, VirtualNumberEsEntity> virtualNumberAddToEsBaseServiceImpl;

    @Override
    @Transactional
    @LogDescribe("长号添加")
    @Lock
    public LongVirtualNumberAddResult add(LongVirtualNumberAddRequest virtualNumberAddRequest) {
        //校验虚拟号长度
        excelAddCheck(virtualNumberAddRequest.getVirtualNumber());
        VirtualNumberMysqlEntity virtualNumberMysqlEntity = longVirtualNumberAddToMysqlBaseServiceImpl.add(virtualNumberAddRequest, VirtualNumberTypeEnum.LONG.getCode());
        LongVirtualNumberAddResult longVirtualNumberAddResult = new LongVirtualNumberAddResult();
        BeanUtils.copyProperties(virtualNumberMysqlEntity, longVirtualNumberAddResult);

        //同步到es
        virtualNumberAddToEsBaseServiceImpl.add(virtualNumberMysqlEntity, VirtualNumberTypeEnum.LONG.getCode());
        return longVirtualNumberAddResult;
    }

    @Override
    @Transactional
    @LogDescribe("Excel长号添加")
    @Lock
    public List<LongVirtualNumberAddResult> excelAdd(List<LongVirtualNumberExcelAddRequest> virtualNumberAddRequestList) {
        List<VirtualNumberMysqlEntity> virtualNumberMysqlEntityList = new ArrayList<>();
        virtualNumberAddRequestList.forEach(longVirtualNumberExcelAddRequest -> {
            //校验虚拟号长度
            excelAddCheck(longVirtualNumberExcelAddRequest.getVirtualNumber());
            LongVirtualNumberAddRequest longVirtualNumberAddRequest = new LongVirtualNumberAddRequest();
            BeanUtils.copyProperties(longVirtualNumberExcelAddRequest, longVirtualNumberAddRequest);
            VirtualNumberMysqlEntity virtualNumberMysqlEntity = longVirtualNumberAddToMysqlBaseServiceImpl.add(longVirtualNumberAddRequest, VirtualNumberTypeEnum.LONG.getCode());
            virtualNumberMysqlEntityList.add(virtualNumberMysqlEntity);
        });
        //同步到es
        virtualNumberMysqlEntityList.forEach(virtualNumberMysqlEntity ->
                virtualNumberAddToEsBaseServiceImpl.add(virtualNumberMysqlEntity, VirtualNumberTypeEnum.LONG.getCode()));
        return new ArrayList<>();
    }

    private void excelAddCheck(String virtualNumber) {
        Pattern pattern = Pattern.compile("[0-9]{11}");
        if (null == virtualNumber || virtualNumber.trim().isEmpty() ||
                !pattern.matcher(virtualNumber.trim()).matches())
            throw new ResultException(StatusCodeEnum.SHORT_VIRTUAL_NUMBER_TOO_SHORT.getCode(),
                    StatusCodeEnum.SHORT_VIRTUAL_NUMBER_TOO_SHORT.getMessage() + " : " + virtualNumber);
    }
}
