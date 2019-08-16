package com.allqj.virtual_number_administrate.business.enums;

import com.allqj.virtual_number_administrate.business.service.IVirtualNumberUseService;
import com.allqj.virtual_number_administrate.business.service.impl.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum UserBindingStateEnum {
    LONG_TYPE(VirtualNumberTypeEnum.LONG.getCode(), LongVirtualNumberUseServiceImpl.class),
    SHORT_TYPE(VirtualNumberTypeEnum.SHORT.getCode(), ShortVirtualNumberUserServiceImpl.class);

    private Integer utype;
    private Class<? extends IVirtualNumberUseService> implType;

    private static final Map<Integer, Class<? extends IVirtualNumberUseService>> map = new HashMap<>();

    static {
        for (UserBindingStateEnum callbackEnum : EnumSet.allOf(UserBindingStateEnum.class))
            map.put(callbackEnum.getUtype(), callbackEnum.getImplType());
    }

    public static Class<? extends IVirtualNumberUseService> getImplType(Integer utype) {
        return map.get(utype);
    }
}
