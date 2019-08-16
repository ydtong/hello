package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IAddBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IQueryBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberAddRequest;
import com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: cj
 * @description 短隐号添加Mysql
 * @date: 2019/4/30 11:50
 **/
@Service
public class ShortVirtualNumberAddToMysqlBaseServiceImpl implements IAddBaseService<ShortVirtualNumberAddRequest, VirtualNumberMysqlEntity> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;


    @Autowired
    @Qualifier("virtualNumberQueryFromMysqlBaseServiceImpl")
    private IQueryBaseService<VirtualNumberBase, VirtualNumberMysqlEntity> virtualNumberQueryFromMysqlBaseServiceImpl;

    @Override
    public VirtualNumberMysqlEntity add(ShortVirtualNumberAddRequest shortVirtualNumberAddRequest, Integer virtualNumberType) {
        Date modifyTime = new Date();
        //查重?更新:新增
        VirtualNumberBase virtualNumberBase = new VirtualNumberBase();
        virtualNumberBase.setVirtualNumber(shortVirtualNumberAddRequest.getVirtualNumber());
        virtualNumberBase.setVirtualNumberType(VirtualNumberTypeEnum.SHORT.getCode());
        VirtualNumberMysqlEntity virtualNumberMysqlEntity = virtualNumberQueryFromMysqlBaseServiceImpl.query(virtualNumberBase, false, false);
        if (null == virtualNumberMysqlEntity) {
            //新增虚拟号
            virtualNumberMysqlEntity = new VirtualNumberMysqlEntity();
            BeanUtils.copyProperties(shortVirtualNumberAddRequest, virtualNumberMysqlEntity);
            virtualNumberMysqlEntity.setUtype(VirtualNumberTypeEnum.SHORT.getCode());
            virtualNumberMysqlEntity.setAccountCode(shortVirtualNumberAddRequest.getAccountCode());
        } else {
            //更新联通长号
            if (null != shortVirtualNumberAddRequest.getSonVirtualNumber()) {
                virtualNumberMysqlEntity.setSonVirtualNumber(shortVirtualNumberAddRequest.getSonVirtualNumber());
                virtualNumberMysqlEntity.setModifytime(modifyTime);
            }
            //更新身份证号
            if (null != shortVirtualNumberAddRequest.getIdCard()) {
                virtualNumberMysqlEntity.setIdCard(shortVirtualNumberAddRequest.getIdCard());
                virtualNumberMysqlEntity.setModifytime(modifyTime);
            }
        }
        return virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
    }
}
