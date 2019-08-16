package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;


import com.allqj.virtual_number_administrate.business.baseService.IPageBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte.PeopleService;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdRequest;
import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.OrganizationQueryByDeptIdResult;
import com.allqj.virtual_number_administrate.business.repository.mysql.IDeptInfoMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.DeptPageRequest;
import com.allqj.virtual_number_administrate.business.vo.DeptPageResult;
import com.allqj.virtual_number_administrate.business.vo.PageResult;
import com.allqj.virtual_number_administrate.business.vo.PageVO;
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
 * 在mysql中查询部门信息列表
 */
@Service
public class DeptPageFromMysqlBaseServiceImpl implements IPageBaseService<DeptPageRequest, PageResult<DeptPageResult>> {

    @Autowired
    private IDeptInfoMysqlRepository deptInfoMysqlRepository;

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Autowired
    private PeopleService peopleServiceClient;

    /**
     * 部门列表
     *
     * @param deptPageRequest
     * @param pageVO
     * @return
     */
    @Override
    public PageResult<DeptPageResult> page(DeptPageRequest deptPageRequest, PageVO pageVO) {
        Sort sort = new Sort(Sort.Direction.DESC, "modifytime");
        Pageable pageable = PageRequest.of(pageVO.getPage() - 1, pageVO.getSize(), sort);
        Page<DeptInfoMysqlEntity> deptPageResultPage = deptInfoMysqlRepository.page(deptPageRequest, pageable);
        List<DeptPageResult> deptPageResultList = new ArrayList<>();
        deptPageResultPage.getContent().forEach(deptInfoMysqlEntity ->
        {
            DeptPageResult deptPageResult = new DeptPageResult(deptInfoMysqlEntity);
            deptInfo(deptPageResult);
            longNumberInfo(deptPageResult);
            shortNumberInfo(deptPageResult);
            deptPageResultList.add(deptPageResult);
        });

        return new PageResult<>(
                deptPageResultList,
                deptPageResultPage.getNumber(),
                deptPageResultPage.getSize(),
                (int) deptPageResultPage.getTotalElements(),
                deptPageResultPage.getTotalPages());
    }

    //部门信息
    private DeptPageResult deptInfo(DeptPageResult deptPageResult) {
        OrganizationQueryByDeptIdRequest queryByDeptIdRequest = new OrganizationQueryByDeptIdRequest();
        queryByDeptIdRequest.setDeptId(deptPageResult.getDeptId());
        queryByDeptIdRequest.setDeptType(deptPageResult.getDeptType());
        //方法内部缓存
        OrganizationQueryByDeptIdResult queryByDeptIdResult = null;
        Map<Integer, OrganizationQueryByDeptIdResult> peopleClientDEptInfoMap = new HashMap<>();
        queryByDeptIdResult = peopleClientDEptInfoMap.get(deptPageResult.getDeptId());
        if (null == queryByDeptIdResult) {
            queryByDeptIdResult = peopleServiceClient.departmentQueryByDeptId(queryByDeptIdRequest).getResult();
            peopleClientDEptInfoMap.put(deptPageResult.getDeptId(), queryByDeptIdResult);
        }
        if (queryByDeptIdResult != null) {
            deptPageResult.setDeptName(queryByDeptIdResult.getDeptName());
            deptPageResult.setPersonInCharge(getPersonInCharge(queryByDeptIdResult.getDeptSuperior()));
            deptPageResult.setEmployeesNumber(queryByDeptIdResult.getNumberHold());
        }
        return deptPageResult;
    }

    //长隐号信息
    private DeptPageResult longNumberInfo(DeptPageResult deptPageResult) {
        Integer bindingCount = virtualNumberMysqlRepository
                .countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(
                        deptPageResult.getDeptId(),
                        deptPageResult.getDeptType(),
                        VirtualNumberTypeEnum.LONG.getCode(),
                        true);
        Integer notBindingCount = virtualNumberMysqlRepository
                .countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(
                        deptPageResult.getDeptId(),
                        deptPageResult.getDeptType(),
                        VirtualNumberTypeEnum.LONG.getCode(),
                        false);
        deptPageResult.setBindingLongVirtualNumber(bindingCount);
        deptPageResult.setNotBindingLongVirtualNumber(notBindingCount);
        return deptPageResult;
    }

    //短隐号信息
    private DeptPageResult shortNumberInfo(DeptPageResult deptPageResult) {
        Integer bindingCount = virtualNumberMysqlRepository
                .countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(
                        deptPageResult.getDeptId(),
                        deptPageResult.getDeptType(),
                        VirtualNumberTypeEnum.SHORT.getCode(),
                        true);
        Integer notBindingCount = virtualNumberMysqlRepository
                .countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(
                        deptPageResult.getDeptId(),
                        deptPageResult.getDeptType(),
                        VirtualNumberTypeEnum.SHORT.getCode(),
                        false);
        deptPageResult.setBindingShortVirtualNumber(bindingCount);
        deptPageResult.setNotBindingShortVirtualNumber(notBindingCount);
        return deptPageResult;
    }

    //拼接负责人
    private String getPersonInCharge(List<String> personInCharges) {
        String mainPeopleName = null;
        if (personInCharges.size() == 1)
            mainPeopleName = personInCharges.get(0);
        if (personInCharges.size() > 1) {
            //斐波那契数列拼接字符串
            String middle = null;
            String mainPeople = personInCharges.get(0);
            String index = " " + "-" + " ";
            for (int i = 1; i < personInCharges.size(); i++) {
                middle = mainPeople + index;
                mainPeople = middle + personInCharges.get(i);
            }
            mainPeopleName = mainPeople;
        }
        return mainPeopleName;
    }
}
