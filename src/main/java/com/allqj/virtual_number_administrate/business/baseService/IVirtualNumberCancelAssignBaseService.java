package com.allqj.virtual_number_administrate.business.baseService;


public interface IVirtualNumberCancelAssignBaseService<Data, Result> {
    Result modifyAnd(Data data, Integer virtualNumberType);
}
