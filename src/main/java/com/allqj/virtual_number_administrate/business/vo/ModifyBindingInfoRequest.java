package com.allqj.virtual_number_administrate.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: cj
 * @description 部门平级调动修改绑定信息请求
 * @date: 2019/4/21 16:46
 **/
@Getter
@Setter
@NoArgsConstructor
public class ModifyBindingInfoRequest {
    @ApiModelProperty("工作电话")
    @NotEmpty(message = "工作号不能为空")
    private String phone;
    @ApiModelProperty("部门id")
    @NotNull(message = "部门id不能为空")
    private Integer deptId;
    @NotEmpty(message = "部门类型不能为空")
    @ApiModelProperty("部门类型")
    private String deptType;
    @NotEmpty(message = "部门名称不能为空")
    @ApiModelProperty("部门名称")
    private String deptName;
}
