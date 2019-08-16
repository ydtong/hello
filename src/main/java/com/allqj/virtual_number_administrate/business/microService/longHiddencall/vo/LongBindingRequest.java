package com.allqj.virtual_number_administrate.business.microService.longHiddencall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LongBindingRequest {
    @ApiModelProperty(value = "隐号号码(必传)")
    private String telX;
    @ApiModelProperty(value = "真实号码(必传)")
    private String telA;
    @ApiModelProperty(value = "姓名(必传)")
    private String name;
    @ApiModelProperty(value = "身份证号(必传)")
    private String cardno;
}
