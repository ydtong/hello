package com.allqj.virtual_number_administrate.business.baseService.impl.elasticsearch;

import com.allqj.virtual_number_administrate.business.baseService.IAddBaseService;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberBindingInfoEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberBindingInfoEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * 虚拟号绑定信息添加到es
 */
@Service
public class VirtualNumberBindingInfoAddToEsBaseServiceImpl implements IAddBaseService<VirtualNumberBindingInfoMysqlEntity, VirtualNumberBindingInfoEsEntity> {

    @Autowired
    private IVirtualNumberBindingInfoEsRepository virtualNumberBindingInfoEsRepository;

    @Override
    @SuppressWarnings("all")
    public VirtualNumberBindingInfoEsEntity add(VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity, Integer virtualNumberType) {
        VirtualNumberBindingInfoEsEntity virtualNumberBindingInfoEsEntity = new VirtualNumberBindingInfoEsEntity();
        BeanUtils.copyProperties(virtualNumberBindingInfoMysqlEntity, virtualNumberBindingInfoEsEntity);
        return virtualNumberBindingInfoEsRepository.save(virtualNumberBindingInfoEsEntity);
    }
}
