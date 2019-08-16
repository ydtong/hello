package com.allqj.virtual_number_administrate.business.service;

/**
 * 虚拟号管理
 */
public interface IVirtualNumberDeleteService<Request, Result> {
    Result deleteVirtualNumber(Request request);
}
