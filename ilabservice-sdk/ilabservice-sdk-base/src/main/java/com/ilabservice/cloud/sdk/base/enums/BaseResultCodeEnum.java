package com.ilabservice.cloud.sdk.base.enums;

/**
 * @Description 结果码枚举
 * @ClassName BaseResultCodeEnum
 * @Author xuejianjun
 * @date 2021.05.14 15:16
 */
public enum BaseResultCodeEnum implements EnumBase {

    /** 执行成功 */
    SUCCESS("SUCCESS", "执行成功", 10000000),

    /** 系统异常*/
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常", 10000001),

    /** 外部接口调用异常*/
    INTERFACE_SYSTEM_ERROR("INTERFACE_SYSTEM_ERROR", "外部接口调用异常，请联系管理员！", 10000003),

    /** 业务连接处理超时 */
    CONNECT_TIME_OUT("CONNECT_TIME_OUT", "系统超时，请稍后再试!", 10000004),

    /** 系统错误 */
    SYSTEM_FAILURE("SYSTEM_FAILURE", "系统错误", 10000005),

    /** 参数为空 */
    NULL_ARGUMENT("NULL_ARGUMENT", "参数为空", 10000006),

    /** 非法参数 */
    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT", "非法参数", 10000007),

    /** 非法配置 */
    ILLEGAG_CONFIGURATION("ILLEGAG_CONFIGURATION", "非法配置" , 10000008),

    /** 非法状态 */
    ILLEGAL_STATE("ILLEGAG_STATE", "非法状态", 10000009),

    /** 错误的枚举编码 */
    ENUM_CODE_ERROR("ENUM_CODE_ERROR", "错误的枚举编码", 10000010),

    /** 逻辑错误 */
    LOGIC_ERROR("LOGIC_ERROR", "逻辑错误", 10000011),

    /**并发异常*/
    CONCURRENT_ERROR("CONCURRENT_ERROR", "并发异常", 10000012),

    /** 非法操作 */
    ILLEGAL_OPERATION("ILLEGAL_OPERATION", "非法操作", 10000013),

    /** 重复操作 */
    REPETITIVE_OPERATION("REPETITIVE_OPERATION", "重复操作", 10000014),

    /** 无操作权限 */
    NO_OPERATE_PERMISSION("NO_OPERATE_PERMISSION", "无操作权限", 10000015),

    /** 数据异常 */
    DATA_ERROR("DATA_ERROR", "数据异常", 10000016),

    /** 链接异常 */
    CONN_ERROR("CONN_ERROR", "连接异常", 10000017),

    /** 类型不匹配 */
    TYPE_UNMATCH("TYPE_UNMATCH", "类型不匹配", 10000018),

    /** 对象类型不匹配 */
    CLASS_UNMATCH("CLASS_UNMATCH", "对象类型不匹配", 10000019),

    /** FILE_NOT_EXIST */
    FILE_NOT_EXIST("FILE_NOT_EXIST", "文件不存在", 10000020),

    INVOKE_IS_REJECT("INVOKE_IS_REJECT", "被限制的调用[{0}]", 10000021);

    /** 枚举编号 */
    private String  code;

    /** 枚举详情 */
    private String  message;

    private Integer NCode;

    /**
     * 构造方法
     *
     * @param code         枚举编号
     * @param message      枚举详情
     */
    private BaseResultCodeEnum(String code, String message, int NCode) {
        this.code = code;
        this.message = message;
        this.NCode = NCode;
    }

    /**
     * 通过枚举<code>code</code>获得枚举。
     *
     * @param code         枚举编号
     * @return
     */
    public static BaseResultCodeEnum getResultCodeEnumByCode(String code) {
        for (BaseResultCodeEnum param : values()) {
            if (param.getCode().equals(code)) {
                return param;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }


    @Override
    public String message() {
        return message;
    }

    /**
     * Getter method for property <tt>nCode</tt>.
     *
     * @return property value of NCode
     */
    public Integer getNCode() {
        return NCode;
    }
}