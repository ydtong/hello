package com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo;

import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SubAccountInfoPageResult {
    private Integer accountCode;
    private String mainPhone = "";
    private Integer phoneNumber;
    private Integer consumeCapacity;
    private Integer totalCapacity;
    private String remark = "";
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifytime;

    @ApiModelProperty(value = "虚拟号数量")
    private Integer virtualNumberCount;

}
