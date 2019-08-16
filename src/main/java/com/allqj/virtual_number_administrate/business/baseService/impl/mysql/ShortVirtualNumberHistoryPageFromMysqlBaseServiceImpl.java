package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: cj
 * @description 短隐号历史记录列表
 * @date: 2019/4/11 11:23
 **/
@Service
public class ShortVirtualNumberHistoryPageFromMysqlBaseServiceImpl implements IPageBaseService<ShortVirtualNumberHistoryRequest, PageResult<ShortVirtualNumberHistoryResult>> {
    @Autowired
    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;

    @Override
    public PageResult<ShortVirtualNumberHistoryResult> page(ShortVirtualNumberHistoryRequest shortVirtualNumberHistoryRequest, PageVO pageVO) {
        Sort sort = new Sort(Sort.Direction.DESC, "modifytime");
        Pageable pageable = PageRequest.of(pageVO.getPage() - 1, pageVO.getSize(), sort);
        //分页查询
        Page<VirtualNumberBindingInfoMysqlEntity> virtualNumberBindingInfoMysqlEntityPage =
                virtualNumberBindingInfoMysqlRepository.page(shortVirtualNumberHistoryRequest.getVirtualNumber(), VirtualNumberTypeEnum.SHORT.getCode(), pageable);
        List<ShortVirtualNumberHistoryResult> shortVirtualNumberHistoryResultList = new ArrayList<>();

        virtualNumberBindingInfoMysqlEntityPage.forEach(o -> {
                    ShortVirtualNumberHistoryResult shortVirtualNumberHistoryResult = new ShortVirtualNumberHistoryResult();
                    BeanUtils.copyProperties(o, shortVirtualNumberHistoryResult);
                    shortVirtualNumberHistoryResultList.add(shortVirtualNumberHistoryResult);
                }
        );
        return new PageResult<>(
                shortVirtualNumberHistoryResultList,
                virtualNumberBindingInfoMysqlEntityPage.getNumber(),
                virtualNumberBindingInfoMysqlEntityPage.getSize(),
                (int) virtualNumberBindingInfoMysqlEntityPage.getTotalElements(),
                virtualNumberBindingInfoMysqlEntityPage.getTotalPages()
        );
    }
}
