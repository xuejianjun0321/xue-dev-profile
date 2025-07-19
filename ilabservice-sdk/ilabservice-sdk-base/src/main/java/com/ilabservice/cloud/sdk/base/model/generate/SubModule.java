package com.ilabservice.cloud.sdk.base.model.generate;

import lombok.Data;

import java.util.List;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月24日 17:02:38
 * @Description: 子模块
 */
@Data
public class SubModule {

    /**
     * 《必填参数》
     * 表名
     */
    String tableName;

    /**
     * 《必填参数》必须整个项目全局唯一，否则会覆盖对应代码
     *  模块key
     **/
    private String moduleKey;

    /**
     * 《必填参数》
     * 模块名称
     **/
    private String moduleName;

    /**
     * 《非必填》
     *  子表列表
     */
    private List<Attribute> attributeList;

    /**
     * 《非必填》
     *  子模块列表
     */
    private List<SubModule> subModuleList;

}
