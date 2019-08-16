package com.allqj.virtual_number_administrate.business.enums;


import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 虚拟号绑定状态
 */
@Getter
@AllArgsConstructor
public enum VirtualNumberBindingStateEnum {
    FREE(false, "空闲"),
    OCCUPY(true, "占用");


    private Boolean code;
    private String describe;
    private static final Map<Boolean, String> virtualNumberBindingStateMap = new HashMap<>();
    private static final List<DictionaryResult<Boolean, String>> dictionaryResultList = new ArrayList<>();

    static {
        for (VirtualNumberBindingStateEnum virtualNumberBindingStateEnum : VirtualNumberBindingStateEnum.values()) {
            dictionaryResultList.add(new DictionaryResult<>(virtualNumberBindingStateEnum.getCode(),
                    virtualNumberBindingStateEnum.getDescribe()));
            virtualNumberBindingStateMap.put(virtualNumberBindingStateEnum.getCode(), virtualNumberBindingStateEnum.getDescribe());
        }
    }

    public static String getDescribe(Boolean code) {
        return virtualNumberBindingStateMap.get(code);
    }

    /**
     * 获取字典表
     *
     * @return
     */
    public static List<DictionaryResult<Boolean, String>> getDictionaryResult() {
        return dictionaryResultList;
    }
}
