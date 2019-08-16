package com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.List;

/**
 * @author: cj
 * @description 调用人才库接口返回结果
 * @date: 2019/4/3 10:38
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDepartmentPageResult {
    @ApiModelProperty("部门id")
    private Integer id;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("部门类型")
    private String departmenttype;

    @ApiModelProperty("是否加盟")
    private Boolean direct;

    @ApiModelProperty("父级id")
    private Integer fatherid;

    @ApiModelProperty("父级名称")
    private String fathername;

    @ApiModelProperty("负责人")
    private List<MainPeopleName> superior;

    @ApiModelProperty("更新时间")
    private Date maintaintime;

    @ApiModelProperty("部门状态")
    private Boolean departmentstatus;

    @ApiModelProperty("隐号拨打状态")
    private Boolean hiddennumber;
}
