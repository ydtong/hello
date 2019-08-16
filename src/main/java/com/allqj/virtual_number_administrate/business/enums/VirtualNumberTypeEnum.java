package com.allqj.virtual_number_administrate.business.enums;


import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@Getter
@AllArgsConstructor
public enum VirtualNumberTypeEnum {
    SHORT(0, "短号") {
        /**
         * 更新部门是否存在空闲短号状态
         * @param deptInfoMysqlEntity
         * @param virtualNumberMysqlRepository
         * @return
         */
        @Override
        public DeptInfoMysqlEntity updateDeptInfoMysqlEntity(DeptInfoMysqlEntity deptInfoMysqlEntity, IVirtualNumberMysqlRepository virtualNumberMysqlRepository) {
            //获得部门未绑定隐号数量
            Integer freeCount = virtualNumberMysqlRepository
                    .countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(deptInfoMysqlEntity.getDeptId(),
                            deptInfoMysqlEntity.getDeptType(),
                            VirtualNumberTypeEnum.SHORT.getCode(),
                            false);
            deptInfoMysqlEntity.setIsFreeShortNumber(freeCount > 0);
            return deptInfoMysqlEntity;
        }
    },
    LONG(1, "长号") {
        /**
         * 更新部门是否存在空闲长号状态
         * @param deptInfoMysqlEntity
         * @param virtualNumberMysqlRepository
         * @return
         */
        @Override
        public DeptInfoMysqlEntity updateDeptInfoMysqlEntity(DeptInfoMysqlEntity deptInfoMysqlEntity, IVirtualNumberMysqlRepository virtualNumberMysqlRepository) {
            //获得部门未绑定隐号数量
            Integer freeCount = virtualNumberMysqlRepository
                    .countByDeptIdAndDeptTypeAndUtypeAndIsBindingAndIsdeleteFalse(deptInfoMysqlEntity.getDeptId(),
                            deptInfoMysqlEntity.getDeptType(),
                            VirtualNumberTypeEnum.LONG.getCode(),
                            false);
            deptInfoMysqlEntity.setIsFreeLongNumber(freeCount > 0);
            return deptInfoMysqlEntity;
        }
    };

    private Integer code;
    private String describe;

    //字典表
    private static final List<DictionaryResult<Integer, String>> dictionaryResultList = new ArrayList<>();

    /**
     * 获得字典表
     *
     * @return
     */
    public static List<DictionaryResult<Integer, String>> getDictionaryResult() {
        return dictionaryResultList;
    }

    //存放枚举map
    private static final Map<Integer, VirtualNumberTypeEnum> virtualNumberTypeEnumHashMap = new HashMap<>();

    /**
     * 获得枚举的类型（长号or短号）
     *
     * @param code
     * @return
     */
    public static VirtualNumberTypeEnum getVirtualNumberTypeEnum(Integer code) {
        VirtualNumberTypeEnum virtualNumberTypeEnum = virtualNumberTypeEnumHashMap.get(code);
        if (virtualNumberTypeEnum == null)
            throw new ResultException(StatusCodeEnum.REQUEST_ERROR.getCode(), StatusCodeEnum.REQUEST_ERROR.getMessage());
        return virtualNumberTypeEnum;
    }

    //遍历枚举map
    static {
        for (VirtualNumberTypeEnum virtualNumberTypeEnum : VirtualNumberTypeEnum.values()) {
            dictionaryResultList.add(new DictionaryResult<>(virtualNumberTypeEnum.getCode(),
                    virtualNumberTypeEnum.getDescribe()));
            virtualNumberTypeEnumHashMap.put(virtualNumberTypeEnum.getCode(), virtualNumberTypeEnum);
        }
    }

    /**
     * 更新部门是否存在空闲长号状态
     *
     * @param deptInfoMysqlEntity
     * @param virtualNumberMysqlRepository
     * @return
     */
    public abstract DeptInfoMysqlEntity updateDeptInfoMysqlEntity(DeptInfoMysqlEntity deptInfoMysqlEntity, IVirtualNumberMysqlRepository virtualNumberMysqlRepository);
}
