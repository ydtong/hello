package com.allqj.virtual_number_administrate.business.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * 绑定请求
 */
@Getter
@Setter
public class BindingVirtualNumberRequest {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("工作电话")
    private String phone;
    @ApiModelProperty("虚拟号")
    private String virtualNumber;
    @ApiModelProperty("虚拟号类型")
    private Integer virtualNumberType;
    @ApiModelProperty("总机Code")
    private Integer accountCode;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("身份证")
    private String cardno;
}
