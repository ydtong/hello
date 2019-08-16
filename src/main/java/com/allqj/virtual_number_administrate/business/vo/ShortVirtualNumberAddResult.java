package com.allqj.virtual_number_administrate.business.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class ShortVirtualNumberAddResult extends VirtualNumberAddResult {
    @ApiModelProperty("总机号")
    private String mainPhone;
    @ApiModelProperty(value = "状态")
    private String isAssign;
    @ApiModelProperty(value = "绑定状态")
    private String isBinding;
    @ApiModelProperty(value = "联通长号")
    private String sonVirtualNumber;
    @ApiModelProperty(value = "身份证")
    private String idCard;
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifytime;
    @ApiModelProperty(value = "是否有历史")
    private Boolean isHistory;
}
