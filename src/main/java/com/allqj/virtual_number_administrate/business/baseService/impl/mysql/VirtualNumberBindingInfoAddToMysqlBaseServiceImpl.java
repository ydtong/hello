package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IAddBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationDeptInfoResult;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationUserInfoResult;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.BindingVirtualNumberRequest;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 虚拟号绑定信息
 */
@Service
public class VirtualNumberBindingInfoAddToMysqlBaseServiceImpl implements IAddBaseService<BindingVirtualNumberRequest, VirtualNumberBindingInfoMysqlEntity> {

    @Autowired
    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;

    @Autowired
    private PeopleService peopleService;

    /**
     * 添加历史记录
     *
     * @param bindingVirtualNumberRequest
     * @param virtualNumberType
     * @return
     */
    @Override
    public VirtualNumberBindingInfoMysqlEntity add(BindingVirtualNumberRequest bindingVirtualNumberRequest, Integer virtualNumberType) {
        //校验是否存在绑定
        VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity =
                virtualNumberBindingInfoMysqlRepository
                        .findByVirtualNumberAndPhoneAndVirtualNumberTypeAndIsdelete(
                                bindingVirtualNumberRequest.getVirtualNumber(),
                                bindingVirtualNumberRequest.getPhone(),
                                virtualNumberType,
                                false);
        if (null != virtualNumberBindingInfoMysqlEntity)
            throw new ResultException(StatusCodeEnum.EXIST_BINDING.getCode(), StatusCodeEnum.EXIST_BINDING.getMessage());
        //拼接绑定历史参数
        virtualNumberBindingInfoMysqlEntity = new VirtualNumberBindingInfoMysqlEntity();
        BeanUtils.copyProperties(bindingVirtualNumberRequest, virtualNumberBindingInfoMysqlEntity);
        virtualNumberBindingInfoMysqlEntity.setCreatetime(new Date());
        deptInfo(virtualNumberBindingInfoMysqlEntity, bindingVirtualNumberRequest.getUserId());
        userInfo(virtualNumberBindingInfoMysqlEntity, bindingVirtualNumberRequest);
        return virtualNumberBindingInfoMysqlRepository.save(virtualNumberBindingInfoMysqlEntity);
    }

    /**
     * 获得部门信息
     *
     * @param virtualNumberBindingInfoMysqlEntity
     * @param userId
     */
    private void deptInfo(VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity, Integer userId) {
        OrganizationUserInfoResult organizationUserInfoResult = peopleService.userInfo(userId).getResult();
        if (organizationUserInfoResult == null)
            throw new ResultException(StatusCodeEnum.NOT_USER.getCode(), StatusCodeEnum.NOT_USER.getMessage());
        OrganizationDeptInfoResult organizationDeptInfoResult = peopleService.deptInfo(organizationUserInfoResult.getInDeptId()).getResult();
        if (organizationDeptInfoResult == null)
            throw new ResultException(StatusCodeEnum.NOT_DEPT.getCode(), StatusCodeEnum.NOT_DEPT.getMessage());
        virtualNumberBindingInfoMysqlEntity.setDeptId(organizationUserInfoResult.getInDeptId());
        virtualNumberBindingInfoMysqlEntity.setDeptName(organizationDeptInfoResult.getName());
        virtualNumberBindingInfoMysqlEntity.setDeptType(organizationDeptInfoResult.getDepartmenttype());
    }

    /**
     * 获得用户名字
     *
     * @param virtualNumberBindingInfoMysqlEntity
     * @param bindingVirtualNumberRequest
     */
    private void userInfo(VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity, BindingVirtualNumberRequest bindingVirtualNumberRequest) {
        virtualNumberBindingInfoMysqlEntity.setUserName(bindingVirtualNumberRequest.getName());
    }
}
