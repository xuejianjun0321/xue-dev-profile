package com.ilabservice.cloud.sdk.base.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @Author: jj.xue
 * @createTime: 2022年11月16日 15:30:30
 * @Description:
 */
@NoArgsConstructor
@Data
public class TableObjectColumn {


    /**
     * 模型key
     */
    private String moduleKey;

    /**
     * 表名
     **/
    private String tableName;

    /**
     * 表别名 驼峰命名法 test_code->TestCode
     */
    private String aliasName;

    /**
     * 表别名（驼峰命名法）（首字母小写）
     **/
    private String aliasName_a;

    /**
     * 属性
     */
    private List<BeanColumn> beanColumnList;

}

