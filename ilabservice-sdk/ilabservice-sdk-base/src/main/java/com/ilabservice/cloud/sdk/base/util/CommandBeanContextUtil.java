package com.ilabservice.cloud.sdk.base.util;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:33:46
 * @Description:
 */
import javax.annotation.Resource;

import com.ilabservice.cloud.sdk.base.auto.manager.GeneratorManager;
import org.springframework.stereotype.Component;

/**
 * @Description 命令bean上下文工具类
 * @ClassName CommandBeanContextUtil
 * @Author xuejianjun
 * @date 2021.05.18 13:51
 */
@Component
public class CommandBeanContextUtil {


    private static GeneratorManager generatorManager;


    public static GeneratorManager getGeneratorManager() {
        return generatorManager;
    }

    @Resource()
    public void setGeneratorManager(GeneratorManager generatorManager) {
        CommandBeanContextUtil.generatorManager = generatorManager;
    }


}