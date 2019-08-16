package com.allqj.virtual_number_administrate.business.vo;

import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBindingQueryBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VirtualNumberBindingQueryResult {

    private String virtualNumber;
    private String describe;
    private Integer virtualNumberType;

    public VirtualNumberBindingQueryResult(VirtualNumberBindingQueryBase virtualNumberBindingQueryBase, VirtualNumberTypeEnum virtualNumberTypeEnum) {
        this.virtualNumber = virtualNumberBindingQueryBase.getVirtualNumber();
        if (virtualNumberBindingQueryBase.getSonVirtualNumber() != null && !virtualNumberBindingQueryBase.getSonVirtualNumber().isEmpty())
            this.virtualNumber = virtualNumberBindingQueryBase.getSonVirtualNumber();
        if (virtualNumberTypeEnum != null) {
            this.describe = virtualNumberTypeEnum.getDescribe();
            this.virtualNumberType = virtualNumberTypeEnum.getCode();
        }
    }
}
