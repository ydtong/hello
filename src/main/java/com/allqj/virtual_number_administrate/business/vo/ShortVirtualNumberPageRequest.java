package com.allqj.virtual_number_administrate.business.vo;


import com.allqj.virtual_number_administrate.business.enums.SonVirtualNumberStateEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * 虚拟号管理列表请求
 */
@Getter
@Setter
public class ShortVirtualNumberPageRequest extends VirtualNumberPageRequest {
    @ApiModelProperty(value = "总机号code")
    private Integer accountCode;
    @ApiModelProperty(value = "短号的虚拟长号是否存在")
    private Integer existSonVirtualNumber;

    public void setExistSonVirtualNumber(Integer existSonVirtualNumber) {
        this.sonVirtualNumberWhere = SonVirtualNumberStateEnum.getSonVirtualNumberWhere(existSonVirtualNumber);
    }

    @ApiModelProperty(value = "长号Integer转化为Boolean,数据库查询条件")
    @JsonIgnore
    private Boolean sonVirtualNumberWhere;
}
