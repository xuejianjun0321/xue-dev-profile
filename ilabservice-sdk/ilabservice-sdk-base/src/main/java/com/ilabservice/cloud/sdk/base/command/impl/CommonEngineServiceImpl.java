package com.ilabservice.cloud.sdk.base.command.impl;


import javax.annotation.Resource;

import com.ilabservice.cloud.sdk.base.command.CommandExecutor;
import com.ilabservice.cloud.sdk.base.command.MacroCommandExecutor;
import org.springframework.stereotype.Component;

/**
 * @Description 公共引擎服务
 * @ClassName CommonEngineServiceImpl
 * @Author xuejianjun
 * @date 2021.05.18 14:12
 */
@Component
public class CommonEngineServiceImpl<T> {

    @Resource
    protected CommandExecutor commandExecutor;

    @Resource
    protected MacroCommandExecutor<T> macroCommandExecutor;

    public CommonEngineServiceImpl() {

    }


}