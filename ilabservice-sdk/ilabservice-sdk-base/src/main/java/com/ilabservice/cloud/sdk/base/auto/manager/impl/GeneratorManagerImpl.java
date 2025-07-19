package com.ilabservice.cloud.sdk.base.auto.manager.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import cn.hutool.json.JSONUtil;
import com.ilabservice.cloud.sdk.base.auto.manager.GeneratorManager;
import com.ilabservice.cloud.sdk.base.model.BeanColumn;
import com.ilabservice.cloud.sdk.base.model.TsysTables;
import com.ilabservice.cloud.sdk.base.model.convert.TsysTablesConvert;
import com.ilabservice.cloud.sdk.base.model.dao.GeneratorMapper;
import com.ilabservice.cloud.sdk.base.model.entity.BeanColumnEntity;
import com.ilabservice.cloud.sdk.base.model.entity.TableEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * @Description 查询数据库表信息 实现类
 * @ClassName GeneratorManagerImpl
 * @Author xuejianjun
 * @date 2021.05.18 09:40
 */
@Slf4j
@Component
public class GeneratorManagerImpl implements GeneratorManager {


    @Resource
    private GeneratorMapper generatorMapper;


    @Override
    public List<TsysTables> queryList(String tableName){

        List<TsysTables> tsysTables = new ArrayList<>();

        List<TableEntity> tsysTableEntities = generatorMapper.queryList(tableName);
        if (CollectionUtils.isNotEmpty(tsysTableEntities)){
            if (CollectionUtils.isNotEmpty(tsysTableEntities)){
                for (TableEntity tsysTableEntity : tsysTableEntities) {
                    tsysTables.add(TsysTablesConvert.tsysTableEntityToTsysTables(tsysTableEntity));
                }
            }
        }
        return tsysTables;
    }


    @Override
    public List<BeanColumn> queryColumns2(String tableName){
        log.info("queryColumns2>>>"+ JSONUtil.toJsonPrettyStr(generatorMapper.queryColumns3(tableName)));
        List<BeanColumnEntity> beanColumnEntities = generatorMapper.queryColumns2(tableName);
        List<BeanColumn> beanColumns = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(beanColumnEntities)){
            for (BeanColumnEntity columnEntity : beanColumnEntities) {
                beanColumns.add(TsysTablesConvert.beanColumnEntityToBeanColumn(columnEntity));
            }
        }
        return beanColumns;
    }


}
