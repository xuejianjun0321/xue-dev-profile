package com.ilabservice.cloud.sdk.base.command.impl;

import com.ilabservice.cloud.sdk.base.command.Command;
import com.ilabservice.cloud.sdk.base.command.CommandInvoker;
import org.springframework.stereotype.Component;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 11:35:07
 * @Description:
 */
@Component
public class CommandInvokerImpl implements CommandInvoker {

    @Override
    public <T> T action(final Command<T> command) {
        return (T) command.execute();
    }
}

