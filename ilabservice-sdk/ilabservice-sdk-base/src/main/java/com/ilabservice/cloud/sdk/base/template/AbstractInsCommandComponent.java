package com.ilabservice.cloud.sdk.base.template;

import com.ilabservice.cloud.sdk.base.command.Command;
import com.ilabservice.cloud.sdk.base.constants.CommandContext;
import com.ilabservice.cloud.sdk.base.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description 命令处理模板基类
 * @ClassName AbstractInsCommandComponent
 * @Author xuejianjun
 * @date 2021.05.18 13:43
 */
@Slf4j
@Component
public abstract class AbstractInsCommandComponent<T> implements Command<Object> {

    /**
     * 命令上下文
     */
    protected CommandContext commandContext = new CommandContext();

    /**
     * 命令处理前
     *
     * @param insContext 上下文
     */
    public void before(CommandContext insContext) {
        log.info("决策处理前  insContext={}.",
                JacksonUtil.toJsonString(insContext));
    }

    @Override
    public Object execute() {
        //前置处理
        before(commandContext);

        //处理集合
        T obj = handle(commandContext);

        //后置处理
        after(commandContext);

        return obj;
    }

    /**
     * 处理集合
     * @param insContext insContext
     * @return
     */
    public abstract T handle(CommandContext insContext);

    /**
     * 命令处理后
     *
     * @param insContext 上下文
     */
    public void after(CommandContext insContext) {

        log.info("命令处理后, insContext={}.",
                JacksonUtil.toJsonString(insContext));
    }

}
