package com.allqj.virtual_number_administrate.business.microService.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CallbackTopic {
    @ApiModelProperty(value = "响铃")
    private String ringTopic;

    @ApiModelProperty(value = "接通")
    private String connectTopic;

    @ApiModelProperty(value = "挂断")
    private String hangUpTopic;
}
