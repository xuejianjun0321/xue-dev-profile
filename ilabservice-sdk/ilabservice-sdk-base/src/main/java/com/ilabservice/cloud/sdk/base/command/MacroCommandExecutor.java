package com.ilabservice.cloud.sdk.base.command;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:11:58
 * @Description:
 */
/**
 * @Description 宏命令执行器
 * @ClassName MacroCommandExecutor
 * @Author xuejianjun
 * @date 2021.05.18 14:17
 */
public interface MacroCommandExecutor<T> {


    /**
     * 增加命令
     * @param command 命令
     */
    void addCommand(Command<T> command);

    /**
     * 同步命令执行器
     */
    void syncExecute();

    /**
     * 异步命令执行器
     */
    void asyncExecute();

}
