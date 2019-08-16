package com.allqj.virtual_number_administrate.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: cj
 * @description 统一拨打返回结果
 * @date: 2019/3/27 16:42
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CallVirtualNumberResult {
    @ApiModelProperty(value = "呼叫 Id，通话的唯一性标识。")
    private String callId;

    @ApiModelProperty(value = "总机号码")
    private String switchNumber;

    @ApiModelProperty(value = "呼叫创建时间")
    private String createTime;
}


