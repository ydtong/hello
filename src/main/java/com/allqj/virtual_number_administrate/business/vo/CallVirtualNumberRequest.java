package com.allqj.virtual_number_administrate.business.vo;

import com.allqj.virtual_number_administrate.business.microService.vo.CallbackTopic;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: cj
 * @description 统一拨打请求
 * @date: 2019/3/27 16:44
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CallVirtualNumberRequest {
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
