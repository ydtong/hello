package com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
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
@Document(indexName = "virtual_number_administrate", type = "virtual_number_binding_info", shards = 5, replicas = 1, refreshInterval = "-1")
public class VirtualNumberBindingInfoEsEntity {
    @Id
    private Integer id;

    private Integer deptId;
    private String deptType;
    private String deptName;

    private Integer userId;
    private String userName;

    private String phone;
    private String virtualNumber;
    private Integer virtualNumberType;

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
        return "VirtualNumberBindingInfoEsEntity{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", deptType='" + deptType + '\'' +
                ", deptName='" + deptName + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", virtualNumber='" + virtualNumber + '\'' +
                ", virtualNumberType=" + virtualNumberType +
                ", operatorid='" + operatorid + '\'' +
                ", isdelete=" + isdelete +
                ", createtime=" + createtime +
                ", modifytime=" + modifytime +
                '}';
    }
}
