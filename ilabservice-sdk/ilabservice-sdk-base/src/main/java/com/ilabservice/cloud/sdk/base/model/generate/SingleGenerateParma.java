package com.ilabservice.cloud.sdk.base.model.generate;

import lombok.Data;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月22日 10:53:49
 * @Description: 单表生成Java类文件参数
 */
@Data
public class SingleGenerateParma {

    /**
     * 《必填参数》
     * 表名
     */
    String tableName;

    /**
     * 《必填参数》
     * 项目 groupId
     * eg: com.ilabservice.lowcode.modeling
     */
    String parentPack;

    /**
     * 《非必填》，默认为auto 
     * 作者
     */
    String auth;

    /**
     * 《非必填参数》默认为：当前工程/auto/lab-auto/lab-auto-client
     *
     * 本地项目 client 代码绝对路径
     * eg: /Users/xuejianjun/code/lowcode-modeling/lowcode-modeling/lowcode-modeling-client
     */
    String projectPathClient;

    /**
     * 《非必填参数》 默认为：当前工程/auto/lab-auto/lab-auto-service
     * 本地项目 Service 代码绝对路径
     * eg: /Users/xuejianjun/code/lowcode-modeling/lowcode-modeling/lowcode-modeling-service
     */
    String projectPathService;

    /**
     * 流程定义key
     * 用于发起工作流，此参数必须在flowable中定义且已发布
     * 对应业务表中必须包含这两个字段 流程状态(flow_status)和流程实例id(process_instance_id)两个字段
     *
     * 其中流程状态(flow_status) 有以下状态
     *     PENDING, 处理中
     *     FLOW_COMPLETE, 已完成
     *     REJECT,   流程拒绝
     *     WITHDRAW, 流程撤回
     *     ROLLBACK, 流程回退
     */
    private String processDefinitionKey;

}
