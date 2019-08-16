package com.allqj.virtual_number_administrate.business.service;


import com.allqj.virtual_number_administrate.business.vo.*;

import java.util.List;

public interface IMainPhoneService {

    /**
     * 总机号添加
     *
     * @param mainPhoneAddRequest
     * @return
     */
    MainPhonePageResult add(MainPhoneAddRequest mainPhoneAddRequest);

    /**
     * 总机号列表
     *
     * @param mainPhonePageRequest
     * @return
     */
    PageResult<MainPhonePageResult> pageQuery(MainPhonePageRequest mainPhonePageRequest, PageVO pageInfo);

    /**
     * 删除总机号
     *
     * @param mainPhoneDeleteRequest
     * @return
     */
    Boolean delete(MainPhoneDeleteRequest mainPhoneDeleteRequest);

    /**
     * 总机号列表表头
     *
     * @return
     */
    List<PageHeadersResult> pageHeader();

    /**
     * 总机号修改
     *
     * @param mainPhoneModifyRequest
     * @return
     */
    MainPhonePageResult modify(MainPhoneModifyRequest mainPhoneModifyRequest);

    /**
     * 总机号字典
     *
     * @return
     */
    List<DictionaryResult<Integer, String>> dictionary();

    /**
     * 通过code查询总机
     *
     * @param accountCode
     * @return
     */
    MainPhoneQueryResult mainPhoneQuery(Integer accountCode);
}
