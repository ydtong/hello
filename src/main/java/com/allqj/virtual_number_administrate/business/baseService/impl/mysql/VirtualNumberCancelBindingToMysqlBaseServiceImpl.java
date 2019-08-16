package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IVirtualNumberCancelBindingBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 虚拟号取消绑定
 */
@Service
public class VirtualNumberCancelBindingToMysqlBaseServiceImpl implements IVirtualNumberCancelBindingBaseService<String, VirtualNumberMysqlEntity> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    /**
     * 修改虚拟号绑定状态
     * 添加绑定历史状态
     *
     * @param s
     * @param virtualNumberType
     * @return
     */
    @Override
    public VirtualNumberMysqlEntity modify(String s, Integer virtualNumberType) {

        VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                virtualNumberMysqlRepository.findByVirtualNumberAndUtypeAndIsdelete(s, virtualNumberType, false);
        if (virtualNumberMysqlEntity == null)
            throw new ResultException(StatusCodeEnum.NOT_FOUND_DATA.getCode(), StatusCodeEnum.NOT_FOUND_DATA.getMessage());
        virtualNumberMysqlEntity.setIsBinding(false);
        virtualNumberMysqlEntity.setIsHistory(true);
        // TODO  身份证需处理
        return virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
    }
}
