package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.stereotype.Service;


/**
 * 虚拟号删除
 */
@Service
public class VirtualNumberDeleteToMysqlBaseServiceImpl implements IModifyBaseService<VirtualNumberMysqlEntity, VirtualNumberMysqlEntity> {
    /**
     * 虚拟号删除
     * 以分配和绑定的不可删除
     *
     * @param virtualNumberMysqlEntity
     * @param virtualNumberType
     * @return
     */
    @Override
    public VirtualNumberMysqlEntity modify(VirtualNumberMysqlEntity virtualNumberMysqlEntity, Integer virtualNumberType) {
        if (virtualNumberMysqlEntity == null)
            throw new ResultException(StatusCodeEnum.NOT_FOUND_DATA.getCode(), StatusCodeEnum.NOT_FOUND_DATA.getMessage());
        if (virtualNumberMysqlEntity.getIsBinding())
            throw new ResultException(StatusCodeEnum.EXIST_BINDING.getCode(), StatusCodeEnum.EXIST_BINDING.getMessage() + " : " + virtualNumberMysqlEntity.getVirtualNumber());
        if (virtualNumberMysqlEntity.getIsAssign())
            throw new ResultException(StatusCodeEnum.EXIST_OCCUPY.getCode(), StatusCodeEnum.EXIST_OCCUPY.getMessage() + " : " + virtualNumberMysqlEntity.getVirtualNumber());
        virtualNumberMysqlEntity.setIsdelete(true);
        return virtualNumberMysqlEntity;
    }
}
