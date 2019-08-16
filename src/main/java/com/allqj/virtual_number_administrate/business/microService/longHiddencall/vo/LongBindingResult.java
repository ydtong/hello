package com.allqj.virtual_number_administrate.business.microService.longHiddencall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LongBindingResult {
    @ApiModelProperty(value = "隐号号码")
    private String telX;

    @ApiModelProperty(value = "绑定ID")
    private String subid;

    @ApiModelProperty(value = "真实号码")
    private String telA;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String cardno;
}
