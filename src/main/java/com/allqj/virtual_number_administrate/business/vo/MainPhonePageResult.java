package com.allqj.virtual_number_administrate.business.vo;

import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class MainPhonePageResult {
    private Integer accountCode;
    @HeadersUtil.Headers(name = "总机号码", width = 140)
    private String mainPhone = "";
    @HeadersUtil.Headers(name = "工作电话数量", width = 115)
    private Integer phoneNumber;
    @HeadersUtil.Headers(name = "短隐号占用数量", width = 110)
    private Integer consumeCapacity;
    @HeadersUtil.Headers(name = "虚拟号池上限", width = 115)
    private Integer totalCapacity;
    @HeadersUtil.Headers(name = "描述", width = 190)
    private String remark = "";
    @HeadersUtil.Headers(name = "空闲数量", width = 130)
    private Integer freeCount;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @HeadersUtil.Headers(name = "维护时间", width = 150)
    private Date modifytime;
}
