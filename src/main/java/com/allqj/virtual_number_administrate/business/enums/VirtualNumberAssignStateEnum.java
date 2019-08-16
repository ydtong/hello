package com.allqj.virtual_number_administrate.business.enums;


import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 虚拟号分配状态
 */
@Getter
@AllArgsConstructor
public enum VirtualNumberAssignStateEnum {
    NOT_ASSIGN(false, "未分配"),
    ASSIGN(true, "分配");

    private Boolean code;
    private String describe;

    private static final Map<Boolean, String> virtualNumberAssignStateMap = new HashMap<>();
    private static final List<DictionaryResult<Boolean, String>> dictionaryResultList = new ArrayList<>();

    static {
        for (VirtualNumberAssignStateEnum virtualNumberAssignStateEnum : VirtualNumberAssignStateEnum.values()) {
            dictionaryResultList.add(new DictionaryResult<>(virtualNumberAssignStateEnum.getCode(),
                    virtualNumberAssignStateEnum.getDescribe()));
            virtualNumberAssignStateMap.put(virtualNumberAssignStateEnum.code, virtualNumberAssignStateEnum.describe);
        }
    }

    public static String getDescribe(Integer code) {
        return virtualNumberAssignStateMap.get(code);
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
