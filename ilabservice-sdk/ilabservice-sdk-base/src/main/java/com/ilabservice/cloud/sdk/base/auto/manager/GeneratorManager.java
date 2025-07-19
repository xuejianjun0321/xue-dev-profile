package com.ilabservice.cloud.sdk.base.auto.manager;

import com.ilabservice.cloud.sdk.base.model.BeanColumn;
import com.ilabservice.cloud.sdk.base.model.TsysTables;

import java.util.List;

/**
 * @Description 查询数据库表信息
 * @ClassName GeneratorManager
 */
public interface GeneratorManager {


    /**
     * 查询具体某表信息
     * @param tableName 表名
     * @return 表信息
     */
    List<TsysTables> queryList(String tableName);


    /**
     * 查询表详情
     * @param tableName 表名
     * @return 表详情
     */
    List<BeanColumn> queryColumns2(String tableName);

}