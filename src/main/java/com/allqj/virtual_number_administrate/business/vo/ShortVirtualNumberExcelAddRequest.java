package com.allqj.virtual_number_administrate.business.vo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortVirtualNumberExcelAddRequest extends VirtualNumberAddRequest {
    @ExcelHead(name = "联通长号(限定号码11位)")
    private String sonVirtualNumber;

    //从Excel导入的数据是""，将其转化为null
    public void setSonVirtualNumber(String sonVirtualNumber) {
        this.sonVirtualNumber = sonVirtualNumber == null ? null : sonVirtualNumber.trim();
    }

    public String getSonVirtualNumber() {
        if (null == sonVirtualNumber || sonVirtualNumber.isEmpty())
            return null;
        return sonVirtualNumber.trim();
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null :idCard.trim();
    }

    @ExcelHead(name = "身份证号")
    private String idCard;


    @ExcelHead(name = "总机号(限定号码11位)")
    private String mainPhone;

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone == null ? null : mainPhone.trim();
    }
}
