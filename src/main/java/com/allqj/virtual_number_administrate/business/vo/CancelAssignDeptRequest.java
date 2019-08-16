package com.allqj.virtual_number_administrate.business.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CancelAssignDeptRequest {
    @ApiModelProperty(value = "虚拟号类型")
    private Integer virtualNumberType;
    @ApiModelProperty(value = "虚拟号")
    private List<String> virtualNumberList;
}
