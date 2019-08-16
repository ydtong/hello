package com.allqj.virtual_number_administrate.business.vo;


import com.allqj.virtual_number_administrate.business.repository.mysql.entity.DeptInfoMysqlEntity;
import com.allqj.virtual_number_administrate.business.vo.base.DeptBase;
import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 部门列表返回
 */
@Getter
@Setter
@AllArgsConstructor
public class DeptPageResult extends DeptBase {

    @HeadersUtil.Headers(name = "部门", width = 120)
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @HeadersUtil.Headers(name = "负责人", width = 130)
    @ApiModelProperty(value = "负责人")
    private String personInCharge;

    @HeadersUtil.Headers(name = "在职人数", width = 70)
    @ApiModelProperty(value = "在职人数")
    private Integer employeesNumber;

    @HeadersUtil.Headers(name = "短号数量", width = 70)
    @ApiModelProperty(value = "短号数量")
    private Integer shortVirtualNumber = 0;

    public Integer getShortVirtualNumber() {
        return bindingShortVirtualNumber + notBindingShortVirtualNumber;
    }

    @HeadersUtil.Headers(name = "占用短号", width = 70)
    @ApiModelProperty(value = "占用短号数量")
    private Integer bindingShortVirtualNumber = 0;

    @HeadersUtil.Headers(name = "空闲短号", width = 70)
    @ApiModelProperty(value = "空闲短号数量")
    private Integer notBindingShortVirtualNumber = 0;

    @HeadersUtil.Headers(name = "长号数量", width = 70)
    @ApiModelProperty(value = "长号数量")
    private Integer longVirtualNumber = 0;

    public Integer getLongVirtualNumber() {
        return bindingLongVirtualNumber + notBindingLongVirtualNumber;
    }

    @HeadersUtil.Headers(name = "占用长号", width = 70)
    @ApiModelProperty(value = "占用长号数量")
    private Integer bindingLongVirtualNumber = 0;

    @HeadersUtil.Headers(name = "空闲长号", width = 70)
    @ApiModelProperty(value = "空闲长号数量")
    private Integer notBindingLongVirtualNumber = 0;

    @HeadersUtil.Headers(name = "更新时间", width = 150)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date modifytime;

    public DeptPageResult() {

    }

    public DeptPageResult(DeptInfoMysqlEntity deptInfoMysqlEntity) {
        BeanUtils.copyProperties(deptInfoMysqlEntity, this);
    }
}
