package com.ilabservice.cloud.sdk.base.command;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:10:25
 * @Description:
 */
public interface CommandExecutor {

    /**
     * 执行方法
     * @param command 命令接口
     * @param <T> 具体命令
     * @return T
     */
    <T> T execute(Command<T> command);

}

