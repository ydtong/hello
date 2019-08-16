package com.allqj.virtual_number_administrate.business.baseService.impl.elasticsearch;


import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * 虚拟号分配到es
 */
@Service
public class VirtualNumberModifyToEsBaseServiceImpl implements IModifyBaseService<VirtualNumberMysqlEntity, VirtualNumberEsEntity> {

    @Autowired
    private IVirtualNumberEsRepository virtualNumberEsRepository;

    @Override
    @SuppressWarnings("all")
    public VirtualNumberEsEntity modify(VirtualNumberMysqlEntity virtualNumberMysqlEntity, Integer virtualNumberType) {
        VirtualNumberEsEntity virtualNumberEsEntity = new VirtualNumberEsEntity();
        BeanUtils.copyProperties(virtualNumberMysqlEntity, virtualNumberEsEntity);
        return virtualNumberEsRepository.save(virtualNumberEsEntity);
    }
}
