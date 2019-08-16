package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IVirtualNumberBindingBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 虚拟号绑定
 */
@Service
public class VirtualNumberBindingToMysqlBaseServiceImpl implements IVirtualNumberBindingBaseService<String, VirtualNumberMysqlEntity> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    /**
     * 修改虚拟号绑定状态
     *
     * @param virtualNumber
     * @param virtualNumberType
     * @return
     */
    @Override
    public VirtualNumberMysqlEntity modify(String virtualNumber, Integer virtualNumberType) {
        //校验虚拟号
        VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                virtualNumberMysqlRepository.findByVirtualNumberAndUtypeAndIsdelete(virtualNumber, virtualNumberType, false);
        if (virtualNumberMysqlEntity == null)
            throw new ResultException(StatusCodeEnum.NOT_FOUND_DATA.getCode(), StatusCodeEnum.NOT_FOUND_DATA.getMessage());
        //是否绑定
        if (virtualNumberMysqlEntity.getIsBinding())
            throw new ResultException(StatusCodeEnum.EXIST_BINDING.getCode(), StatusCodeEnum.EXIST_BINDING.getMessage());
        virtualNumberMysqlEntity.setIsBinding(true);
        return virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);

    }
}
