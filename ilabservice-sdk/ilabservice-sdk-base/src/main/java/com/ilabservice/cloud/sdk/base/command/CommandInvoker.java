package com.ilabservice.cloud.sdk.base.command;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 11:34:50
 * @Description:
 */
public interface CommandInvoker {

    /**
     * 行动方法
     * @param command 持有命令对象
     * @param <T> 具体命令
     * @return T
     */
    <T> T action(Command<T> command);

}
