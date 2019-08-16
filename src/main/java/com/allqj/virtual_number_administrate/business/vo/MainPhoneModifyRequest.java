package com.allqj.virtual_number_administrate.business.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainPhoneModifyRequest {
    @ApiModelProperty(value = "账号code")
    private Integer accountCode;
    @ApiModelProperty(value = "账号id")
    private String accountid;
    @ApiModelProperty(value = "账号token")
    private String token;
    @ApiModelProperty(value = "总上限")
    private int totalCapacity;
    @ApiModelProperty(value = "总机号")
    private String mainPhone = "";
    @ApiModelProperty(value = "描述")
    private String remark = "";
}
