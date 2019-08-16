package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IQueryBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBase;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 虚拟号查询
 */
@Service
public class VirtualNumberQueryFromMysqlBaseServiceImpl implements IQueryBaseService<VirtualNumberBase, VirtualNumberMysqlEntity> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Override
    public VirtualNumberMysqlEntity query(VirtualNumberBase virtualNumberBase, Boolean isdelete, Boolean must) {
        VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                virtualNumberMysqlRepository.findByVirtualNumberAndUtypeAndIsdelete(virtualNumberBase.getVirtualNumber(), virtualNumberBase.getVirtualNumberType(), isdelete);
        if (must && virtualNumberMysqlEntity == null)
            throw new ResultException(StatusCodeEnum.NOT_FOUND_DATA.getCode(), StatusCodeEnum.NOT_FOUND_DATA.getMessage() + " : " + virtualNumberBase.getVirtualNumber());
        return virtualNumberMysqlEntity;
    }
}
