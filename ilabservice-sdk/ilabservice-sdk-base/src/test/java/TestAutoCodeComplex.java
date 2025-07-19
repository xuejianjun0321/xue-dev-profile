// import com.ilabservice.cloud.sdk.base.auto.manager.AutoCodeManager;
// import com.ilabservice.cloud.sdk.base.enums.FlagEnum;
// import com.ilabservice.cloud.sdk.base.model.generate.Attribute;
// import com.ilabservice.cloud.sdk.base.model.generate.ComplexGenerateParma;
//
// import javax.annotation.Resource;
// import java.util.ArrayList;
// import java.util.List;
//
// /**
//  * @Author: jj.xue
//  * @createTime: 2022年11月30日 13:47:13
//  * @Description:
//  */
// public class TestAutoCodeComplex {
//
//     /** 表结构
//      CREATE TABLE `t_app` (
//      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
//      `app_name` varchar(64) NOT NULL COMMENT '应用名称',
//      `app_key` varchar(64) NOT NULL COMMENT '应用key',
//      `description` varchar(255) NOT NULL COMMENT '描述',
//      `git_addr` varchar(255) NOT NULL COMMENT 'git地址',
//      `git_user` varchar(64) NOT NULL COMMENT 'git账户',
//      `git_password` varchar(64) NOT NULL COMMENT 'git密码',
//      `app_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'INIT' COMMENT 'App状态：INIT初始，PUBLISH发布中，ONLINE在线',
//      `version` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '版本号',
//      `org_id` bigint DEFAULT NULL COMMENT '组织 id',
//      `product_id` bigint DEFAULT NULL COMMENT '产品id',
//      `created_by` bigint DEFAULT NULL COMMENT '创建人id',
//      `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
//      `last_modified_by` bigint DEFAULT NULL COMMENT '修改人id',
//      `last_modified_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
//      `data_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'VALID' COMMENT '数据状态；VALID有效，INVALID无效',
//      PRIMARY KEY (`id`),
//      UNIQUE KEY `unx_app_key` (`app_key`)
//      ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用表';
//
//      CREATE TABLE `t_publish_record` (
//      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
//      `app_id` bigint NOT NULL COMMENT 'appID',
//      `app_vsersion` varchar(64) NOT NULL COMMENT 'App版本号',
//      `publish_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'INIT' COMMENT '发布状态:INIT初始，UNDERWAY发布中，FAIL失败，SUCCESS成功 ',
//      `course` varchar(32) NOT NULL COMMENT '进度：BUILD构建，PACKAGE打包，PUBLISH发布',
//      `config_info` json NOT NULL COMMENT '配置信息',
//      `org_id` bigint NOT NULL COMMENT '组织 id',
//      `product_id` bigint NOT NULL COMMENT '产品id',
//      `created_by` bigint NOT NULL COMMENT '创建人id',
//      `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
//      `last_modified_by` bigint DEFAULT NULL COMMENT '修改人id',
//      `last_modified_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
//      `data_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'VALID' COMMENT '数据状态；VALID有效，INVALID无效',
//      PRIMARY KEY (`id`)
//      ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='发布记录表';
//
//      CREATE TABLE `t_model` (
//      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
//      `app_id` bigint NOT NULL COMMENT 'appId',
//      `module_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '模块key',
//      `module_name` varchar(64) NOT NULL COMMENT '模块名称',
//      `master_table` varchar(64) NOT NULL COMMENT '主表',
//      `java_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'java类名',
//      `model_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'INIT' COMMENT '模块状态：INIT初始，CODING代码生成，COMPLETE完成',
//      `org_id` bigint NOT NULL COMMENT '组织 id',
//      `product_id` bigint NOT NULL COMMENT '产品id',
//      `created_by` bigint NOT NULL COMMENT '创建人id',
//      `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
//      `last_modified_by` bigint DEFAULT NULL COMMENT '修改人id',
//      `last_modified_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
//      `data_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'VALID' COMMENT '数据状态；VALID有效，INVALID无效',
//      PRIMARY KEY (`id`),
//      UNIQUE KEY `unx_module_key` (`module_key`),
//      UNIQUE KEY `unx_java_name` (`java_name`,`app_id`) USING BTREE
//      ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='模块表';
//
//      *
//      */
//
//
//
//     @Resource
//     private AutoCodeManager autoCodeService;
//
//     public void moreTestSDK() {
//         ComplexGenerateParma codeConfig = new ComplexGenerateParma();
//         codeConfig.setModuleKey("testMoreTable");
//         codeConfig.setModuleName("测试多表");
//         codeConfig.setTableName("t_app");
//         codeConfig.setParentPack("com.ilabservice.lowcode.modeling");
//         codeConfig.setProjectPathClient("/Users/xuejianjun/code/lowcode-modeling/lowcode-modeling/lowcode-modeling-client");
//         codeConfig.setProjectPathService("/Users/xuejianjun/code/lowcode-modeling/lowcode-modeling/lowcode-modeling-service");
//
//         List<Attribute> componentList = new ArrayList<>();
//         Attribute component = new Attribute();
//         component.setDependTable("t_publish_record");
//         component.setIsGenerateExcelService(FlagEnum.YES);
//         componentList.add(component);
//
//         Attribute component2 = new Attribute();
//         component2.setDependTable("t_model");
//         component2.setIsGenerateExcelService(FlagEnum.YES);
//
//         componentList.add(component2);
//         codeConfig.setAttributeList(componentList);
//         autoCodeService.generateJavaCode(codeConfig);
//
//     }
//
// }
