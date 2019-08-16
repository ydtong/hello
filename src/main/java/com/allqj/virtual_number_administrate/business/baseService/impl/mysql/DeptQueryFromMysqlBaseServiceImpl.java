package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IQueryBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdRequest;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdResult;
import com.allqj.virtual_number_administrate.business.repository.mysql.IDeptInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.base.DeptBase;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 在mysql中查询部门信息
 */
@Service
public class DeptQueryFromMysqlBaseServiceImpl implements IQueryBaseService<DeptBase, DeptInfoMysqlEntity> {

    @Autowired
    private IDeptInfoMysqlRepository deptInfoMysqlRepository;

    @Autowired
    private PeopleService peopleServiceClient;

    /**
     * 获取部门信息
     *
     * @param deptBase
     * @param isdelete
     * @param must
     * @return
     */
    @Override
    public DeptInfoMysqlEntity query(DeptBase deptBase, Boolean isdelete, Boolean must) {

        DeptInfoMysqlEntity deptInfoMysqlEntity =
                deptInfoMysqlRepository.findByDeptIdAndDeptTypeAndIsdelete(deptBase.getDeptId(), deptBase.getDeptType(), isdelete);

        if (!must || deptInfoMysqlEntity != null)
            return deptInfoMysqlEntity;
        //查询人才库部门信息
        OrganizationQueryByDeptIdRequest queryByDeptIdRequest = new OrganizationQueryByDeptIdRequest();
        queryByDeptIdRequest.setDeptId(deptBase.getDeptId());
        queryByDeptIdRequest.setDeptType(deptBase.getDeptType());
        OrganizationQueryByDeptIdResult queryByDeptIdResult = peopleServiceClient.departmentQueryByDeptId(queryByDeptIdRequest).getResult();
        if (null == queryByDeptIdResult)
            throw new ResultException(StatusCodeEnum.NOT_FOUND_DATA.getCode(), StatusCodeEnum.NOT_FOUND_DATA.getMessage());

        return new DeptInfoMysqlEntity(deptBase.getDeptId(), deptBase.getDeptType(), queryByDeptIdResult.getDeptName());
    }
}
