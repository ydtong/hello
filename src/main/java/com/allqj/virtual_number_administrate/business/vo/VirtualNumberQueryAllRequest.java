package com.allqj.virtual_number_administrate.business.vo;


import com.allqj.virtual_number_administrate.business.vo.base.DeptBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VirtualNumberQueryAllRequest extends DeptBase {
    @ApiModelProperty("是否分配")
    private Boolean isAssign;
    @ApiModelProperty("是否绑定")
    private Boolean isBinding;
    @ApiModelProperty(value = "虚拟号类型")
    private Integer virtualNumberType;
    @ApiModelProperty(value = "查询数量")
    private Integer number;
}
