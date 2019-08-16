package com.allqj.virtual_number_administrate.business.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 绑定返回
 */
@Getter
@Setter
@NoArgsConstructor
public class BindingVirtualNumberResult {
    private Integer userId;

    private Integer deptId;
    private String deptType;

    private String phone;
    private String virtualNumber;
    private Integer virtualNumberType;
}
