package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LongVirtualNumberQueryServiceImpl implements IVirtualNumberQueryService {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Override
    public Integer queryVirtualNumber(String virtualNumber) {
        Integer integer = virtualNumberMysqlRepository.countByVirtualNumberAndUtypeAndIsdelete(virtualNumber, VirtualNumberTypeEnum.LONG.getCode(), false);
        return integer;
    }

    @Override
    public Integer querySonVirtualNumber(String sonVirtualNumber) {
        return 0;
    }
}
