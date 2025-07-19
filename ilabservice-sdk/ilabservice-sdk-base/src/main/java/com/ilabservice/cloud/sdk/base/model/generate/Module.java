package com.ilabservice.cloud.sdk.base.model.generate;

import com.ilabservice.cloud.sdk.base.util.StringUtils;
import lombok.Data;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月24日 17:49:40
 * @Description:
 */
@Data
public class Module {


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
     * 模块Key 大写
     */
    private String moduleKeyA;




    public String getModuleKey_A() {
        if (this.getModuleKey() != null) {
            return StringUtils.firstUpperCase(moduleKey);
        }
        return this.getModuleKey();
    }

}
