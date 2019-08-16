package com.allqj.virtual_number_administrate.business.baseService;

import com.allqj.virtual_number_administrate.business.vo.PageVO;

public interface IPageBaseService<Request, Result> {
    Result page(Request request, PageVO pageVO);
}
