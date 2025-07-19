// import com.ilabservice.cloud.sdk.base.auto.manager.AutoCodeManager;
// import com.ilabservice.cloud.sdk.base.enums.FlagEnum;
// import com.ilabservice.cloud.sdk.base.model.generate.Attribute;
// import com.ilabservice.cloud.sdk.base.model.generate.NestGenerateParma;
// import com.ilabservice.cloud.sdk.base.model.generate.SubModule;
//
// import javax.annotation.Resource;
// import java.util.ArrayList;
// import java.util.List;
//
// /**
//  * @Author: jj.xue
//  * @createTime: 2022年11月30日 13:47:54
//  * @Description:
//  */
// public class TestAutoCodeNest {
//
//     /** 表结构
//      CREATE TABLE `t_account` (
//      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户主键',
//      `account_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
//      `org_id` bigint DEFAULT NULL,
//      `product_id` bigint DEFAULT NULL,
//      `created_by` bigint DEFAULT NULL,
//      `created_date` datetime DEFAULT NULL,
//      `last_modified_by` bigint DEFAULT NULL,
//      `last_modified_date` datetime DEFAULT NULL,
//      `data_status` varchar(255) DEFAULT NULL,
//      PRIMARY KEY (`id`)
//      ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
//
//      CREATE TABLE `t_gift` (
//      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
//      `gift_name` varchar(255) DEFAULT NULL COMMENT '礼券名称',
//      `price` decimal(10,2) DEFAULT NULL COMMENT '价值',
//      `org_id` bigint DEFAULT NULL,
//      `product_id` bigint DEFAULT NULL,
//      `created_by` bigint DEFAULT NULL,
//      `created_date` datetime DEFAULT NULL,
//      `last_modified_by` bigint DEFAULT NULL,
//      `last_modified_date` datetime DEFAULT NULL,
//      `data_status` varchar(255) DEFAULT NULL,
//      PRIMARY KEY (`id`)
//      ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='礼券表';
//
//      CREATE TABLE `t_gift_record` (
//      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '中奖记录主键',
//      `account_id` bigint DEFAULT NULL COMMENT '用户id',
//      `gift_code` varchar(255) DEFAULT NULL COMMENT '礼券兑换码',
//      `gift_price` int DEFAULT NULL COMMENT '礼券是否使用',
//      `gift_id` bigint DEFAULT NULL COMMENT '礼券id',
//      `org_id` bigint DEFAULT NULL,
//      `product_id` bigint DEFAULT NULL,
//      `created_by` bigint DEFAULT NULL,
//      `created_date` datetime DEFAULT NULL,
//      `last_modified_by` bigint DEFAULT NULL,
//      `last_modified_date` datetime DEFAULT NULL,
//      `data_status` varchar(255) DEFAULT NULL,
//      PRIMARY KEY (`id`)
//      ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='中奖记录表';
//
//      CREATE TABLE `t_sign` (
//      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '签到主键',
//      `account_id` bigint DEFAULT NULL COMMENT '用户Id',
//      `record_num` int DEFAULT NULL COMMENT '记录次数',
//      `record_day` varchar(255) DEFAULT NULL COMMENT '记录日期',
//      `org_id` bigint DEFAULT NULL,
//      `product_id` bigint DEFAULT NULL,
//      `created_by` bigint DEFAULT NULL,
//      `created_date` datetime DEFAULT NULL,
//      `last_modified_by` bigint DEFAULT NULL,
//      `last_modified_date` datetime DEFAULT NULL,
//      `data_status` varchar(255) DEFAULT NULL,
//      PRIMARY KEY (`id`)
//      ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='签到记录表';
//      *
//      */
//
//     @Resource
//     private AutoCodeManager autoCodeService;
//
//     public void testNest1() {
//         NestGenerateParma nestGenerateParma = new NestGenerateParma();
//         nestGenerateParma.setModuleKey("accountModel");
//         nestGenerateParma.setModuleName("用户组件");
//         List<Attribute> componentList = new ArrayList<>();
//         Attribute component = new Attribute();
//         component.setDependTable("t_sign");
//         componentList.add(component);
//         nestGenerateParma.setAttributeList(componentList);
//         List<SubModule> subModuleList = new ArrayList<>();
//         SubModule subModule = new SubModule();
//         subModule.setTableName("t_gift_record");
//         subModule.setModuleKey("giftRecordModel");
//         subModule.setModuleName("中奖记录");
//
//         List<Attribute> componentList1 = new ArrayList<>();
//         Attribute component1 = new Attribute();
//         component1.setDependTable("t_gift");
//         component1.setIsToMany(FlagEnum.NO);
//         componentList1.add(component1);
//         subModule.setAttributeList(componentList1);
//         subModuleList.add(subModule);
//
//         nestGenerateParma.setSubModuleList(subModuleList);
//         nestGenerateParma.setTableName("t_account");
//         nestGenerateParma.setParentPack("com.ilabservice.lowcode.modeling");
//         nestGenerateParma.setAuth("jj.xue");
//         nestGenerateParma.setProjectPathClient("/Users/xuejianjun/code/lowcode-modeling/lowcode-modeling/lowcode-modeling-client");
//         nestGenerateParma.setProjectPathService("/Users/xuejianjun/code/lowcode-modeling/lowcode-modeling/lowcode-modeling-service");
//
//         autoCodeService.generateJavaCode(nestGenerateParma);
//
//     }
//
// }
