package com.allqj.virtual_number_administrate.business.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WhereDictionary<T> {
    private Integer code;
    private String describe;
    private T data;
}
