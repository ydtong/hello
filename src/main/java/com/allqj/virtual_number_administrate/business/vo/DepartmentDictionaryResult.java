package com.allqj.virtual_number_administrate.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: cj
 * @description 查询部门返回结果
 * @date: 2019/3/31 8:58
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDictionaryResult {
    @ApiModelProperty("部门id")
    private Integer deptId;
    @ApiModelProperty("部门类型")
    private String deptType;
    @ApiModelProperty("长号数量")
    private Integer longNumber;
    @ApiModelProperty("绑定长号数量")
    private Integer bindingLongNumber;
    @ApiModelProperty("短号数量")
    private Integer shortNumber;
    @ApiModelProperty("绑定短号数量")
    private Integer bindingShortNumber;
    @ApiModelProperty("是否删除")
    private Boolean isdelete;
}
