package com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 虚拟号
 */
@Getter
@Setter
@Document(indexName = "virtual_number_administrate", type = "virtual_number_info", shards = 5, replicas = 1, refreshInterval = "-1")
public class VirtualNumberEsEntity {
    @Id
    private Integer id;
    @ApiModelProperty("虚拟号")
    private String virtualNumber;
    @ApiModelProperty("虚拟号类型")
    private Integer utype;
    @ApiModelProperty(value = "子虚拟号")
    private String sonVirtualNumber;
    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("是否分配")
    private Boolean isAssign;
    @ApiModelProperty("部门id")
    private Integer deptId;
    @ApiModelProperty("部门类型")
    private String deptType;
    @ApiModelProperty("总机Code")
    private Integer accountCode;
    @ApiModelProperty("操作人id")
    private String operatorid;
    @ApiModelProperty("是否删除")
    private Boolean isdelete;
    @ApiModelProperty("是否绑定")
    private Boolean isBinding;
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    @ApiModelProperty("修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifytime;
    @ApiModelProperty(value = "是否有历史")
    private Boolean isHistory;

    public VirtualNumberEsEntity() {
        this.createtime = new Date();
        this.modifytime = createtime;
        this.isdelete = false;
        this.isAssign = false;
        this.isHistory = false;
        this.isBinding = false;
    }

    @Override
    public String toString() {
        return "VirtualNumberEsEntity{" +
                "id=" + id +
                ", virtualNumber='" + virtualNumber + '\'' +
                ", utype=" + utype +
                ", sonVirtualNumber='" + sonVirtualNumber + '\'' +
                ", isAssign=" + isAssign +
                ", deptId=" + deptId +
                ", deptType='" + deptType + '\'' +
                ", accountCode=" + accountCode +
                ", operatorid='" + operatorid + '\'' +
                ", isdelete=" + isdelete +
                ", isBinding=" + isBinding +
                ", createtime=" + createtime +
                ", modifytime=" + modifytime +
                ", isHistory=" + isHistory +
                '}';
    }
}

