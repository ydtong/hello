package com.allqj.virtual_number_administrate.business.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryVirtualNumberResult {
    private String workPhone;
    private List<String> virtual;
}
