package com.allqj.virtual_number_administrate.business.baseService;

public interface IQueryBaseService<Request, Data> {
    Data query(Request request, Boolean isdelete, Boolean must);
}
