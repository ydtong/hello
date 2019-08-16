package com.allqj.virtual_number_administrate.business.microService.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallResult {
    @ApiModelProperty(value = "呼叫 Id，通话的唯一性标识。")
    private String callId;

    @ApiModelProperty(value = "总机号码")
    private String switchNumber;

    @ApiModelProperty(value = "呼叫创建时间")
    private String createTime;
}
