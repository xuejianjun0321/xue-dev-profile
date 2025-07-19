package com.ilabservice.cloud.sdk.base.model.convert;

import com.ilabservice.cloud.sdk.base.model.BeanColumn;
import com.ilabservice.cloud.sdk.base.model.TsysTables;
import com.ilabservice.cloud.sdk.base.model.entity.BeanColumnEntity;
import com.ilabservice.cloud.sdk.base.model.entity.TableEntity;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:42:57
 * @Description:
 */
public class TsysTablesConvert {

    /**
     * TsysTableEntity TO TsysTables
     * @param tsysTableEntity
     * @return
     */
    public static TsysTables tsysTableEntityToTsysTables(TableEntity tsysTableEntity){
        if (tsysTableEntity == null){
            return null;
        }
        TsysTables tsysTables = new TsysTables();
        tsysTables.setTableName(tsysTableEntity.getTableName());
        tsysTables.setEngine(tsysTableEntity.getEngine());
        tsysTables.setTableComment(tsysTableEntity.getTableComment());
        tsysTables.setTableModel(tsysTableEntity.getTableModel());
        tsysTables.setTableModel_a(tsysTableEntity.getTableModel_a());
        return tsysTables;
    }

    /**
     * BeanColumnEntity TO BeanColumn
     * @param columnEntity
     * @return
     */
    public static BeanColumn beanColumnEntityToBeanColumn(BeanColumnEntity columnEntity) {
        if (columnEntity == null){
            return null;
        }
        BeanColumn beanColumn = new BeanColumn();
        beanColumn.setTable_catalog(columnEntity.getTable_catalog());
        beanColumn.setIs_nullable(columnEntity.getIs_nullable());
        beanColumn.setTable_name(columnEntity.getTable_name());
        beanColumn.setTable_schema(columnEntity.getTable_schema());
        beanColumn.setExtra(columnEntity.getExtra());
        beanColumn.setColumn_name(columnEntity.getColumn_name());
        beanColumn.setColumn_key(columnEntity.getColumn_key());
        beanColumn.setNumeric_precision(columnEntity.getNumeric_precision());
        beanColumn.setPrivileges(columnEntity.getPrivileges());
        beanColumn.setColumn_comment(columnEntity.getColumn_comment());
        beanColumn.setNumeric_scale(columnEntity.getNumeric_scale());
        beanColumn.setColumn_type(columnEntity.getColumn_type());
        beanColumn.setGeneration_expression(columnEntity.getGeneration_expression());
        beanColumn.setOrdinal_position(columnEntity.getOrdinal_position());
        beanColumn.setData_type(columnEntity.getData_type());
        beanColumn.setColumn_default(columnEntity.getColumn_default());
        beanColumn.setCharacter_maximum_length(columnEntity.getCharacter_maximum_length());
        beanColumn.setCharacter_octet_length(columnEntity.getCharacter_octet_length());
        beanColumn.setDatetime_precision(columnEntity.getDatetime_precision());
        beanColumn.setCharacter_set_name(columnEntity.getCharacter_set_name());
        beanColumn.setCollation_name(columnEntity.getCollation_name());
        return beanColumn;
    }

}
