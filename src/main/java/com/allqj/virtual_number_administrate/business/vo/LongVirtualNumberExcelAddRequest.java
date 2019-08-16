package com.allqj.virtual_number_administrate.business.vo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LongVirtualNumberExcelAddRequest extends VirtualNumberAddRequest {
    @ExcelHead(name = "身份证号")
    private String idCard;
}
