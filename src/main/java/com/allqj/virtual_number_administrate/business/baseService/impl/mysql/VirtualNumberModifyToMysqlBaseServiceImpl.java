package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberAddRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MySQL修改虚拟号
 */
@Service
public class VirtualNumberModifyToMysqlBaseServiceImpl implements IModifyBaseService<VirtualNumberAddRequest, VirtualNumberMysqlEntity> {
    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Override
    public VirtualNumberMysqlEntity modify(VirtualNumberAddRequest virtualNumberAddRequest, Integer virtualNumberType) {
        VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                virtualNumberMysqlRepository.findByVirtualNumberAndUtypeAndIsdelete(virtualNumberAddRequest.getVirtualNumber(), virtualNumberType, false);
        if (null == virtualNumberMysqlEntity)
            return null;
        BeanUtils.copyProperties(virtualNumberAddRequest, virtualNumberMysqlEntity);
        virtualNumberMysqlEntity.setUtype(virtualNumberType);
        return virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
    }
}
