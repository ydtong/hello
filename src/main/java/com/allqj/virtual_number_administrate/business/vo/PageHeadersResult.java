package com.allqj.virtual_number_administrate.business.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 表头
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageHeadersResult {
    private String fieldName;
    private String describe;
    private Integer width;
}
