package com.allqj.virtual_number_administrate.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: cj
 * @description 短隐号修改身份证号请求
 * @date: 2019/4/30 13:38
 **/
@Getter
@Setter
@NoArgsConstructor
public class VirtualNumberModifyIdCardRequest {
    @ApiModelProperty("虚拟号")
    private String virtualNumber;
    @ApiModelProperty("虚拟号类型")
    private Integer virtualNumberType;
    @ApiModelProperty("身份证号")
    private String idCard;
}
