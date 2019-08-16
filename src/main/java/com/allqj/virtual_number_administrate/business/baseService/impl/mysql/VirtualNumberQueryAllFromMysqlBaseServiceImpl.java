package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IQueryBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberQueryAllRequest;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 虚拟号按指定数量查询
 */
@Service
public class VirtualNumberQueryAllFromMysqlBaseServiceImpl implements IQueryBaseService<VirtualNumberQueryAllRequest, List<String>> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    /**
     * 查询空闲隐号（按数量匹配）
     *
     * @param virtualNumberQueryAllRequest
     * @param isdelete
     * @param must
     * @return
     */
    @Override
    public List<String> query(VirtualNumberQueryAllRequest virtualNumberQueryAllRequest, Boolean isdelete, Boolean must) {
        Sort sort = new Sort(Sort.Direction.ASC, "modifytime");
        Pageable pageable = PageRequest.of(0, virtualNumberQueryAllRequest.getNumber(), sort);
        List<String> result = virtualNumberMysqlRepository.virtualNumberList(virtualNumberQueryAllRequest, isdelete, pageable).getContent();
        if (must && result.size() < virtualNumberQueryAllRequest.getNumber())
            throw new ResultException(StatusCodeEnum.NUMBER_INSUFFICIENT.getCode(), StatusCodeEnum.NUMBER_INSUFFICIENT.getMessage());
        return result;
    }
}
