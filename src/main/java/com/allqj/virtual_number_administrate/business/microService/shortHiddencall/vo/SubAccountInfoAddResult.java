package com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class SubAccountInfoAddResult {
    private Integer accountCode;
    @ApiModelProperty(value = "总机号")
    private String mainPhone = "";
    @ApiModelProperty(value = "号码的数量")
    private Integer phoneNumber;
    @ApiModelProperty(value = "当前使用")
    private Integer consumeCapacity;
    @ApiModelProperty(value = "总上限")
    private Integer totalCapacity;
    @ApiModelProperty(value = "描述")
    private String remark = "";
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifytime;
}
