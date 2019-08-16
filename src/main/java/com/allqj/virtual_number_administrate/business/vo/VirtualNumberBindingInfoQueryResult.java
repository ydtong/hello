package com.allqj.virtual_number_administrate.business.vo;

import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBindingQueryBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VirtualNumberBindingInfoQueryResult {

    private List<VirtualNumberBindingQueryBase> virtualNumberInfoList;
    private String describe;
    private Integer virtualNumberType;

    public VirtualNumberBindingInfoQueryResult(List<VirtualNumberBindingQueryBase> virtualNumberInfoList, VirtualNumberTypeEnum virtualNumberTypeEnum) {
        this.virtualNumberInfoList = virtualNumberInfoList;
        if (virtualNumberTypeEnum != null) {
            this.describe = virtualNumberTypeEnum.getDescribe();
            this.virtualNumberType = virtualNumberTypeEnum.getCode();
        }
    }
}
