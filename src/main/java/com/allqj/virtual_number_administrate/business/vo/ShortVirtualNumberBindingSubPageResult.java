package com.allqj.virtual_number_administrate.business.vo;


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
 * 短虚拟号绑定字列表返回
 */
@Getter
@Setter
public class ShortVirtualNumberBindingSubPageResult {
    @HeadersUtil.Headers(name = "虚拟号", width = 100)
    @ApiModelProperty(value = "虚拟号")
    private String virtualNumber;
    @HeadersUtil.Headers(name = "联通长号", width = 100)
    @ApiModelProperty(value = "联通长号")
    private String sonVirtualNumber;
    @HeadersUtil.Headers(name = "姓名", width = 80)
    @ApiModelProperty(value = "姓名")
    private String userName;
    @HeadersUtil.Headers(name = "更新时间", width = 145)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date modifytime;

    public ShortVirtualNumberBindingSubPageResult() {
    }

    public ShortVirtualNumberBindingSubPageResult(VirtualNumberMysqlEntity virtualNumberMysqlEntity) {
        BeanUtils.copyProperties(virtualNumberMysqlEntity, this);
    }
}
