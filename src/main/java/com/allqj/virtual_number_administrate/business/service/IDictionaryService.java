package com.allqj.virtual_number_administrate.business.service;

import com.allqj.virtual_number_administrate.business.vo.DeptVirtualNumberWhereDictionary;
import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
import com.allqj.virtual_number_administrate.business.vo.WhereDictionary;

import java.util.List;


/**
 * 字典表
 */
public interface IDictionaryService {
    /**
     * 虚拟号类型
     *
     * @return
     */
    List<DictionaryResult<Integer, String>> virtualNumberType();

    /**
     * 部门虚拟号查询条件
     *
     * @return
     */
    List<DictionaryResult<Integer, String>> deptVirtualNumberWhere();

    /**
     * 虚拟号分配状态
     *
     * @return
     */
    List<DictionaryResult<Boolean, String>> virtualNumberAssignState();

    /**
     * 虚拟号绑定状态
     *
     * @return
     */
    List<DictionaryResult<Boolean, String>> virtualNumberBindingState();

    /**
     * 联通长号存在状态
     *
     * @return
     */
    List<DictionaryResult<Integer, String>> sonVirtualNumberState();

}
