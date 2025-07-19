package com.ilabservice.cloud.sdk.base.model.entity;

import lombok.Data;

/**
 * @Description 列信息 实体
 * @ClassName BeanColumnEntity
 * @Author xuejianjun
 * @date 2021.05.18 09:36
 */
@Data
public class BeanColumnEntity {

    /**
     * 表\目录
     **/
    private String table_catalog;
    /**
     * 是否为null
     **/
    private String is_nullable;
    /**
     * 表名
     **/
    private String table_name;
    /**
     * 数据库
     **/
    private String table_schema;
    /**
     * 额外的 EXTRA": "auto_increment  自增id
     **/
    private String extra;
    /**
     * 列名
     **/
    private String column_name;
    /**
     * 主键 PRI
     **/
    private String column_key;
    /**
     * 数字精度
     **/
    private String numeric_precision;
    /**
     * 权限
     **/
    private String privileges;
    /**
     * 列注释
     **/
    private String column_comment;
    /**
     * 数字刻度
     **/
    private String numeric_scale;
    /**
     * 列/类型
     **/
    private String column_type;
    /**
     * 生成表达式
     **/
    private String generation_expression;
    /**
     * 序数位置
     **/
    private String ordinal_position;
    /**
     * 数据类型
     **/
    private String data_type;
    /**
     * 默认值
     **/
    private String column_default;

    /**
     * 字符最大长度
     **/
    private String character_maximum_length;


    /**
     * 字符\八位字节\长度
     **/
    private String character_octet_length;

    /**
     * 日期时间精度
     **/
    private String datetime_precision;


    /**
     * 字符集名称
     **/
    private String character_set_name;

    /**
     * 排序规则名称
     **/
    private String collation_name;

    /**实体 类型**/
    private String beanType=getBeanType();

    public String getBeanType() {
        String type=this.getData_type();
        String returnStr="java.lang.String";
        if(type==null) {
            return returnStr;
        }
        switch(type) {
            case "tinyint" :
                returnStr="java.lang.Integer";
                break;
            case "smallint" :
                returnStr="java.lang.Integer";
                break;
            case "int" :
                returnStr="java.lang.Integer";
                break;
            case "bigint" :
                returnStr="java.lang.Long";
                break;
            case "mediumint" :
                returnStr="java.lang.Integer";
                break;
            case "integer" :
                returnStr="java.lang.Integer";
                break;
            case "float" :
                returnStr="java.lang.Float";
                break;
            case "double" :
                returnStr="java.lang.Double";
                break;
            case "decimal" :
                returnStr="java.math.BigDecimal";
                break;
            case "bit" :
                returnStr="java.lang.Byte";
                break;
            case "char" :
                returnStr="java.lang.Character";
                break;
            case "varchar" :
                returnStr="java.lang.String";
                break;
            case "tinytext" :
                returnStr="java.lang.String";
                break;
            case "text" :
                returnStr="java.lang.String";
                break;
            case "mediumtext" :
                returnStr="java.lang.String";
                break;
            case "longtext" :
                returnStr="java.lang.String";
                break;
            case "date" :
                returnStr="java.util.Date";
                break;
            case "datetime" :
                returnStr="java.util.Date";
                break;
            case "timestamp" :
                returnStr="java.util.Date";
                break;
            default:
                break;
        }
        return returnStr;
    }
}
