package com.ilabservice.cloud.sdk.base.model.dao;

import com.ilabservice.cloud.sdk.base.model.entity.BeanColumnEntity;
import com.ilabservice.cloud.sdk.base.model.entity.TableEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Description
 * @ClassName GeneratorMapper
 * @Author xuejianjun
 */
@Mapper
@Repository
public interface GeneratorMapper {

    /**
     * 查询当前所有表
     *
     * @param tableName 表名
     * @return 表信息列表
     */
    List<TableEntity> queryList(String tableName);

    /**
     * 查询具体某表信息
     *
     * @param tableName 表名
     * @return 表信息
     */
    TableEntity queryTable(String tableName);

    /**
     * 查询表详情
     *
     * @param tableName 表名
     * @return list<map>
     */
    List<Map<String, String>> queryColumns(String tableName);

    /**
     * 查询表详情
     *
     * @param tableName 表名
     * @return 列信息列表
     */
    List<BeanColumnEntity> queryColumns2(String tableName);

    /**
     * 查询表详情
     *
     * @param tableName 表名
     * @return list<map>
     */
    List<Map<String, String>> queryColumns3(String tableName);

}