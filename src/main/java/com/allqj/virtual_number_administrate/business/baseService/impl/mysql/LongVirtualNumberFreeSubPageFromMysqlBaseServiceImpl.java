package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;


import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberFreeSubPageRequest;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberFreeSubPageResult;
import com.allqj.virtual_number_administrate.business.vo.PageResult;
import com.allqj.virtual_number_administrate.business.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 长隐号空闲子列表
 */
@Service
public class LongVirtualNumberFreeSubPageFromMysqlBaseServiceImpl implements IPageBaseService<LongVirtualNumberFreeSubPageRequest, PageResult<LongVirtualNumberFreeSubPageResult>> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Override
    public PageResult<LongVirtualNumberFreeSubPageResult> page(LongVirtualNumberFreeSubPageRequest request, PageVO pageVO) {
        Sort sort = new Sort(Sort.Direction.DESC, "modifytime");
        Pageable pageable = PageRequest.of(pageVO.getPage() - 1, 1000, sort);
        Page<VirtualNumberMysqlEntity> virtualNumberMysqlEntityPage =
                virtualNumberMysqlRepository.subPage(request.getDeptId(), request.getDeptType(), VirtualNumberTypeEnum.LONG.getCode(), false, pageable);

        List<LongVirtualNumberFreeSubPageResult> longVirtualNumberFreeSubPageResultList = new ArrayList<>();
        virtualNumberMysqlEntityPage.getContent().forEach(virtualNumberMysqlEntity ->
                longVirtualNumberFreeSubPageResultList.add(new LongVirtualNumberFreeSubPageResult(virtualNumberMysqlEntity)));
        return new PageResult<>(
                longVirtualNumberFreeSubPageResultList,
                virtualNumberMysqlEntityPage.getNumber(),
                virtualNumberMysqlEntityPage.getSize(),
                (int) virtualNumberMysqlEntityPage.getTotalElements(),
                virtualNumberMysqlEntityPage.getTotalPages());
    }
}
