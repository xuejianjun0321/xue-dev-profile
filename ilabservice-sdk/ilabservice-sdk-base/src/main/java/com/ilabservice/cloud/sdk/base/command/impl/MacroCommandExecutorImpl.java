package com.ilabservice.cloud.sdk.base.command.impl;

import com.ilabservice.cloud.sdk.base.command.Command;
import com.ilabservice.cloud.sdk.base.command.MacroCommandExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:11:35
 * @Description:
 */
@Component
public class MacroCommandExecutorImpl<T> implements MacroCommandExecutor<T> {

    /**
     * 命令集合
     */
    private List<Command<T>> commands = new ArrayList<>();


    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void addCommand(Command<T> command) {
        commands.add(command);
    }

    @Override
    public void syncExecute() {
        for (Command<T> command : commands) {
            command.execute();
        }
    }

    @Override
    public void asyncExecute() {
        for (Command<T> command : commands) {
            threadPoolTaskExecutor.execute(command::execute);
        }
    }
}