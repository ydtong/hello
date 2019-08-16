package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IVirtualNumberCancelAssignBaseService;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * 虚拟号取消分配
 */
@Service
public class VirtualNumberCancelAssignToMysqlBaseServiceImpl implements IVirtualNumberCancelAssignBaseService<VirtualNumberMysqlEntity, VirtualNumberMysqlEntity> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Override
    public VirtualNumberMysqlEntity modifyAnd(VirtualNumberMysqlEntity virtualNumberMysqlEntity, Integer virtualNumberType) {
        virtualNumberMysqlEntity.setIsAssign(false);
        virtualNumberMysqlEntity.setDeptId(null);
        virtualNumberMysqlEntity.setDeptType(null);
        virtualNumberMysqlEntity.setModifytime(new Date());
        return virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
    }
}
