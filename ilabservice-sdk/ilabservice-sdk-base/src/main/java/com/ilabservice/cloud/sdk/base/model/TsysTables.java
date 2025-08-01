package com.ilabservice.cloud.sdk.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ilabservice.cloud.sdk.base.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @ClassName TsysTables
 * @Author xuejianjun
 * @date 2021.05.18 09:40
 */
public class TsysTables implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.tableModel = StringUtils.columnToJava(this.tableName);
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

    public TsysTables(String tableName, String engine, String tableComment, String tableModel,
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

    public TsysTables() {
        super();
    }

    public String getTableModel() {
        return tableModel;
    }

    public void setTableModel(String tableModel) {
        this.tableModel = tableModel;
    }

    public String getTableModel_a() {
        String tablename = this.getTableModel();
        if (tablename == null) {
            return tablename;
        }
        return StringUtils.firstLowerCase(tablename);
    }

    public void setTableModel_a(String tableModel_a) {
        this.tableModel_a = tableModel_a;
    }

}