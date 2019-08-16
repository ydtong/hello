package com.allqj.virtual_number_administrate.business.vo;

import com.allqj.virtual_number_administrate.business.vo.base.DeptBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 删除虚拟号请求
 */
@Getter
@Setter
public class DeleteVirtualNumberRequest extends DeptBase {
    @ApiModelProperty(value = "部门")
    private String department;
    @ApiModelProperty(value = "虚拟号类型")
    private Integer virtualNumberType;
    @ApiModelProperty(value = "虚拟号")
    private List<String> virtualNumberList;
}
