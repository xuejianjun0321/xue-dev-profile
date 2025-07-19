package com.ilabservice.cloud.sdk.base.model.generate;

import com.ilabservice.cloud.sdk.base.enums.FlagEnum;
import lombok.Data;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月22日 11:00:15
 * @Description: 组件对象
 */
@Data
public class Attribute {

    /**
     * 关联服务：关联服务：表名
     **/
    private String dependTable;

    /**
     * 是否1：N
     */
    private FlagEnum isToMany;

    /**
     * 是否生成Excel服务
     */
    private FlagEnum isGenerateExcelService;

}
