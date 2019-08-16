package com.allqj.virtual_number_administrate.business.microService.pzhframeBaseOrganization.vo;

import lombok.*;

import java.util.List;

/**
 * @author: cj
 * @description TODO
 * @date: 2019/4/3 14:50
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentPageVO {
    private Integer pageNum;
    private Long totalCount;
    private List<OrganizationDepartmentPageResult> list;
}
