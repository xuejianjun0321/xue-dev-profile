package com.ilabservice.cloud.sdk.base.command.impl;

import com.ilabservice.cloud.sdk.base.command.Command;
import com.ilabservice.cloud.sdk.base.command.CommandExecutor;
import com.ilabservice.cloud.sdk.base.command.CommandInvoker;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 11:34:27
 * @Description:
 */
@Component
public class CommandExecutorImpl implements CommandExecutor {

    /**
     * 命令请求者角色接口
     */
    @Resource
    protected CommandInvoker invoker;

    @Override
    public <T> T execute(Command<T> command) {
        return invoker.action(command);
    }
}
