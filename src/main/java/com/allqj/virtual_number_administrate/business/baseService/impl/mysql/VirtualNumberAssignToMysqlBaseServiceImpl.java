package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IVirtualNumberAssignBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.base.DeptBase;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * 虚拟号分配
 */
@Service
public class VirtualNumberAssignToMysqlBaseServiceImpl implements IVirtualNumberAssignBaseService<String, VirtualNumberMysqlEntity> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    /**
     * 分配隐号
     *
     * @param virtualNumber
     * @param virtualNumberType
     * @param deptBase
     * @return
     */
    @Override
    public VirtualNumberMysqlEntity modify(String virtualNumber, Integer virtualNumberType, DeptBase deptBase) {
        VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                virtualNumberMysqlRepository.findByVirtualNumberAndUtypeAndIsdelete(virtualNumber, virtualNumberType, false);
        //是否存在
        if (virtualNumberMysqlEntity == null)
            throw new ResultException(StatusCodeEnum.NOT_FOUND_DATA.getCode(), StatusCodeEnum.NOT_FOUND_DATA.getMessage());
        //是否分配
        if (virtualNumberMysqlEntity.getIsAssign())
            throw new ResultException(StatusCodeEnum.EXIST_OCCUPY.getCode(), StatusCodeEnum.EXIST_OCCUPY.getMessage() + " : " + virtualNumber);
        //保存数据
        virtualNumberMysqlEntity.setIsAssign(true);
        virtualNumberMysqlEntity.setDeptId(deptBase.getDeptId());
        virtualNumberMysqlEntity.setDeptType(deptBase.getDeptType());
        virtualNumberMysqlEntity.setModifytime(new Date());
        return virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
    }
}
