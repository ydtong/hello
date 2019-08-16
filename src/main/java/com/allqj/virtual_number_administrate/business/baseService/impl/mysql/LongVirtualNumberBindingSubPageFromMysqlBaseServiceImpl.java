package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;


import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationUserInfoResult;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberBindingSubPageRequest;
import com.allqj.virtual_number_administrate.business.vo.LongVirtualNumberBindingSubPageResult;
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
 * 长隐号绑定子列表
 */
@Service
public class LongVirtualNumberBindingSubPageFromMysqlBaseServiceImpl implements IPageBaseService<LongVirtualNumberBindingSubPageRequest, PageResult<LongVirtualNumberBindingSubPageResult>> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Autowired
    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;

    @Autowired
    private PeopleService peopleService;

    @Override
    public PageResult<LongVirtualNumberBindingSubPageResult> page(LongVirtualNumberBindingSubPageRequest request, PageVO pageVO) {
        Sort sort = new Sort(Sort.Direction.DESC, "modifytime");
        Pageable pageable = PageRequest.of(pageVO.getPage() - 1, 1000, sort);
        Page<VirtualNumberMysqlEntity> virtualNumberMysqlEntityPage =
                virtualNumberMysqlRepository.subPage(request.getDeptId(), request.getDeptType(), VirtualNumberTypeEnum.LONG.getCode(), true, pageable);
        //拼接返回结果
        List<LongVirtualNumberBindingSubPageResult> longVirtualNumberBindingSubPageResultList = new ArrayList<>();
        virtualNumberMysqlEntityPage.getContent().forEach(virtualNumberMysqlEntity ->
        {
            LongVirtualNumberBindingSubPageResult longVirtualNumberBindingSubPageResult = new LongVirtualNumberBindingSubPageResult(virtualNumberMysqlEntity);
            BeanUtils.copyProperties(virtualNumberMysqlEntity, longVirtualNumberBindingSubPageResult);
            userInfo(longVirtualNumberBindingSubPageResult);
            longVirtualNumberBindingSubPageResultList.add(longVirtualNumberBindingSubPageResult);
        });

        return new PageResult<>(
                longVirtualNumberBindingSubPageResultList,
                virtualNumberMysqlEntityPage.getNumber(),
                virtualNumberMysqlEntityPage.getSize(),
                (int) virtualNumberMysqlEntityPage.getTotalElements(),
                virtualNumberMysqlEntityPage.getTotalPages());
    }

    //用户信息
    private void userInfo(LongVirtualNumberBindingSubPageResult longVirtualNumberBindingSubPageResult) {
        VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity =
                virtualNumberBindingInfoMysqlRepository
                        .findByVirtualNumberAndVirtualNumberTypeAndIsdeleteFalse(
                                longVirtualNumberBindingSubPageResult.getVirtualNumber(),
                                VirtualNumberTypeEnum.LONG.getCode());
        if (virtualNumberBindingInfoMysqlEntity == null)
            return;
        OrganizationUserInfoResult organizationUserInfoResult = peopleService.userInfo(virtualNumberBindingInfoMysqlEntity.getUserId()).getResult();
        if (organizationUserInfoResult != null)
            longVirtualNumberBindingSubPageResult.setUserName(organizationUserInfoResult.getUserName());
    }
}
