package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IDeptInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * 部门中的虚拟号信息更新到mysql
 */
@Service
public class DeptVirtualNumberUpdateToMysqlBaseServiceImpl implements IModifyBaseService<DeptInfoMysqlEntity, DeptInfoMysqlEntity> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Autowired
    private IDeptInfoMysqlRepository deptInfoMysqlRepository;

    /**
     * 部门中的虚拟号信息更新到mysql
     *
     * @param deptInfoMysqlEntity
     * @param virtualNumberType
     * @return
     */
    @Override
    public DeptInfoMysqlEntity modify(DeptInfoMysqlEntity deptInfoMysqlEntity, Integer virtualNumberType) {
        //在枚举中更新部门隐号状态
        deptInfoMysqlEntity = VirtualNumberTypeEnum
                .getVirtualNumberTypeEnum(virtualNumberType)
                .updateDeptInfoMysqlEntity(deptInfoMysqlEntity, virtualNumberMysqlRepository);
        deptInfoMysqlEntity.setModifytime(new Date());
        return deptInfoMysqlRepository.save(deptInfoMysqlEntity);
    }
}
