package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;


import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdRequest;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdResult;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.inte.IShortService;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.SubAccountInfoPageResult;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.SubAccountInfoQueryResult;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 短隐号列表
 */
@Service
public class ShortVirtualNumberPageFromMysqlBaseServiceImpl implements IPageBaseService<ShortVirtualNumberPageRequest, PageResult<ShortVirtualNumberPageResult>> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Autowired
    private PeopleService peopleServiceClient;

    @Autowired
    private IShortService shortService;

    @Override
    public PageResult<ShortVirtualNumberPageResult> page(ShortVirtualNumberPageRequest request, PageVO pageVO) {

        Sort sort = new Sort(Sort.Direction.DESC, "modifytime");
        Pageable pageable = PageRequest.of(pageVO.getPage() - 1, pageVO.getSize(), sort);
        Integer[] accountCodes = null;
        if (request.getVagueParameter() != null && !request.getVagueParameter().isEmpty())
            accountCodes = mainPhoneLike(request.getVagueParameter());
        if (request.getDeptIds() != null) {
            List<Integer> integerList = new ArrayList<>();
            for (int i = 0; i < request.getDeptIds().length; i++) {
                ResultVO<ChildDeptsVo> childDeptsVoResultVO = peopleServiceClient.childDeptById(request.getDeptIds()[i]);
                integerList.addAll(childDeptsVoResultVO.getResult().getChildDepts());
            }
            request.setDeptIds(integerList.toArray(new Integer[integerList.size()]));
        }
        //查询
        Page<VirtualNumberMysqlEntity> virtualNumberMysqlEntityPage =
                virtualNumberMysqlRepository.shortPage(request, VirtualNumberTypeEnum.SHORT.getCode(), accountCodes, pageable);
        //拼接返回结果
        List<ShortVirtualNumberPageResult> shortVirtualNumberPageResultList = new ArrayList<>();
        virtualNumberMysqlEntityPage.getContent().forEach(virtualNumberMysqlEntity ->
        {
            ShortVirtualNumberPageResult shortVirtualNumberPageResult = new ShortVirtualNumberPageResult(virtualNumberMysqlEntity);
            deptInfo(shortVirtualNumberPageResult, virtualNumberMysqlEntity);
            mainPhoneInfo(shortVirtualNumberPageResult, virtualNumberMysqlEntity);
            shortVirtualNumberPageResultList.add(shortVirtualNumberPageResult);
        });
        return new PageResult<>(
                shortVirtualNumberPageResultList,
                virtualNumberMysqlEntityPage.getNumber(),
                virtualNumberMysqlEntityPage.getSize(),
                (int) virtualNumberMysqlEntityPage.getTotalElements(),
                virtualNumberMysqlEntityPage.getTotalPages());
    }

    //部门信息
    @SuppressWarnings("all")
    private ShortVirtualNumberPageResult deptInfo(ShortVirtualNumberPageResult shortVirtualNumberPageResult, VirtualNumberMysqlEntity virtualNumberMysqlEntity) {
        String deptName = null;
        if (null == virtualNumberMysqlEntity.getDeptId() || null == virtualNumberMysqlEntity.getDeptType()) {
            shortVirtualNumberPageResult.setDeptName(deptName);
            return shortVirtualNumberPageResult;
        }
        OrganizationQueryByDeptIdRequest queryByDeptIdRequest = new OrganizationQueryByDeptIdRequest();
        queryByDeptIdRequest.setDeptId(virtualNumberMysqlEntity.getDeptId());
        queryByDeptIdRequest.setDeptType(virtualNumberMysqlEntity.getDeptType());
        Map<Integer, String> peopleClientDeptNameMap = new HashMap<>();
        deptName = peopleClientDeptNameMap.get(virtualNumberMysqlEntity.getDeptId());
        if (null == deptName) {
            OrganizationQueryByDeptIdResult queryByDeptIdResult = peopleServiceClient.departmentQueryByDeptId(queryByDeptIdRequest).getResult();
            try {
                deptName = queryByDeptIdResult.getDeptName();
            } catch (Exception e) {

            }
            peopleClientDeptNameMap.put(virtualNumberMysqlEntity.getDeptId(), deptName);
        }
        shortVirtualNumberPageResult.setDeptName(deptName);
        return shortVirtualNumberPageResult;
    }

    //总机号信息
    private ShortVirtualNumberPageResult mainPhoneInfo(ShortVirtualNumberPageResult shortVirtualNumberPageResult, VirtualNumberMysqlEntity virtualNumberMysqlEntity) {
        Map<Integer, String> mainPhoneInfoMap = new HashMap<>();
        String mainPhone = mainPhoneInfoMap.get(virtualNumberMysqlEntity.getAccountCode());
        if (null == mainPhone) {
            SubAccountInfoQueryResult subAccountInfoQueryResult =
                    shortService.subAccountQueryFromAccountCode(virtualNumberMysqlEntity.getAccountCode()).getResult();
            try {
                mainPhone = subAccountInfoQueryResult.getMainPhone();
            } catch (Exception e) {

            }
            mainPhoneInfoMap.put(virtualNumberMysqlEntity.getAccountCode(), mainPhone);
        }
        shortVirtualNumberPageResult.setMainPhone(mainPhone);
        return shortVirtualNumberPageResult;
    }

    //模糊字段获取总机号code
    private Integer[] mainPhoneLike(String mainPhone) {
        //总机号模糊查询
        ResultVO<List<SubAccountInfoPageResult>> listResultVO = shortService.subAccountLikeMainPhone(mainPhone);
        if (null == listResultVO.getResult())
            return null;
        Integer[] integerList = new Integer[listResultVO.getResult().size()];
        Integer i = 0;
        for (SubAccountInfoPageResult subAccountInfoPageResult : listResultVO.getResult()) {
            integerList[i] = subAccountInfoPageResult.getAccountCode();
            i++;
        }
        return integerList;
    }

}
