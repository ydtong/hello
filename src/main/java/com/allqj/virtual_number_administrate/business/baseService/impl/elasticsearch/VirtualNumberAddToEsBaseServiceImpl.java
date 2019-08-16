package com.allqj.virtual_number_administrate.business.baseService.impl.elasticsearch;

import com.allqj.virtual_number_administrate.business.baseService.IAddBaseService;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * 添加虚拟号到es
 */
@Service
@AddLog
public class VirtualNumberAddToEsBaseServiceImpl implements IAddBaseService<VirtualNumberMysqlEntity, VirtualNumberEsEntity> {

    @Autowired
    private IVirtualNumberEsRepository virtualNumberEsRepository;

    @Override
    @SuppressWarnings("all")
    @LogDescribe("虚拟号同步到es")
    public VirtualNumberEsEntity add(VirtualNumberMysqlEntity virtualNumberMysqlEntity, Integer virtualNumberType) {
        VirtualNumberEsEntity virtualNumberEsEntity = new VirtualNumberEsEntity();
        BeanUtils.copyProperties(virtualNumberMysqlEntity, virtualNumberEsEntity);
        return virtualNumberEsRepository.save(virtualNumberEsEntity);
    }
}
