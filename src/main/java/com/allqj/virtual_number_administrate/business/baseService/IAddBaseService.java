package com.allqj.virtual_number_administrate.business.baseService;


public interface IAddBaseService<Request, Data> {
    Data add(Request request, Integer virtualNumberType);
}
