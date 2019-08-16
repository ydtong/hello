package com.allqj.virtual_number_administrate.business.service;

/**
 * @author: cj
 * @description 隐号修改接口
 * @date: 2019/4/30 13:49
 **/

public interface IVirtualNumberModifyService<Request, Result> {
    Result modify(Request request, Integer virtualNumberType);
}
