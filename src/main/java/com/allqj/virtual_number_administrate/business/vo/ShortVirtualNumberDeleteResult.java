package com.allqj.virtual_number_administrate.business.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除虚拟号返回
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShortVirtualNumberDeleteResult {
    private List<String> virtualNumberList;
}
