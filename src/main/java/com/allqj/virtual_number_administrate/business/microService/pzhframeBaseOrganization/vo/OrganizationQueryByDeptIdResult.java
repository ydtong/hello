package com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author: cj
 * @description TODO
 * @date: 2019/4/6 13:10
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationQueryByDeptIdResult {
    @ApiModelProperty("部门名称")
    private String deptName;
    @ApiModelProperty("部门主管")
    private List<String> deptSuperior;
    @ApiModelProperty("在职人数")
    private Integer numberHold;
}
