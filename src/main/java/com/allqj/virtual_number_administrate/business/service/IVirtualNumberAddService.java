package com.allqj.virtual_number_administrate.business.service;

import java.util.List;

/**
 * 虚拟号管理
 */
public interface IVirtualNumberAddService<Request, ExcelRequest, Result> {
    Result add(Request request);

    List<Result> excelAdd(List<ExcelRequest> requestList);
}
