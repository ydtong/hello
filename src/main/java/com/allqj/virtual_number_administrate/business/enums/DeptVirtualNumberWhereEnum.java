package com.allqj.virtual_number_administrate.business.enums;


import com.allqj.virtual_number_administrate.business.vo.DeptVirtualNumberWhereDictionary;
import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
import com.allqj.virtual_number_administrate.business.vo.WhereDictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum DeptVirtualNumberWhereEnum {
    FREE_SHORT(1, "有空闲短号", new DeptVirtualNumberWhereDictionary(true, null)),
    FREE_LONG(2, "有空闲长号", new DeptVirtualNumberWhereDictionary(null, true)),
    NO_FREE_SHORT(3, "无空闲短号", new DeptVirtualNumberWhereDictionary(false, null)),
    NO_FREE_LONG(4, "无空闲长号", new DeptVirtualNumberWhereDictionary(null, false));

    private Integer code;
    private String describe;
    private DeptVirtualNumberWhereDictionary deptVirtualNumberWhereDictionary;

    private static final List<DictionaryResult<Integer, String>> dictionaryList = new ArrayList<>();
    private static final Map<Integer, DeptVirtualNumberWhereEnum> deptVirtualNumberWhereEnumMap = new HashMap<>();

    static {
        for (DeptVirtualNumberWhereEnum deptVirtualNumberWhereEnum : DeptVirtualNumberWhereEnum.values()) {
            dictionaryList.add(new DictionaryResult<>(deptVirtualNumberWhereEnum.code, deptVirtualNumberWhereEnum.describe));
            deptVirtualNumberWhereEnumMap.put(deptVirtualNumberWhereEnum.code, deptVirtualNumberWhereEnum);
        }
    }

    public static List<DictionaryResult<Integer, String>> getDictionaryList() {
        return dictionaryList;
    }

    public static DeptVirtualNumberWhereEnum getDeptVirtualNumberWhereEnum(Integer code) {
        return deptVirtualNumberWhereEnumMap.get(code);
    }
}
