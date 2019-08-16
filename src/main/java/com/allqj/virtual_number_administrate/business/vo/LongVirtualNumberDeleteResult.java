package com.allqj.virtual_number_administrate.business.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 删除虚拟号返回
 */
@Getter
@Setter
@NoArgsConstructor
public class LongVirtualNumberDeleteResult {
    private List<String> virtualNumberList;
}
