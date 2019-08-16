package com.allqj.virtual_number_administrate.business.baseService.impl.elasticsearch;

import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IDeptInfoEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.DeptInfoEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * 将修改的部门信息更新到es
 */
@Service
public class DeptModifyToEsBaseServiceImpl implements IModifyBaseService<DeptInfoMysqlEntity, DeptInfoEsEntity> {

    @Autowired
    private IDeptInfoEsRepository deptInfoEsRepository;

    /**
     * 将修改的部门信息更新到es
     *
     * @param deptInfoMysqlEntity
     * @param virtualNumberType
     * @return
     */
    @Override
    public DeptInfoEsEntity modify(DeptInfoMysqlEntity deptInfoMysqlEntity, Integer virtualNumberType) {
        DeptInfoEsEntity deptInfoEsEntity = new DeptInfoEsEntity();
        BeanUtils.copyProperties(deptInfoMysqlEntity, deptInfoEsEntity);
        return deptInfoEsRepository.save(deptInfoEsEntity);
    }
}
