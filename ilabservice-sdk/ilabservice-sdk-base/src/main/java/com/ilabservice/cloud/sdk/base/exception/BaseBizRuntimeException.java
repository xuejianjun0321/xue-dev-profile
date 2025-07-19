package com.ilabservice.cloud.sdk.base.exception;

import com.ilabservice.cloud.sdk.base.enums.BaseResultCodeEnum;

public class BaseBizRuntimeException extends BaseRuntimeException {

    /** serialVersionUID */
    private static final long serialVersionUID = -3121133043898262409L;

    /**
     * @param detailMessage
     */
    public BaseBizRuntimeException(String detailMessage) {
        super(BaseResultCodeEnum.SYSTEM_ERROR, detailMessage);
    }

    /**
     *
     * @param code
     * @param detailMessage
     */
    public BaseBizRuntimeException(String code, String detailMessage) {
        super(code, detailMessage);
    }

    /**
     *
     * @param code
     * @param digestMessage
     * @param detailMessage
     */
    public BaseBizRuntimeException(String code, String digestMessage, String detailMessage){
        super(code, digestMessage, detailMessage);
    }
}