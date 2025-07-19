package com.ilabservice.cloud.sdk.base.enums;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月22日 09:48:44
 * @Description:
 */
import com.ilabservice.cloud.common.base.error.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月17日 15:47:11
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum GeneralError implements Error {
    PARAM_ERROR(200, "PARAM_ERROR", "参数异常"),
    ;

    private final Integer status;
    private final String code;
    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getStatus() {
        return status;
    }
}

