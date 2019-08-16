package com.allqj.virtual_number_administrate.business.repository.mysql.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 虚拟号
 */
@Getter
@Setter
@Entity
@Table(catalog = "virtual_number_administrate", name = "virtual_number_info")
public class VirtualNumberMysqlEntity {
    @Id
    @GenericGenerator(name = "autoId", strategy = "native")
    @GeneratedValue(generator = "autoId")
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("虚拟号")
    private String virtualNumber;
    @ApiModelProperty("虚拟号类型")
    private Integer utype;
    @ApiModelProperty("子虚拟号")
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
    private String mainPhone;
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

    public VirtualNumberMysqlEntity() {
        init();
    }

    private void init() {
        this.createtime = new Date();
        this.modifytime = createtime;
        this.isdelete = false;
        this.isAssign = false;
        this.isHistory = false;
        this.isBinding = false;
    }
}

