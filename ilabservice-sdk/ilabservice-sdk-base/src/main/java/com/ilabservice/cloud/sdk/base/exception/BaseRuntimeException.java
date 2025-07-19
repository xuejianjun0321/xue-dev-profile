package com.ilabservice.cloud.sdk.base.exception;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ilabservice.cloud.sdk.base.enums.BaseResultCodeEnum;
import com.ilabservice.cloud.sdk.base.enums.EnumBase;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description 运行时异常基类
 * @ClassName BaseRuntimeException
 * @Author xuejianjun
 * @date 2021.05.14 15:14
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 3265492502177237267L;

    /** 异常码 */
    protected String code = BaseResultCodeEnum.SYSTEM_ERROR.getCode();

    /** 异常摘要信息 */
    protected String digestMessage = BaseResultCodeEnum.SYSTEM_ERROR.message();

    /** 异常具体信息 */
    protected String message = BaseResultCodeEnum.SYSTEM_ERROR.message();

    /**
     * <br>
     * 原始异常信息：<br>
     * 异常可以通过 rpc 调用返回，此时得到的直接异常栈是关于 rpc 的被调用方的，因为 rpc 调用的异常是反序列化直接得到的<br>
     * 这样就会导致本地栈丢失，因为没有通过 new Exception(rpcExcetion) 抛出异常，也不能这样做，因为会导致异常类信息的丢失<br>
     * 如果增加本地栈到 rpc 异常中，会导致随着调用链的增加，异常栈成倍放大（除非实现了异常压缩）<br>
     * 如果替换 rpcException 中的异常栈为本地栈，那么虽然当前服务定位方便了，需要找到最终源头更复杂了<br>
     */
    private StackTraceElement[] originalStackTraceElement;

    /**
     * 构造器。
     *
     * @param detailMessage 异常信息
     */
    public BaseRuntimeException(String detailMessage) {
        this.message = detailMessage;
    }

    /**
     * 构造器。
     *
     * @param errorCode 异常码
     * @param detailMessage 异常信息
     */
    public BaseRuntimeException(String errorCode, String detailMessage) {
        this.code = errorCode;
        this.message = detailMessage;
    }

    /**
     * 构造器。
     *
     * @param errorCode 异常码
     * @param digestMessage 异常信息
     */
    public BaseRuntimeException(String errorCode, String digestMessage, String detailMessage) {
        this.code = errorCode;
        this.digestMessage = digestMessage;
        this.message = detailMessage;
    }

    /**
     * 构造器。
     *
     * @param baseEnum 异常结果枚举
     */
    public BaseRuntimeException(EnumBase baseEnum) {
        this(baseEnum.name(), baseEnum.message());
    }

    /**
     * 构造器。
     *
     * @param baseEnum 异常结果枚举
     * @param detailMessage 异常信息
     */
    public BaseRuntimeException(EnumBase baseEnum, String detailMessage) {
        this(baseEnum.name(), detailMessage);
    }

    /**
     * 构造器。
     *
     * @param baseEnum 异常结果枚举
     * @param digestMessage 异常信息
     * @param detailMessage 异常具体信息
     */
    public BaseRuntimeException(EnumBase baseEnum, String digestMessage, String detailMessage) {
        this.code = baseEnum.name();
        this.digestMessage = digestMessage;
        this.message = detailMessage;
    }

    /**
     * 构造器。
     *
     * @param cause 原因
     */
    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造器。
     *
     * @param detailMessage 异常信息
     * @param cause 原因
     */
    public BaseRuntimeException(String detailMessage, Throwable cause) {
        super(cause);
        this.message = detailMessage;
    }

    /**
     * 构造器。
     *
     * @param errorCode 异常码
     * @param detailMessage 异常信息
     * @param cause 原因
     */
    public BaseRuntimeException(String errorCode, String detailMessage, Throwable cause) {
        this(errorCode, null, detailMessage, cause);
    }

    /**
     * 构造器
     *
     * @param errorCode 异常码
     * @param digestMessage 异常信息
     * @param detailMessage 异常具体信息
     * @param cause 原因
     */
    public BaseRuntimeException(
            String errorCode, String digestMessage, String detailMessage, Throwable cause) {
        super(cause);
        this.digestMessage = digestMessage;
        this.message = detailMessage;
        this.code = errorCode;
    }

    /**
     * 构造函数
     *
     * @param baseEnum 异常结果集枚举
     * @param cause 异常
     */
    public BaseRuntimeException(EnumBase baseEnum, Throwable cause) {
        this(baseEnum.name(), baseEnum.message(), cause);
    }

    /**
     * 构造函数
     *
     * @param baseEnum
     * @param detailMessage
     * @param cause
     */
    public BaseRuntimeException(EnumBase baseEnum, String detailMessage, Throwable cause) {
        this(baseEnum.name(), detailMessage, cause);
    }

    /**
     * 构造函数
     *
     * @param baseEnum
     * @param digestMessage
     * @param detailMessage
     * @param cause
     */
    public BaseRuntimeException(
            EnumBase baseEnum, String digestMessage, String detailMessage, Throwable cause) {
        this(baseEnum.name(), digestMessage, detailMessage, cause);
    }

    /** @see java.lang.Throwable#toString() */
    @Override
    public String toString() {
        StringBuilder exceptionInfo = new StringBuilder(getClass().getName());
        exceptionInfo
                .append("[")
                .append("code=")
                .append(getCode())
                .append(",digestMessage=")
                .append(getDigestMessage())
                .append(",message=")
                .append(getMessage())
                .append("]");
        return exceptionInfo.toString();
    }

    /**
     * 异常信息参数格式化 <br>
     * <a style='color:red;font-weight:800;'>此方法不建议使用，若发现此方法请当即删除。替代功能参照{@link
     * java.text.DateFormat}</a>
     *
     * @param args message中有需要传入的变量"s%,d%"之类的 eg：message:"this is a example! %s",args:"ok" 消息
     */
    @Deprecated
    public BaseRuntimeException formatter(Object... args) {
        if (!StringUtils.isEmpty(message)) {
            for (int i = 0; i < args.length; i++) {
                message = String.format(this.message, args);
            }
        }
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getDigestMessage() {
        return digestMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BaseRuntimeException code(String code) {
        this.code = code;
        return this;
    }

    public BaseRuntimeException digestMessage(String digestMessage) {
        this.digestMessage = digestMessage;
        return this;
    }

    public BaseRuntimeException message(String message) {
        this.message = message;
        return this;
    }

}