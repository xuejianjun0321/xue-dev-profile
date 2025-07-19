package com.ilabservice.cloud.sdk.base.command;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:11:00
 * @Description:
 */
public interface Command<T> {

    /**
     * 执行器
     * @return T
     */
    T execute();
}