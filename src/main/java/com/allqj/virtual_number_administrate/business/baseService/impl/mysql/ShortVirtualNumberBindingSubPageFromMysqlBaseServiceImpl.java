package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;


import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationUserInfoResult;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberBindingInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
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
 * 短隐号绑定子列表
 */
@Service
public class ShortVirtualNumberBindingSubPageFromMysqlBaseServiceImpl implements IPageBaseService<ShortVirtualNumberBindingSubPageRequest, PageResult<ShortVirtualNumberBindingSubPageResult>> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Autowired
    private IVirtualNumberBindingInfoMysqlRepository virtualNumberBindingInfoMysqlRepository;

    @Autowired
    private PeopleService peopleService;

    @Override
    public PageResult<ShortVirtualNumberBindingSubPageResult> page(ShortVirtualNumberBindingSubPageRequest request, PageVO pageVO) {
        Sort sort = new Sort(Sort.Direction.DESC, "modifytime");
        Pageable pageable = PageRequest.of(pageVO.getPage() - 1, 1000, sort);
        Page<VirtualNumberMysqlEntity> virtualNumberMysqlEntityPage =
                virtualNumberMysqlRepository.subPage(request.getDeptId(), request.getDeptType(), VirtualNumberTypeEnum.SHORT.getCode(), true, pageable);

        List<ShortVirtualNumberBindingSubPageResult> virtualNumberMysqlEntityList = new ArrayList<>();
        virtualNumberMysqlEntityPage.getContent().forEach(virtualNumberMysqlEntity ->
        {
            ShortVirtualNumberBindingSubPageResult shortVirtualNumberBindingSubPageResult = new ShortVirtualNumberBindingSubPageResult();
            BeanUtils.copyProperties(virtualNumberMysqlEntity, shortVirtualNumberBindingSubPageResult);
            userInfo(shortVirtualNumberBindingSubPageResult);
            virtualNumberMysqlEntityList.add(shortVirtualNumberBindingSubPageResult);
        });
        return new PageResult<>(
                virtualNumberMysqlEntityList,
                virtualNumberMysqlEntityPage.getNumber(),
                virtualNumberMysqlEntityPage.getSize(),
                (int) virtualNumberMysqlEntityPage.getTotalElements(),
                virtualNumberMysqlEntityPage.getTotalPages());
    }

    //用户信息
    private void userInfo(ShortVirtualNumberBindingSubPageResult shortVirtualNumberBindingSubPageResult) {
        VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity =
                virtualNumberBindingInfoMysqlRepository
                        .findByVirtualNumberAndVirtualNumberTypeAndIsdeleteFalse(
                                shortVirtualNumberBindingSubPageResult.getVirtualNumber(),
                                VirtualNumberTypeEnum.SHORT.getCode());
        if (virtualNumberBindingInfoMysqlEntity == null)
            return;
        OrganizationUserInfoResult organizationUserInfoResult = peopleService.userInfo(virtualNumberBindingInfoMysqlEntity.getUserId()).getResult();
        if (organizationUserInfoResult != null)
            shortVirtualNumberBindingSubPageResult.setUserName(organizationUserInfoResult.getUserName());
    }
}
