package com.allqj.virtual_number_administrate.business.microService.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallReqest {
    @ApiModelProperty("虚拟号类型")
    private Integer virtualNumberType;

    @ApiModelProperty(value = "主叫电话（真实号码）")
    private String caller;

    public void setCaller(String caller) {
        this.caller = caller == null ? null : caller.trim();
    }

    @ApiModelProperty(value = "被叫电话（真实号码）")
    private String called;

    public void setCalled(String called) {
        this.called = called == null ? null : called.trim();
    }

    @ApiModelProperty(value = "回调标识")
    private CallbackTopic callbackTopic;

    @ApiModelProperty(value = "可选数据（回调时返回）")
    private String userData;
}
