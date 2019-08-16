package com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@Document(indexName = "virtual_number_administrate", type = "dept_info", shards = 5, replicas = 1, refreshInterval = "-1")
public class DeptInfoEsEntity {
    @Id
    private Integer id;

    private Integer deptId;
    private String deptType;

    @ApiModelProperty(value = "是否有空闲短号")
    private Boolean isFreeShortNumber;
    @ApiModelProperty(value = "是否有空闲长号")
    private Boolean isFreeLongNumber;

    private String operatorid;
    private Boolean isdelete;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifytime;

    @Override
    public String toString() {
        return "DeptInfoEsEntity{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", deptType='" + deptType + '\'' +
                ", isFreeShortNumber=" + isFreeShortNumber +
                ", isFreeLongNumber=" + isFreeLongNumber +
                ", operatorid='" + operatorid + '\'' +
                ", isdelete=" + isdelete +
                ", createtime=" + createtime +
                ", modifytime=" + modifytime +
                '}';
    }
}
