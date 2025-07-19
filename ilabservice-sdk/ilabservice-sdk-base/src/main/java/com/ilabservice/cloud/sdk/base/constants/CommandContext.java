package com.ilabservice.cloud.sdk.base.constants;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:14:03
 * @Description:
 */

import com.ilabservice.cloud.sdk.base.model.TsysTables;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 命令上下文
 * @ClassName CommandContext
 * @Author xuejianjun
 * @date 2021.05.18 13:45
 */
@Data
public class CommandContext {

    /**
     * 模板参数Map
     */
    private Map<String,Object> templateParamMap = new HashMap<>();

    /**
     * 模板列表
     */
    private List<String> templates = new ArrayList<>();

    /**
     * 模板vm文件列表
     */
    private List<File> files = new ArrayList<>();

    /**
     * 自定义业务表信息
     */
    private TsysTables tables;

    /**
     * 表名
     */
    private String tableName;

    private String templatePath;

    private String packageName;

    private String packageMid;

    /** 本地代码项目路径 */
    private String projectPath = System.getProperty("user.dir");

}
