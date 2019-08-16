package com.allqj.virtual_number_administrate.business.vo;


import com.allqj.virtual_number_administrate.business.enums.DeptVirtualNumberWhereEnum;
import com.allqj.virtual_number_administrate.util.dateOperation.DateFormat;
import com.allqj.virtual_number_administrate.util.dateOperation.DateOperation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.Calendar;
import java.util.Date;

/**
 * 部门列表请求
 */
@Getter
public class DeptPageRequest {

    private Integer deptVirtualNumberWhereCode;

    public void setDeptVirtualNumberWhereCode(Integer deptVirtualNumberWhereCode) {
        DeptVirtualNumberWhereEnum deptVirtualNumberWhereEnum =
                DeptVirtualNumberWhereEnum.getDeptVirtualNumberWhereEnum(deptVirtualNumberWhereCode);
        if (deptVirtualNumberWhereEnum == null)
            return;
        this.isFreeShortNumber = deptVirtualNumberWhereEnum.getDeptVirtualNumberWhereDictionary().getIsFreeShortNumber();
        this.isFreeLongNumber = deptVirtualNumberWhereEnum.getDeptVirtualNumberWhereDictionary().getIsFreeLongNumber();
    }

    @JsonIgnore
    @ApiModelProperty(value = "是否有空闲短号")
    private Boolean isFreeShortNumber;
    @JsonIgnore
    @ApiModelProperty(value = "是否有空闲长号")
    private Boolean isFreeLongNumber;
    @ApiModelProperty(value = "部门Id")
    private Integer[] deptIds;

    public void setDeptIds(Integer[] deptIds) {
        if (deptIds == null || deptIds.length < 1)
            return;
        this.deptIds = deptIds;
    }

    @ApiModelProperty(value = "部门类型")
    private String deptType;

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    @ApiModelProperty(value = "模糊查询字段")
    private String vagueParameter;

    public void setVagueParameter(String vagueParameter) {
        this.vagueParameter = vagueParameter;
    }

    private String[] searchTime = new String[2];

    @SuppressWarnings("all")
    public void setSearchTime(String[] searchTime) {
        if (searchTime == null || searchTime.length < 2)
            return;
        this.startTime = DateOperation.stringDate(searchTime[0], DateFormat.yyyMMdd);
        Date date = DateOperation.stringDate(searchTime[1], DateFormat.yyyMMdd);
        if (date == null)
            return;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        this.endTime = calendar.getTime();
    }

    @JsonIgnore
    private Date startTime;
    @JsonIgnore
    private Date endTime;
}
