package com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: cj
 * @description TODO
 * @date: 2019/4/6 13:09
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationQueryByDeptIdRequest {
    @ApiModelProperty("部门id")
    private Integer deptId;
    @ApiModelProperty("部门类型")
    private String deptType;
}
