package com.allqj.virtual_number_administrate.business.vo.base;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeptBase {
    @ApiModelProperty(value = "部门Id")
    private Integer deptId;
    @ApiModelProperty(value = "部门类型")
    private String deptType;
}
