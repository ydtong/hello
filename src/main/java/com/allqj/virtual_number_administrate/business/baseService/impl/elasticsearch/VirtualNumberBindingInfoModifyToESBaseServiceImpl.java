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
 * @author: cj
 * @description 修改绑定信息到ES
 * @date: 2019/4/21 17:38
 **/
@Service
public class VirtualNumberBindingInfoModifyToESBaseServiceImpl implements IModifyBaseService<VirtualNumberBindingInfoMysqlEntity, VirtualNumberBindingInfoEsEntity> {
    @Autowired
    private IVirtualNumberBindingInfoEsRepository virtualNumberBindingInfoEsRepository;

    @Override
    /**
     * 修改绑定信息到ES（已废弃）
     */
    @SuppressWarnings("all")
    @Deprecated
    public VirtualNumberBindingInfoEsEntity modify(VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity, Integer virtualNumberType) {

        VirtualNumberBindingInfoEsEntity virtualNumberEsEntity = new VirtualNumberBindingInfoEsEntity();
        BeanUtils.copyProperties(virtualNumberBindingInfoMysqlEntity, virtualNumberEsEntity);
        virtualNumberBindingInfoEsRepository.save(virtualNumberEsEntity);

        //添加绑定信息
        VirtualNumberBindingInfoMysqlEntity saveVirtualNumberBindingInfoMysqlEntity = new VirtualNumberBindingInfoMysqlEntity();
        virtualNumberBindingInfoMysqlEntity.setId(null);
        virtualNumberBindingInfoMysqlEntity.setCreatetime(null);
        virtualNumberBindingInfoMysqlEntity.setModifytime(null);
        virtualNumberBindingInfoMysqlEntity.setIsdelete(false);
        BeanUtils.copyProperties(virtualNumberBindingInfoMysqlEntity, saveVirtualNumberBindingInfoMysqlEntity);
        //转换为ES绑定信息表
        VirtualNumberBindingInfoEsEntity saveVirtualNumberEsEntity = new VirtualNumberBindingInfoEsEntity();
        BeanUtils.copyProperties(saveVirtualNumberBindingInfoMysqlEntity, saveVirtualNumberEsEntity);
        return virtualNumberBindingInfoEsRepository.save(saveVirtualNumberEsEntity);
    }
}
