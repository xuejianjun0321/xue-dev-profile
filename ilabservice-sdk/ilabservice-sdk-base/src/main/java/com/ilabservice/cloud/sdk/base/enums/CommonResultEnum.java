package com.ilabservice.cloud.sdk.base.enums;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:30:48
 * @Description:
 */


import java.util.Arrays;

/**
 * @Description 返回码
 * @ClassName CommonResultEnum
 * @Author xuejianjun
 * @date 2021.05.14 15:01
 */
public enum CommonResultEnum implements IResultEnum {
    /**
     * 基础返回码
     */
    SUCCESS(200, "success"),
    ERROR(500, "error"),
    SYSTEM_EXCEPTION(1000, "系统异常"),
    PARAM_ERROR(1001, "参数有误"),
    PARAM_INCOMPLETE(1002, "参数不完整"),
    FILE_UPLOAD_FAIL(1003, "文件上传失败"),
    FILE_DOWNLOAD_FAIL(1113, "文件下载失败"),
    FILE_TYPE_ERROR(1005, "文件类型有误"),
    SERVICE_ERROR(1006, "内部服务错误"),
    START_FLOW_FAIL(1007, "流程发起失败"),
    OPERATION_FAIL(1008, "操作失败"),
    CONTEXT_EXCEPTION(1009, "上下文异常"),
    DATABASE_EXCEPTION(1011, "数据库异常"),
    GET_USER_INFO_ERROR(1012, "获取session 用户信息异常"),
    TYPE_CONVERSION_ERROR(1013, "类型转换错误"),

    /**
     * 数据库
     */
    NOT_TABLE(2001, "数据库表不存在"),
    NOT_TABLE_NAME(2002, "表名不在上下文中"),


    ;


    private int code;

    private String msg;

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    CommonResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    /**
     * 根据Key得到枚举的Value
     *
     * @param msg m
     * @return enum
     */
    public static CommonResultEnum getEnumType(String msg) {
        CommonResultEnum[] alarmGrades = CommonResultEnum.values();
        CommonResultEnum result = Arrays.asList(alarmGrades).stream()
                .filter(alarmGrade -> alarmGrade.getMessage().equals(msg))
                .findFirst().orElse(CommonResultEnum.SYSTEM_EXCEPTION);
        return result;
    }
}
