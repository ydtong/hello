package com.allqj.virtual_number_administrate.business.baseService.impl.elasticsearch;

import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberBindingInfoEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberBindingInfoEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * 取消绑定关系到es
 */
@Service
public class VirtualNumberBindingInfoDeleteToEsBaseServiceImpl implements IModifyBaseService<VirtualNumberBindingInfoMysqlEntity, VirtualNumberBindingInfoEsEntity> {

    @Autowired
    private IVirtualNumberBindingInfoEsRepository virtualNumberBindingInfoEsRepository;

    @Override
    @SuppressWarnings("all")
    public VirtualNumberBindingInfoEsEntity modify(VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity, Integer virtualNumberType) {
        VirtualNumberBindingInfoEsEntity virtualNumberBindingInfoEsEntity = new VirtualNumberBindingInfoEsEntity();
        BeanUtils.copyProperties(virtualNumberBindingInfoMysqlEntity, virtualNumberBindingInfoEsEntity);
        return virtualNumberBindingInfoEsRepository.save(virtualNumberBindingInfoEsEntity);
    }
}
