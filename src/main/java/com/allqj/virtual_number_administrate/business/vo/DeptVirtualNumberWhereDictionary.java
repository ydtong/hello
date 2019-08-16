package com.allqj.virtual_number_administrate.business.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeptVirtualNumberWhereDictionary {
    @ApiModelProperty(value = "是否有空闲短号")
    private Boolean isFreeShortNumber;
    @ApiModelProperty(value = "是否有空闲长号")
    private Boolean isFreeLongNumber;
}
