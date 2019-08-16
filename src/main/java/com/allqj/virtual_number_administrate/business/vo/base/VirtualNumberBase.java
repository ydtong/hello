package com.allqj.virtual_number_administrate.business.vo.base;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VirtualNumberBase {
    @ApiModelProperty(value = "虚拟号")
    private String virtualNumber;
    @ApiModelProperty(value = "虚拟号类型")
    private Integer virtualNumberType;
}
