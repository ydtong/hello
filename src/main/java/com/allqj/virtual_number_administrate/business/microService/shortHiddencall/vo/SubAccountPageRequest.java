package com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubAccountPageRequest {
    private String mainPhone;
    @ApiModelProperty(value = "当前页")
    private Integer page;
    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;
}
