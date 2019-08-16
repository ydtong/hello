package com.allqj.virtual_number_administrate.business.vo;

import com.allqj.virtual_number_administrate.business.enums.VirtualNumberBindingStateEnum;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 虚拟号管理列表返回
 */
@Getter
@Setter
public class ShortVirtualNumberPageResult {
    @HeadersUtil.Headers(name = "虚拟短号", width = 110)
    @ApiModelProperty(value = "短隐号")
    private String virtualNumber;
    @HeadersUtil.Headers(name = "虚拟长号", width = 110)
    @ApiModelProperty(value = "联通长号")
    private String sonVirtualNumber;
    @HeadersUtil.Headers(name = "身份证号", width = 120)
    @ApiModelProperty(value = "身份证号")
    private String idCard;
    @HeadersUtil.Headers(name = "总机号码", width = 110)
    @ApiModelProperty(value = "总机号")
    private String mainPhone;
    @HeadersUtil.Headers(name = "状态", width = 70)
    @ApiModelProperty(value = "状态")
    private String bindingState;
    @ApiModelProperty(value = "状态")
    private Boolean isBinding;
    @HeadersUtil.Headers(name = "部门", width = 120)
    @ApiModelProperty(value = "部门")
    private String deptName;
    @HeadersUtil.Headers(name = "创建时间", width = 100)
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime;
    @HeadersUtil.Headers(name = "更新时间", width = 100)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date modifytime;
    @ApiModelProperty(value = "是否有历史")
    private Boolean isHistory = false;

    public ShortVirtualNumberPageResult() {
    }

    public ShortVirtualNumberPageResult(VirtualNumberMysqlEntity virtualNumberMysqlEntity) {
        BeanUtils.copyProperties(virtualNumberMysqlEntity, this);
        this.bindingState = VirtualNumberBindingStateEnum.getDescribe(virtualNumberMysqlEntity.getIsBinding());
    }
}
