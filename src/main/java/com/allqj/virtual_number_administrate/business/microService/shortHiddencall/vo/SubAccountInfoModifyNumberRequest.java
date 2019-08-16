package com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubAccountInfoModifyNumberRequest {
    @ApiModelProperty(value = "账号code")
    private Integer accountCode;
    @ApiModelProperty(value = "总上限")
    private int totalCapacity;
    @ApiModelProperty(value = "虚拟号数量")
    private Integer virtualNumberCount;
}
