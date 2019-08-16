package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.enums.*;
import com.allqj.virtual_number_administrate.business.service.IDictionaryService;
import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DictionaryServiceImpl implements IDictionaryService {

    @Override
    public List<DictionaryResult<Integer, String>> virtualNumberType() {
        return VirtualNumberTypeEnum.getDictionaryResult();
    }

    @Override
    public List<DictionaryResult<Integer, String>> deptVirtualNumberWhere() {
        return DeptVirtualNumberWhereEnum.getDictionaryList();
    }

    @Override
    public List<DictionaryResult<Boolean, String>> virtualNumberAssignState() {
        return VirtualNumberAssignStateEnum.getDictionaryResult();
    }

    @Override
    public List<DictionaryResult<Boolean, String>> virtualNumberBindingState() {
        return VirtualNumberBindingStateEnum.getDictionaryResult();
    }

    @Override
    public List<DictionaryResult<Integer, String>> sonVirtualNumberState() {
        return SonVirtualNumberStateEnum.getDictionaryResult();
    }
}
