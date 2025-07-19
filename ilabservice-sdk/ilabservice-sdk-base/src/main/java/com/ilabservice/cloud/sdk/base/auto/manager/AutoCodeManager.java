package com.ilabservice.cloud.sdk.base.auto.manager;

import com.ilabservice.cloud.sdk.base.model.generate.ComplexGenerateParma;
import com.ilabservice.cloud.sdk.base.model.generate.NestGenerateParma;
import com.ilabservice.cloud.sdk.base.model.generate.SingleGenerateParma;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月12日 18:38:09
 * @Description: Java代码自动生成器
 */
public interface AutoCodeManager {

    /**
     *  使用说明
     *  目前代码生成器生成的目录是 iLabService标准目录结构 ，
     *  如果非标准工程接入，请先依赖替换父pom为 ilabservice-cloud-parent-service
     *
     *  建议设计好表结构后一次性生成。
     *  在生成代码基础上已经做了二开的同学，特别注意！！！
     *  在表结构变更之后不要用代码生成器，否则会覆盖你写的代码。
     *  也可以不指定本地代码项目路径，在默认路径生成出来后再手动复制。
     *
     */


    /**
     * 单表结构自动生成Java代码
     *
     * @param singleGenerateParma  单表生成Java类文件参数
     */
    void generateJavaCode(SingleGenerateParma singleGenerateParma);



    /**
     * 复杂表结构自动生成Java代码
     *
     * 目前可以生成 1：N 数据表关系的Java代码
     * 注意：从表中的主表id必须 是主表名称去掉前缀_Id 比如 app_id  其中 app 是表 t_app 的名称
     *
     * @param complexGenerateParma  复杂表关系生成Java类文件参数
     */
    void generateJavaCode(ComplexGenerateParma complexGenerateParma);



    /**
     * 嵌套模型关系自动生成Java代码
     *
     * 目前可以生成 1：N：N:N 数据表关系的Java代码
     * 注意：从表中的主表id必须 是主表名称去掉前缀_Id 比如 app_id  其中 app 是表 t_app 的名称
     *
     * @param nestGenerateParma  套娃模型关系生产Java类文件入参
     */
    void generateJavaCode(NestGenerateParma nestGenerateParma);
}

