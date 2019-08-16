package com.allqj.virtual_number_administrate.business.vo;


import com.allqj.virtual_number_administrate.util.excelOperation.TemplateEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VirtualNumberAddRequest extends TemplateEntity {
    @ExcelHead(name = "虚拟号(限定号码11位)")
    private String virtualNumber;

    public String getVirtualNumber() {
        return virtualNumber.trim();
    }

    public void setVirtualNumber(String virtualNumber) {
        this.virtualNumber = virtualNumber == null ? null : virtualNumber.trim();
    }
}
