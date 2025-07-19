package com.ilabservice.cloud.sdk.base.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.text.WordUtils;

/**
 * @Description  表信息实体
 * @ClassName TableEntity
 * @Author xuejianjun
 */
public class TableEntity {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表引擎
     */
    private String engine;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 表实体名字
     */
    private String tableModel;

    /**
     * 表实体名称 小写
     */
    private String tableModel_a = getTableModel_a();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public String getTableModel_a() {
        String tablename = this.getTableModel();
        if(tablename==null) {
            return tablename;
        }
        return firstLowerCase(tablename);
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.tableModel =columnToJava(this.tableName);
    }

    public String getTableName() {
        return tableName;
    }


    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TableEntity(String tableName, String engine, String tableComment, String tableModel,
                       String tableModel_a,
                       Date createTime) {
        super();
        this.tableName = tableName;
        this.engine = engine;
        this.tableComment = tableComment;
        this.tableModel = tableModel;
        this.tableModel_a = tableModel_a;
        this.createTime = createTime;
    }

    public TableEntity() {
        super();
    }

    public String getTableModel() {
        return tableModel;
    }

    public void setTableModel(String tableModel) {
        this.tableModel = tableModel;
    }

    public void setTableModel_a(String tableModel_a) {
        this.tableModel_a = tableModel_a;
    }


    /**
     * 首字母小写
     *
     * @param name
     * @return
     */
    private static String firstLowerCase(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;

    }


    private static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "" );
    }

}
