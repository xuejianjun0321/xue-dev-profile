package com.ilabservice.cloud.sdk.base.template;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import com.ilabservice.cloud.sdk.base.constants.CommandContext;
import com.ilabservice.cloud.sdk.base.enums.CommonResultEnum;
import com.ilabservice.cloud.sdk.base.enums.FlagEnum;
import com.ilabservice.cloud.sdk.base.model.BeanColumn;
import com.ilabservice.cloud.sdk.base.model.TableObjectColumn;
import com.ilabservice.cloud.sdk.base.model.TsysTables;
import com.ilabservice.cloud.sdk.base.model.generate.ComplexGenerateParma;
import com.ilabservice.cloud.sdk.base.util.AssertUtil;
import com.ilabservice.cloud.sdk.base.util.CommandBeanContextUtil;
import com.ilabservice.cloud.sdk.base.util.JacksonUtil;
import com.ilabservice.cloud.sdk.base.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description 生成文件抽象类
 * @ClassName AbstractGenerateFile
 * @Author xuejianjun
 * @date 2021.05.18 13:46
 */
@Slf4j
public abstract class AbstractGenerateFile<Void> extends AbstractInsCommandComponent<Void> {


    /**
     * 项目路径
     */
    protected String projectPath;

    /**
     * 项目包名
     */
    protected String packageName;

    /**
     * 模板路径
     */
    protected String templatePath;

    /**
     * 作者
     */
    protected String author;

    /**
     * 表名
     */
    protected String tableName;

    /**
     * 业务key
     */
    protected String moduleKey;

    /**
     * 业务名称
     */
    protected String moduleName;

    /**
     * 是否主表
     */
    protected FlagEnum primaryFlag;

    /**
     * 是否excel服务
     */
    protected FlagEnum isGenerateExcelService;


    /**
     * 模型关系对象信息
     */
    protected ComplexGenerateParma complexGenerateParma;


    public static final String PARENT_PACK = "parentPack";

    /**
     * 作者
     */
    public static final String AUTHOR = "author";

    /**
     * 前置处理
     *
     * @param commandContext 上下文
     */
    @Override
    public void before(CommandContext commandContext) {
        log.info("生成文件前置处理  insContext={}.",
                JacksonUtil.toJsonString(commandContext));
    }


    /**
     * 处理集合
     *
     * @param insContext insContext
     * @return Void
     */
    @Override
    public Void handle(CommandContext insContext) {

        loadTemplate(insContext);

        initTemplateParamMap(insContext);

        buildTemplateParamMap(insContext);

        buildTableInfo(insContext);

        autoGenerateCode(insContext);

        return null;
    }


    /**
     * 加载模板
     *
     * @param commandContext 上下文
     */
    public abstract void loadTemplate(CommandContext commandContext);

    /**
     * 构建模板参数
     *
     * @param commandContext 上下文
     */
    public abstract void buildTemplateParamMap(CommandContext commandContext);


    /**
     * 初始化模板参数
     *
     * @param commandContext 上下文
     */
    public void initTemplateParamMap(CommandContext commandContext) {
        buildTemplates(commandContext);

        commandContext.setProjectPath(projectPath);
        commandContext.setPackageName(packageName);
        Map<String, Object> map = commandContext.getTemplateParamMap();
        map.put(AUTHOR, author);
        map.put("datetime", new DateTime());
        map.put("tableName", tableName);
        map.put(PARENT_PACK, commandContext.getPackageName());
        if (StringUtils.isNotEmpty(moduleKey)){
            map.put("moduleKey", StringUtils.firstUpperCase(moduleKey));
            map.put("moduleKey_a", StringUtils.firstLowerCase(moduleKey));
            map.put("moduleName", moduleName);
            String tableName =  StringUtils.firstUpperCase(
                    StringUtils.underlineToHump(
                            this.tableName.replaceFirst("t_", "")));
            commandContext.setTableName(tableName);
        }
    }



    /**
     * 构建表信息
     *
     * @param insContext 上下文
     */
    public void buildTableInfo(CommandContext insContext) {
        String tableName = (String) insContext.getTemplateParamMap().get("tableName");
        AssertUtil.assertNotNull(tableName, CommonResultEnum.NOT_TABLE_NAME);
        List<TsysTables> list = CommandBeanContextUtil.getGeneratorManager().queryList(tableName);
        AssertUtil.assertTrue(CollectionUtils.isNotEmpty(list), CommonResultEnum.NOT_TABLE);
        List<BeanColumn> beanColumns = CommandBeanContextUtil.getGeneratorManager()
                .queryColumns2(tableName);
        TsysTables tables = list.get(0);
        commandContext.setTables(tables);
        insContext.getTemplateParamMap().put("fileName", commandContext.getTableName());
        //数据库表数据
        insContext.getTemplateParamMap().put("TsysTables", tables);
        //字段集合
        //过滤base字段
        Set<String> baseColumns = new HashSet<>();
        Collections.addAll(baseColumns, "id", "org_id", "product_id",
                "created_by", "created_date", "last_modified_by", "last_modified_date", "data_status");
        beanColumns = beanColumns.stream().filter(v -> !baseColumns.contains(v.getColumn_name())).collect(Collectors.toList());
        insContext.getTemplateParamMap().put("beanColumns", beanColumns);

        if (null != complexGenerateParma && CollectionUtil.isNotEmpty(complexGenerateParma.getAttributeList())) {
            List<TableObjectColumn> objectBeanColumnList = complexGenerateParma.getAttributeList().stream().map(i -> {
                TableObjectColumn beanColumn = new TableObjectColumn();
                beanColumn.setAliasName_a(StringUtils.underlineToHump(i.getDependTable().replaceFirst("t_", "")));
                beanColumn.setAliasName(StringUtils.firstUpperCase(beanColumn.getAliasName_a().replaceFirst("t_", "")));
                return beanColumn;
            }).collect(Collectors.toList());
            insContext.getTemplateParamMap().put("objectBeanColumnList", objectBeanColumnList);
            commandContext.setTableName(StringUtils.firstUpperCase(moduleKey));
        }

    }

    /**
     * 自动生成代码
     *
     * @param insContext 上下文
     */
    private void autoGenerateCode(CommandContext insContext) {

        Map<String, Object> templateParamMap = insContext.getTemplateParamMap();
        List<String> templates = insContext.getTemplates();

        if (CollectionUtils.isEmpty(templates)) {
            return;
        }
        List<BeanColumn> beanColumns = JacksonUtil.parseList(JacksonUtil.toJsonString(
                        templateParamMap.get("beanColumns")),
                BeanColumn.class);

        Properties prop = new Properties();
        prop.put("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        VelocityContext context = new VelocityContext(templateParamMap);

        //需要导入的java类
        templateParamMap.put("JavaClassPackages", getJavaClassPackage(beanColumns));

        try {
            for (String template : templates) {
                String filepath = buildFilePath(template, insContext);
                if (StringUtils.isEmpty(filepath)) {
                    log.error("自动生成代码获取文件路径为空，跳过自动生成代码");
                    continue;
                }
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                File file = new File(filepath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                try (FileOutputStream outStream = new FileOutputStream(file, false);
                     OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF-8");
                     BufferedWriter sw = new BufferedWriter(writer)) {
                    tpl.merge(context, sw);
                    sw.flush();
                    log.info("成功生成Java文件:" + filepath);
                }
            }
        } catch (IOException e) {
            log.error("自动生成代码发生异常！", e);
        }

    }

    /**
     * 构建文件路径
     *
     * @param commandContext 上下文
     */
    public String buildFilePath(String template, CommandContext commandContext) {

        String targetPath = commandContext.getProjectPath();

        String filePackageName = commandContext.getPackageName().replace(".", File.separator)
                + File.separator + commandContext.getPackageMid().replace(".", File.separator);

        String className = commandContext.getTableName();

        String resourceFilePath = template.replaceFirst(commandContext.getTemplatePath(), "");
        resourceFilePath = resourceFilePath.substring(0, resourceFilePath.lastIndexOf("/"));

        String name = template.substring(template.lastIndexOf("/"));
        name = name.substring(1, name.indexOf("."));

        String targetDalName =
                targetPath + File.separator + "src" + File.separator + "main" + File.separator + "java"
                        + File.separator + filePackageName + resourceFilePath + File.separator;

        String targetXml =
                targetPath + File.separator + "src" + File.separator + "main" + File.separator + "resources"
                        + resourceFilePath + File.separator;

        // .java
        if (template.contains(".java.vm")) {
            return targetDalName + className + name + ".java";
        }
        // .xml
        if (template.contains(".xml.vm")) {
            return targetXml + className + name + ".xml";
        }

        return null;
    }


    /**
     * 获取javaModel需要的导入类
     */
    public static String getJavaClassPackage(List<BeanColumn> beanColumns) {
        Map<String, String> map = new HashMap<>();
        StringBuffer buffer = new StringBuffer();
        if (CollectionUtils.isEmpty(beanColumns)) {
            return null;
        }
        for (BeanColumn beanColumn : beanColumns) {
            map.put(beanColumn.getBeanType(), beanColumn.getBeanType());
        }
        if (map.size() > 0) {
            boolean time = false;
            for (String key : map.keySet()) {
                if (!"java.lang.String".equals(key)) {
                    buffer.append("import " + map.get(key) + ";\n");
                }
                if ("java.util.Date".equals(key) && !time) {
                    time = true;
                    buffer.append("import com.fasterxml.jackson.annotation.JsonFormat;\n");
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 后置处理
     *
     * @param commandContext 上下文
     */
    @Override
    public void after(CommandContext commandContext) {
        log.info("生成文件后置处理, insContext={}.",
                JacksonUtil.toJsonString(commandContext));
    }



    /**
     * 从jar包加载模板
     * @param commandContext 上下文
     */
    protected void buildTemplates(CommandContext commandContext) {
        List<File> files = commandContext.getFiles();
        Set<String> fileNames = new HashSet<>();
        List<String> templates = commandContext.getTemplates();
        for (File file : files) {
            if (file.getName().endsWith(".vm")) {
                String filePath = file.getPath().substring(file.getPath().indexOf(templatePath));
                fileNames.add(filePath);
            }
        }
        templates.addAll(fileNames);
    }

}
