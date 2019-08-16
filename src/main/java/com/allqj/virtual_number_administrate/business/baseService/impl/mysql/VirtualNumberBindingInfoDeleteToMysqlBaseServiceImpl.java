package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationDeptInfoResult;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.CancelBindingVirtualNumberRequest;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * 虚拟号绑定信息删除
 */
@Service
public class VirtualNumberBindingInfoDeleteToMysqlBaseServiceImpl implements IModifyBaseService<CancelBindingVirtualNumberRequest, VirtualNumberBindingInfoMysqlEntity> {

    @Autowired
    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;

    @Autowired
    private PeopleService peopleService;

    @Override
    public VirtualNumberBindingInfoMysqlEntity modify(CancelBindingVirtualNumberRequest cancelBindingVirtualNumberRequest, Integer virtualNumberType) {
        //校验是否存在绑定
        VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity =
                virtualNumberBindingInfoMysqlRepository
                        .findByVirtualNumberAndPhoneAndVirtualNumberTypeAndIsdelete(
                                cancelBindingVirtualNumberRequest.getVirtualNumber(),
                                cancelBindingVirtualNumberRequest.getPhone(),
                                cancelBindingVirtualNumberRequest.getVirtualNumberType(),
                                false);
        if (virtualNumberBindingInfoMysqlEntity == null)
            throw new ResultException(StatusCodeEnum.NOT_FOUND_DATA.getCode(), StatusCodeEnum.NOT_FOUND_DATA.getMessage());
        //修改绑定历史表
        deptInfo(virtualNumberBindingInfoMysqlEntity);
        virtualNumberBindingInfoMysqlEntity.setIsdelete(true);
        virtualNumberBindingInfoMysqlEntity.setModifytime(new Date());
        return virtualNumberBindingInfoMysqlRepository.save(virtualNumberBindingInfoMysqlEntity);
    }

    /**
     * 获得部门信息
     *
     * @param virtualNumberBindingInfoMysqlEntity
     */
    private void deptInfo(VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity) {
        OrganizationDeptInfoResult organizationDeptInfoResult = peopleService.deptInfo(virtualNumberBindingInfoMysqlEntity.getDeptId()).getResult();
        if (organizationDeptInfoResult == null)
            throw new ResultException(StatusCodeEnum.NOT_DEPT.getCode(), StatusCodeEnum.NOT_DEPT.getMessage());
        virtualNumberBindingInfoMysqlEntity.setDeptName(organizationDeptInfoResult.getName());
    }
}
