package com.ilabservice.cloud.sdk.base.exception;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:27:22
 * @Description:
 */
public class AutoBaseBizRuntimeException extends BaseBizRuntimeException {

    private static final long serialVersionUID = -1403903475386472431L;

    public AutoBaseBizRuntimeException(String detailMessage) {
        super(detailMessage);
    }

    public AutoBaseBizRuntimeException(String code, String detailMessage) {
        super(code, detailMessage);
    }

    public AutoBaseBizRuntimeException(String code, String digestMessage, String detailMessage) {
        super(code, digestMessage, detailMessage);
    }
}
