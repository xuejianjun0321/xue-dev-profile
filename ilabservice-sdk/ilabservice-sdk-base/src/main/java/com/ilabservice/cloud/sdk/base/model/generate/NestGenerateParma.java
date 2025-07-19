package com.ilabservice.cloud.sdk.base.model.generate;

import lombok.Data;

import java.util.List;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月24日 16:57:10
 * @Description: 嵌套模型关系生产Java类文件入参
 */
@Data
public class NestGenerateParma extends SingleGenerateParma{

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
     * 《非必填》，如果不填则会按单表生成代码
     *  子表列表
     */
    private List<Attribute> attributeList;


    /**
     * 《非必填》
     *  子模块列表
     */
    private List<SubModule> subModuleList;


}
