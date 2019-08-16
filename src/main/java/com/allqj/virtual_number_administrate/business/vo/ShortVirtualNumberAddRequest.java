package com.allqj.virtual_number_administrate.business.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortVirtualNumberAddRequest extends VirtualNumberAddRequest {
    @ExcelHead(name = "联通长号(限定号码11位)")
    private String sonVirtualNumber;

    @ApiModelProperty("总机Code")
    private Integer accountCode;

    public void setSonVirtualNumber(String sonVirtualNumber) {
        this.sonVirtualNumber = sonVirtualNumber == null ? null : sonVirtualNumber.trim();
    }

    @ApiModelProperty("身份证号")
    private String idCard;

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }
}
