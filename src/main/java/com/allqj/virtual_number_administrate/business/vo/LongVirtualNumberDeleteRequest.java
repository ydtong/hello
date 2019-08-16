package com.allqj.virtual_number_administrate.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 删除虚拟号请求
 */
@Getter
@Setter
public class LongVirtualNumberDeleteRequest {
    @ApiModelProperty(value = "虚拟号")
    private List<String> virtualNumberList;
}
