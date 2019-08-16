package com.allqj.virtual_number_administrate.business.vo;

import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VirtualNumberHistoryResult {
    @HeadersUtil.Headers(name = "工作电话", width = 100)
    private String phone;
    @HeadersUtil.Headers(name = "姓名", width = 80)
    private String userName;
    @HeadersUtil.Headers(name = "部门", width = 210)
    private String deptName;
    @HeadersUtil.Headers(name = "绑定时间", width = 145)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime;
    @HeadersUtil.Headers(name = "解绑时间", width = 145)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifytime;
}
