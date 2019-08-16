package com.allqj.virtual_number_administrate.business.vo;


import com.allqj.virtual_number_administrate.business.vo.base.DeptBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CancelNumberAssignRequest extends DeptBase {
    @ApiModelProperty(value = "虚拟号类型")
    private Integer virtualNumberType;

    @ApiModelProperty(value = "虚拟号数量")
    private Integer number;
}
