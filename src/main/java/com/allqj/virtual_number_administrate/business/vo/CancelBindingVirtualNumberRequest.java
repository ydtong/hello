package com.allqj.virtual_number_administrate.business.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * 取消绑定请求
 */
@Getter
@Setter
public class CancelBindingVirtualNumberRequest {
    @ApiModelProperty("工作电话")
    private String phone;
    @ApiModelProperty("虚拟号")
    private String virtualNumber;
    @ApiModelProperty("虚拟号类型")
    private Integer virtualNumberType;
}
