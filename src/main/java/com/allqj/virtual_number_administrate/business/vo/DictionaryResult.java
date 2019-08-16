package com.allqj.virtual_number_administrate.business.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 字典表
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryResult<Key, Value> {
    @ApiModelProperty(value = "值")
    private Key code;
    @ApiModelProperty(value = "描述")
    private Value describe;
}
