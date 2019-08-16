package com.allqj.virtual_number_administrate.business.enums;

import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: cj
 * @description 联通长号存在状态枚举
 * @date: 2019/4/13 17:24
 **/
@Getter
@AllArgsConstructor
public enum SonVirtualNumberStateEnum {
    EXIST(0, "不存在", false),
    NOTEXIST(1, "存在", true);

    private Integer code;
    private String describe;
    private Boolean sonVirtualNumberWhere;

    private static final Map<Integer, SonVirtualNumberStateEnum> sonVirtualNumberStateMap = new HashMap<>();
    private static final List<DictionaryResult<Integer, String>> dictionaryResultList = new ArrayList<>();

    static {
        for (SonVirtualNumberStateEnum sonVirtualNumberStateEnum : SonVirtualNumberStateEnum.values()) {
            dictionaryResultList.add(new DictionaryResult<>(sonVirtualNumberStateEnum.getCode(),
                    sonVirtualNumberStateEnum.getDescribe()));
            sonVirtualNumberStateMap.put(sonVirtualNumberStateEnum.getCode(), sonVirtualNumberStateEnum);
        }
    }

    /**
     * 获得字典表
     *
     * @return
     */
    public static List<DictionaryResult<Integer, String>> getDictionaryResult() {
        return dictionaryResultList;
    }

    /**
     * 获得联通长号枚举
     *
     * @param code
     * @return
     */
    public static Boolean getSonVirtualNumberWhere(Integer code) {
        if (code == null)
            return null;
        SonVirtualNumberStateEnum sonVirtualNumberStateEnum = sonVirtualNumberStateMap.get(code);
        if (sonVirtualNumberStateEnum == null)
            return null;
        return sonVirtualNumberStateEnum.sonVirtualNumberWhere;
    }

}
