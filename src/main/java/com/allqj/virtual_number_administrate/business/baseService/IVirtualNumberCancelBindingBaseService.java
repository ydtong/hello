package com.allqj.virtual_number_administrate.business.baseService;


public interface IVirtualNumberCancelBindingBaseService<Data, Result> {
    Result modify(Data data, Integer virtualNumberType);
}
