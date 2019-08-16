package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IAddBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberAddRequest;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;


/**
 * 长虚拟号添加到mysql
 */
@AddLog
@Service
public class LongVirtualNumberAddToMysqlBaseServiceImpl implements IAddBaseService<LongVirtualNumberAddRequest, VirtualNumberMysqlEntity> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Override
    @LogDescribe("虚拟号添加到mysql")
    public VirtualNumberMysqlEntity add(LongVirtualNumberAddRequest virtualNumberAddRequest, Integer virtualNumberType) {
        //查询，存在修改，不存在新增
        VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                virtualNumberMysqlRepository.findByVirtualNumberAndUtypeAndIsdelete(virtualNumberAddRequest.getVirtualNumber().trim(),
                        VirtualNumberTypeEnum.LONG.getCode(), false);
        if (virtualNumberMysqlEntity != null && !StringUtils.isEmpty(virtualNumberAddRequest.getIdCard())) {
            virtualNumberMysqlEntity.setModifytime(new Date());
            virtualNumberMysqlEntity.setIdCard(virtualNumberAddRequest.getIdCard());
        } else {
            virtualNumberMysqlEntity = new VirtualNumberMysqlEntity();
            BeanUtils.copyProperties(virtualNumberAddRequest, virtualNumberMysqlEntity);
            virtualNumberMysqlEntity.setUtype(virtualNumberType);
        }
        return virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
    }
}
