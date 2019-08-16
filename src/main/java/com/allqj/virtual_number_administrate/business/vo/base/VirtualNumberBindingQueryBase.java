package com.allqj.virtual_number_administrate.business.vo.base;

import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberBindingInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VirtualNumberBindingQueryBase {
    private String virtualNumber;
    private String sonVirtualNumber;

    public VirtualNumberBindingQueryBase(VirtualNumberBindingInfoMysqlEntity virtualNumberBindingInfoMysqlEntity, VirtualNumberMysqlEntity virtualNumberMysqlEntity) {
        this.virtualNumber = virtualNumberBindingInfoMysqlEntity.getVirtualNumber();
        this.sonVirtualNumber = virtualNumberMysqlEntity.getSonVirtualNumber();
    }
}
