package com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: cj
 * @description 调用人才库部门接口请求
 * @date: 2019/4/3 10:39
 **/


@Setter
@Getter
public class OrganizationDepartmentPageRequest {
    @ApiModelProperty("部门类型")
    private String departmenttype;

    @ApiModelProperty("是否加盟")
    private Integer direct;

    @ApiModelProperty("隐号拨打状态")
    private Integer hiddennunmber;

    @ApiModelProperty("部门id")
    private Integer id;

    @ApiModelProperty("部门名称，长度必须大于零")
    private String keyWord;

    @ApiModelProperty("部门状态：停用或启用")
    private Integer status;

    @ApiModelProperty("部门主管")
    private Integer superior;
}
