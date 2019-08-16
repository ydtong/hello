package com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.inte;

import com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo.*;
import com.allqj.virtual_number_administrate.business.vo.ChildDeptsVo;
import com.allqj.virtual_number_administrate.business.vo.QueryPageVO;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: cj
 * @description 人才库接口
 * @date: 2019/4/3 10:03
 **/
//,url = "http://192.168.10.200:8108"
@Component
@FeignClient(name = "organization")
public interface PeopleService {
    @PostMapping(value = "/v1/organization/department/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<DepartmentPageVO> departmentPage(@RequestBody QueryPageVO<OrganizationDepartmentPageRequest> queryPageVO);

    @PostMapping(value = "/v1/basic/virtual/query/dept/ByDeptId", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<OrganizationQueryByDeptIdResult> departmentQueryByDeptId(@RequestBody OrganizationQueryByDeptIdRequest queryByDeptIdRequest);

    @GetMapping("/v1/basic/query/peopleById/{basicId}")
    ResultVO<OrganizationUserInfoResult> userInfo(@PathVariable("basicId") Integer basicId);

    @GetMapping("/v1/organization/department/info/{id}")
    ResultVO<OrganizationDeptInfoResult> deptInfo(@PathVariable("id") Integer id);

    @GetMapping("/v1/basic/virtual/query/childDeptById/{deptId}")
    ResultVO<ChildDeptsVo> childDeptById(@PathVariable("deptId") Integer deptId);
}
