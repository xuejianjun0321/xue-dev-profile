package com.ilabservice.cloud.sdk.base.enums;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:32:32
 * @Description:
 */
public interface IResultEnum {

    /**
     * 返回结果码
     * @return
     */
    int getCode();

    /**
     * 返回信息
     * @return
     */
    String getMessage();
}
