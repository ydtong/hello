package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;


import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdRequest;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdResult;
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
 * 长隐号列表
 */
@Service
public class LongVirtualNumberPageFromMysqlBaseServiceImpl implements IPageBaseService<LongVirtualNumberPageRequest, PageResult<LongVirtualNumberPageResult>> {

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Autowired
    private PeopleService peopleServiceClient;

    /**
     * 长隐号列表
     *
     * @param request
     * @param pageVO
     * @return
     */
    @Override
    public PageResult<LongVirtualNumberPageResult> page(LongVirtualNumberPageRequest request, PageVO pageVO) {
        Sort sort = new Sort(Sort.Direction.DESC, "modifytime");
        Pageable pageable = PageRequest.of(pageVO.getPage() - 1, pageVO.getSize(), sort);
        if (request.getDeptIds() != null) {
            List<Integer> integerList = new ArrayList<>();
            for (int i = 0; i < request.getDeptIds().length; i++) {
                ResultVO<ChildDeptsVo> childDeptsVoResultVO = peopleServiceClient.childDeptById(request.getDeptIds()[i]);
                integerList.addAll(childDeptsVoResultVO.getResult().getChildDepts());
            }
            request.setDeptIds(integerList.toArray(new Integer[integerList.size()]));
        }
        Page<VirtualNumberMysqlEntity> virtualNumberMysqlEntityPage =
                virtualNumberMysqlRepository.longPage(request, VirtualNumberTypeEnum.LONG.getCode(), pageable);
        //拼接返回结果
        List<LongVirtualNumberPageResult> longVirtualNumberPageResultList = new ArrayList<>();
        virtualNumberMysqlEntityPage.getContent().forEach(virtualNumberMysqlEntity ->
        {
            LongVirtualNumberPageResult longVirtualNumberPageResult = new LongVirtualNumberPageResult(virtualNumberMysqlEntity);
            deptInfo(longVirtualNumberPageResult, virtualNumberMysqlEntity);
            longVirtualNumberPageResultList.add(longVirtualNumberPageResult);
        });
        return new PageResult<>(
                longVirtualNumberPageResultList,
                virtualNumberMysqlEntityPage.getNumber(),
                virtualNumberMysqlEntityPage.getSize(),
                (int) virtualNumberMysqlEntityPage.getTotalElements(),
                virtualNumberMysqlEntityPage.getTotalPages());
    }

    //部门信息
    @SuppressWarnings("all")
    private LongVirtualNumberPageResult deptInfo(LongVirtualNumberPageResult longVirtualNumberPageResult, VirtualNumberMysqlEntity virtualNumberMysqlEntity) {
        String deptName = null;
        if (null == virtualNumberMysqlEntity.getDeptId() || null == virtualNumberMysqlEntity.getDeptType()) {
            return longVirtualNumberPageResult;
        }
        //拼接人员库请求
        OrganizationQueryByDeptIdRequest queryByDeptIdRequest = new OrganizationQueryByDeptIdRequest();
        queryByDeptIdRequest.setDeptId(virtualNumberMysqlEntity.getDeptId());
        queryByDeptIdRequest.setDeptType(virtualNumberMysqlEntity.getDeptType());
        //方法内部缓存
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
        longVirtualNumberPageResult.setDeptName(deptName);
        return longVirtualNumberPageResult;
    }
}
