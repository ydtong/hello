package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberHistoryRequest;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberHistoryResult;
import com.allqj.virtual_number_administrate.business.vo.PageResult;
import com.allqj.virtual_number_administrate.business.vo.PageVO;
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
 * @description 长隐号历史记录列表
 * @date: 2019/4/11 11:22
 **/
@Service
public class LongVirtualNumberHistoryPageFromMysqlBaseServiceImpl implements IPageBaseService<LongVirtualNumberHistoryRequest, PageResult<LongVirtualNumberHistoryResult>> {
    @Autowired
    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;

    @Override
    public PageResult<LongVirtualNumberHistoryResult> page(LongVirtualNumberHistoryRequest longVirtualNumberHistoryRequest, PageVO pageVO) {
        Sort sort = new Sort(Sort.Direction.DESC, "modifytime");
        Pageable pageable = PageRequest.of(pageVO.getPage() - 1, pageVO.getSize(), sort);

        Page<VirtualNumberBindingInfoMysqlEntity> virtualNumberBindingInfoMysqlEntityPage =
                virtualNumberBindingInfoMysqlRepository.page(longVirtualNumberHistoryRequest.getVirtualNumber(), VirtualNumberTypeEnum.LONG.getCode(), pageable);
        List<LongVirtualNumberHistoryResult> longVirtualNumberHistoryResultList = new ArrayList<>();

        virtualNumberBindingInfoMysqlEntityPage.forEach(o -> {
                    LongVirtualNumberHistoryResult longVirtualNumberHistoryResult = new LongVirtualNumberHistoryResult();
                    BeanUtils.copyProperties(o, longVirtualNumberHistoryResult);
                    longVirtualNumberHistoryResultList.add(longVirtualNumberHistoryResult);
                }
        );

        return new PageResult<>(
                longVirtualNumberHistoryResultList,
                virtualNumberBindingInfoMysqlEntityPage.getNumber(),
                virtualNumberBindingInfoMysqlEntityPage.getSize(),
                (int) virtualNumberBindingInfoMysqlEntityPage.getTotalElements(),
                virtualNumberBindingInfoMysqlEntityPage.getTotalPages()
        );
    }


}
