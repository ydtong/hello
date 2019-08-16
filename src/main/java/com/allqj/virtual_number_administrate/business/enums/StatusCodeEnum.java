package com.allqj.virtual_number_administrate.business.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    OK(1000, "成功"),
    //START(431000,"开始的状态码")
    NUMBER_INSUFFICIENT(431300, "空闲号码数量不足"),
    VIRTUAL_NUMBER_EXIST(431301, "虚拟号已存在"),
    MAIN_PHONE_TOO_SHORT(431302, "限定总机号长度11位"),
    LONG_VIRTUAL_NUMBER_TOO_SHORT(431302, "限定长度11位"),
    SHORT_VIRTUAL_NUMBER_TOO_SHORT(431302, "限定虚拟号长度11位"),
    CARD_NUMBER_ERROR(431302,"身份证号错误"),
    OUT_TOTAL_MAIN_PHONE(431302, "超出上限,请到总机号管理查看"),
    REQUEST_ERROR(431400, "请求错误"),
    NOT_FOUND_DATA(431401, "没有找到指定数据"),
    VIRTUAL_NUMBER_ERROR(431402, "虚拟号存在问题"),
    EXCEL_ERROR(431403, "excel格式错误"),
    EXCEL_LACK(431413, "excel数据不全"),
    EXIST_OCCUPY(431404, "存在占用"),
    EXIST_BINDING(431404, "存在绑定"),
    NOT_DEPT_DATA(431405, "没有找到部门信息"),
    NOT_MAIN_PHONE(431406, "总机号不存在"),
    NOT_USER(431406, "用户不存在"),
    NOT_DEPT(431406, "部门不存在"),
    BINDING_FAULT(431407, "绑定失败"),
    BINDING_CANCEL_FAULT(431408, "解绑失败"),
    BINDING_USER_NOT(431409, "用户未绑定"),
    EXIST_VIRTUAL_NUMBER(431410, "存在虚拟号"),
    GO_LOG_IN(431411, "请登录后再试"),
    TOKEN_ERROR(431412, "权限失效,请重新登陆"),
    CALL_FAULT(431410, "拨打失败"),
    NO_FUNCTION(431999, "功能未实现"),
    MODIFY_FAIL(431999,"修改失败"),
    //SEND(432000,"结束的状态码")
    PARAMETERVALIDERROR(431401, "参数校验失败"),
    TOTAL_CAPACITY_ERROR(431600, "总机号上限小于号码池虚拟号数量");

    private Integer code;
    private String message;

}
