package com.allqj.virtual_number_administrate.business.repository.mysql.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(catalog = "virtual_number_administrate", name = "virtual_number_binding_info")
public class VirtualNumberBindingInfoMysqlEntity {
    @Id
    @GenericGenerator(name = "autoId", strategy = "native")
    @GeneratedValue(generator = "autoId")
    private Integer id;
    private Integer userId;
    private String userName;

    private Integer deptId;
    private String deptType;
    private String deptName;

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

    public VirtualNumberBindingInfoMysqlEntity() {
        init();
    }

    private void init() {
        //this.createtime = new Date();
        //this.modifytime = createtime;
        this.isdelete = false;
    }

    public VirtualNumberBindingInfoMysqlEntity(Integer userId, String userName, String phone, String virtualNumber, Integer virtualNumberType) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.virtualNumber = virtualNumber;
        this.virtualNumberType = virtualNumberType;
    }
}
