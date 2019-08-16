package com.allqj.virtual_number_administrate.business.vo;


import com.allqj.virtual_number_administrate.util.dateOperation.DateFormat;
import com.allqj.virtual_number_administrate.util.dateOperation.DateOperation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.Calendar;
import java.util.Date;


/**
 * 虚拟号管理列表请求
 */
@Getter
public class VirtualNumberPageRequest {
    @ApiModelProperty(value = "虚拟号绑定状态")
    private Boolean isAssign;

    public void setAssign(Boolean assign) {
        isAssign = assign;
    }

    @ApiModelProperty(value = "虚拟号分配状态")
    private Boolean isBinding;

    public void setBinding(Boolean binding) {
        isBinding = binding;
    }

    @ApiModelProperty(value = "部门id")
    private Integer[] deptIds;

    public void setDeptIds(Integer[] deptIds) {
        if (deptIds == null || deptIds.length < 1)
            return;
        this.deptIds = deptIds;
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
