package com.allqj.virtual_number_administrate.business.baseService;


public interface IVirtualNumberBindingBaseService<Data, Result> {
    Result modify(Data data, Integer virtualNumberType);
}
