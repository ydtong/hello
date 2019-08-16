package com.allqj.virtual_number_administrate.business.baseService;

import com.allqj.virtual_number_administrate.business.vo.base.DeptBase;


public interface IVirtualNumberAssignBaseService<Data, Result> {
    Result modify(Data data, Integer virtualNumberType, DeptBase deptBase);
}
