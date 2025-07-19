package com.ilabservice.cloud.sdk.base.exception;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:33:03
 * @Description:
 */

import com.ilabservice.cloud.sdk.base.enums.BaseResultCodeEnum;
import com.ilabservice.cloud.sdk.base.enums.IResultEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Description RestServiceException
 * @ClassName RestServiceException
 * @Author xuejianjun
 * @date 2021.05.14 15:37
 */
@Getter
@Setter
public class RestServiceException extends RuntimeException {

    private static final long serialVersionUID = -8373310686430610622L;
    protected int code;
    protected String message;

    public RestServiceException(String message) {
        super(message);
        this.code = BaseResultCodeEnum.SYSTEM_ERROR.getNCode();
        this.message = BaseResultCodeEnum.SYSTEM_ERROR.getMessage();
        this.message = message;
    }

    public RestServiceException(BaseResultCodeEnum errorCode, String message) {
        super(message);
        this.message = BaseResultCodeEnum.SYSTEM_ERROR.getMessage();
        this.code = errorCode.getNCode();
        this.message = message;
    }

    public RestServiceException(int errorCode, String message) {
        super(message);
        this.message = BaseResultCodeEnum.SYSTEM_ERROR.getMessage();
        this.code = errorCode;
        this.message = message;
    }

    public RestServiceException(IResultEnum iResultEnum) {
        this(iResultEnum.getCode(), iResultEnum.getMessage());
    }

    public RestServiceException(IResultEnum iResultEnum, String message) {
        this(iResultEnum.getCode(), message);
    }

    public RestServiceException(Throwable cause) {
        super(cause);
        this.code = BaseResultCodeEnum.SYSTEM_ERROR.getNCode();
        this.message = BaseResultCodeEnum.SYSTEM_ERROR.getMessage();
    }

    public RestServiceException(String message, Throwable cause) {
        super(message, cause);
        this.code = BaseResultCodeEnum.SYSTEM_ERROR.getNCode();
        this.message = BaseResultCodeEnum.SYSTEM_ERROR.getMessage();
        this.message = message;
    }

    public RestServiceException(int errorCode, String message, Throwable cause) {
        this(message, cause);
        this.code = errorCode;
        this.message = message;
    }

    public RestServiceException(IResultEnum iResultEnum, Throwable cause) {
        this(iResultEnum.getCode(), iResultEnum.getMessage(), cause);
    }

    public RestServiceException(IResultEnum iResultEnum, String message, Throwable cause) {
        this(iResultEnum.getCode(), message, cause);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public RestServiceException formatter(Object... args) {
        if (!StringUtils.isEmpty(this.message)) {
            for(int i = 0; i < args.length; ++i) {
                this.message = String.format(this.message, args);
            }
        }

        return this;
    }

    public static long getSerialversionuid() {
        return -8373310686430610622L;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
