package com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ShortBindingRequest {
    @ApiModelProperty(value = "真实号码")
    private String phone;
    @ApiModelProperty(value = "直线号（虚拟号）")
    private String directNumber;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "子账户id")
    private Integer accountCode;
}
