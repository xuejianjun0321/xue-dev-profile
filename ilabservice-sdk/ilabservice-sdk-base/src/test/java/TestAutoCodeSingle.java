// import com.ilabservice.cloud.sdk.base.auto.manager.AutoCodeManager;
// import com.ilabservice.cloud.sdk.base.model.generate.SingleGenerateParma;
//
// import javax.annotation.Resource;
//
// /**
//  * @Author: jj.xue
//  * @createTime: 2022年11月30日 13:45:56
//  * @Description:
//  */
// public class TestAutoCodeSingle {
//
//     /** 表结构
//
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
//      *
//      */
//
//
//
//     @Resource
//     private AutoCodeManager autoCodeService;
//
//     public void testSingle() {
//
//         SingleGenerateParma singleGenerateParma = new SingleGenerateParma();
//         singleGenerateParma.setTableName("t_app");
//         singleGenerateParma.setProjectPathClient("/Users/xuejianjun/code/lowcode-modeling/lowcode-modeling/lowcode-modeling-client");
//         singleGenerateParma.setProjectPathService("/Users/xuejianjun/code/lowcode-modeling/lowcode-modeling/lowcode-modeling-service");
//         singleGenerateParma.setParentPack("com.ilabservice.lowcode.modeling");
//         singleGenerateParma.setAuth("jj.xue");
//
//         autoCodeService.generateJavaCode(singleGenerateParma);
//
//     }
//
// }
