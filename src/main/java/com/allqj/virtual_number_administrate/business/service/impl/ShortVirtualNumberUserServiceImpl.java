package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IAddBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IVirtualNumberBindingBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IVirtualNumberCancelBindingBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.inte.IShortService;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.ShortBindingRequest;
import com.allqj.virtual_number_administrate.business.microService.vo.CallReqest;
import com.allqj.virtual_number_administrate.business.microService.vo.CallResult;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberBindingInfoEsEntity;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberUseService;
import com.allqj.virtual_number_administrate.business.vo.*;
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


/**
 * @author: cj
 * @description 短隐号操作
 * @date: 2019/3/28 10:01
 **/
@Service("shortVirtualNumberUserServiceImpl")
@AddLog
@UseDistributedLocks
public class ShortVirtualNumberUserServiceImpl implements IVirtualNumberUseService {

    @Autowired
    @Qualifier("virtualNumberBindingInfoAddToEsBaseServiceImpl")
    private IAddBaseService<VirtualNumberBindingInfoMysqlEntity, VirtualNumberBindingInfoEsEntity> virtualNumberBindingInfoAddToEsBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberBindingInfoAddToMysqlBaseServiceImpl")
    private IAddBaseService<BindingVirtualNumberRequest, VirtualNumberBindingInfoMysqlEntity> virtualNumberBindingInfoAddToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberBindingInfoDeleteToEsBaseServiceImpl")
    private IModifyBaseService<VirtualNumberBindingInfoMysqlEntity, VirtualNumberBindingInfoEsEntity> virtualNumberBindingInfoDeleteToEsBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberBindingInfoDeleteToMysqlBaseServiceImpl")
    private IModifyBaseService<CancelBindingVirtualNumberRequest, VirtualNumberBindingInfoMysqlEntity> virtualNumberBindingInfoDeleteToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberModifyToEsBaseServiceImpl")
    private IModifyBaseService<VirtualNumberMysqlEntity, VirtualNumberEsEntity> virtualNumberModifyToEsBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberBindingToMysqlBaseServiceImpl")
    private IVirtualNumberBindingBaseService<String, VirtualNumberMysqlEntity> virtualNumberBindingToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberCancelBindingToMysqlBaseServiceImpl")
    private IVirtualNumberCancelBindingBaseService<String, VirtualNumberMysqlEntity> virtualNumberCancelBindingToMysqlBaseServiceImpl;

    @Autowired
    private IShortService shortService;

    /**
     * 短隐号绑定
     *
     * @param bindingVirtualNumberRequest
     * @return
     */
    @Override
    @Transactional
    @LogDescribe("短号绑定")
    @Lock
    public BindingVirtualNumberResult binding(BindingVirtualNumberRequest bindingVirtualNumberRequest) {

        if (bindingVirtualNumberRequest == null || bindingVirtualNumberRequest.getUserId() == null)
            throw new ResultException(StatusCodeEnum.PARAMETERVALIDERROR.getCode(), StatusCodeEnum.PARAMETERVALIDERROR.getMessage());

        VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                virtualNumberBindingToMysqlBaseServiceImpl.modify(bindingVirtualNumberRequest.getVirtualNumber(), bindingVirtualNumberRequest.getVirtualNumberType());
        VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity =
                virtualNumberBindingInfoAddToMysqlBaseServiceImpl.add(bindingVirtualNumberRequest, bindingVirtualNumberRequest.getVirtualNumberType());

        BindingVirtualNumberResult result = new BindingVirtualNumberResult();
        BeanUtils.copyProperties(virtualNumberBindingInfoMysqlEntity, result);

        //调用基础服务
        ShortBindingRequest shortBindingRequest = new ShortBindingRequest(
                bindingVirtualNumberRequest.getPhone(),
                bindingVirtualNumberRequest.getVirtualNumber(),
                bindingVirtualNumberRequest.getName(),
                bindingVirtualNumberRequest.getAccountCode()
        );
        ResultVO<Boolean> clientResult = shortService.binding(shortBindingRequest);
        if (clientResult.getResult() == null || !clientResult.getResult())
            throw new ResultException(StatusCodeEnum.BINDING_FAULT.getCode(), clientResult.getMessage());

        //同步到es
        virtualNumberModifyToEsBaseServiceImpl.modify(virtualNumberMysqlEntity, bindingVirtualNumberRequest.getVirtualNumberType());
        virtualNumberBindingInfoAddToEsBaseServiceImpl.add(virtualNumberBindingInfoMysqlEntity, bindingVirtualNumberRequest.getVirtualNumberType());

        return result;
    }

    /**
     * 短隐号解绑
     *
     * @param cancelBindingVirtualNumberRequest
     * @return
     */
    @Override
    @Transactional
    @LogDescribe("短号解绑")
    @Lock
    public Boolean cancelBinding(CancelBindingVirtualNumberRequest cancelBindingVirtualNumberRequest) {

        VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                virtualNumberCancelBindingToMysqlBaseServiceImpl.modify(cancelBindingVirtualNumberRequest.getVirtualNumber(), cancelBindingVirtualNumberRequest.getVirtualNumberType());
        VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity =
                virtualNumberBindingInfoDeleteToMysqlBaseServiceImpl.modify(cancelBindingVirtualNumberRequest, cancelBindingVirtualNumberRequest.getVirtualNumberType());

        //解绑
        ResultVO<Boolean> result = shortService.dropUser(cancelBindingVirtualNumberRequest.getPhone());
        if (result.getResult() == null || !result.getResult())
            throw new ResultException(StatusCodeEnum.BINDING_CANCEL_FAULT.getCode(), result.getMessage());

        //同步到es
        virtualNumberModifyToEsBaseServiceImpl.modify(virtualNumberMysqlEntity, cancelBindingVirtualNumberRequest.getVirtualNumberType());
        virtualNumberBindingInfoDeleteToEsBaseServiceImpl.modify(virtualNumberBindingInfoMysqlEntity, cancelBindingVirtualNumberRequest.getVirtualNumberType());
        return true;
    }

    /**
     * 短隐号拨打
     *
     * @param callVirtualNumberRequest
     * @return
     */
    @Override
    @LogDescribe("短号拨打")
    public CallVirtualNumberResult calls(CallReqest callVirtualNumberRequest) {

        //拨打
        ResultVO<CallResult> result = shortService.call(callVirtualNumberRequest);
        if (result.getResult() == null)
            throw new ResultException(StatusCodeEnum.CALL_FAULT.getCode(), result.getMessage());

        CallResult callResult = result.getResult();
        CallVirtualNumberResult callVirtualNumberResult = new CallVirtualNumberResult();
        BeanUtils.copyProperties(callResult, callVirtualNumberResult);
        return callVirtualNumberResult;
    }
}
