package com.allqj.virtual_number_administrate.business.repository.mysql.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dept_info")
public class DeptInfoMysqlEntity {
    @Id
    @GenericGenerator(name = "autoId", strategy = "native")
    @GeneratedValue(generator = "autoId")
    private Integer id;

    private Integer deptId;
    private String deptType;
    private String deptName;

    @ApiModelProperty(value = "是否有空闲短号")
    private Boolean isFreeShortNumber;
    @ApiModelProperty(value = "是否有空闲长号")
    private Boolean isFreeLongNumber;

    private String operatorid;
    private Boolean isdelete;
    private Date createtime;
    private Date modifytime;

    public DeptInfoMysqlEntity() {
        init();
    }

    public DeptInfoMysqlEntity(Integer deptId, String deptType, String deptName) {
        this.deptId = deptId;
        this.deptType = deptType;
        this.deptName = deptName;
        init();
    }

    private void init() {
        this.createtime = new Date();
        this.modifytime = createtime;
        this.isFreeShortNumber = false;
        this.isFreeLongNumber = false;
        this.isdelete = false;
    }
}
