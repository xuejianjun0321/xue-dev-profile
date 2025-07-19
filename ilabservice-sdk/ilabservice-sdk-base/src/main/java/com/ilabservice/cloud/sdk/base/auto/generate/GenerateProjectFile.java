package com.ilabservice.cloud.sdk.base.auto.generate;

import cn.hutool.core.date.DateTime;
import com.ilabservice.cloud.sdk.base.auto.manager.impl.AutoCodeManagerImpl;
import com.ilabservice.cloud.sdk.base.constants.CommandContext;
import com.ilabservice.cloud.sdk.base.template.AbstractGenerateFile;
import com.ilabservice.cloud.sdk.base.util.SnowflakeIdWorker;
import com.ilabservice.cloud.sdk.base.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月22日 10:33:38
 * @Description: 单表生成所有Java类文件
 */
public class GenerateProjectFile extends AbstractGenerateFile<Object> {

    private String parentPack;
    private String packageMin;
    private String processDefinitionKey;

    public GenerateProjectFile(String tableName, String parentPack, String packageMin, String auth,
                               String projectPath, String templatePath,String processDefinitionKey) {
        this.tableName = tableName;
        this.parentPack = parentPack;
        this.packageMin = packageMin;
        this.author = auth;
        this.templatePath = templatePath;
        this.projectPath = projectPath;
        this.processDefinitionKey = processDefinitionKey;

    }

    @Override
    public void loadTemplate(CommandContext commandContext) {
        String path = AutoCodeManagerImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        List<File> files = commandContext.getFiles();
        if ("/ils/client".equals(templatePath)) {
            files.add(new File(path + "!/ils/client/api/Api.java.vm"));
            files.add(new File(path + "!/ils/client/dto/Dto.java.vm"));
            files.add(new File(path + "!/ils/client/service/Service.java.vm"));
            files.add(new File(path + "!/ils/client/vo/Vo.java.vm"));
        } else {
            files.add(new File(path + "!/ils/service/client/api/ApiImpl.java.vm"));
            files.add(new File(path + "!/ils/service/client/service/ServiceImpl.java.vm"));
            files.add(new File(path + "!/ils/service/convertor/Convertor.java.vm"));
            files.add(new File(path + "!/ils/service/mapper/Dao.xml.vm"));
            files.add(new File(path + "!/ils/service/model/dao/Dao.java.vm"));
            files.add(new File(path + "!/ils/service/model/domain/impl/DomainImpl.java.vm"));
            files.add(new File(path + "!/ils/service/model/domain/Domain.java.vm"));
            files.add(new File(path + "!/ils/service/model/entity/.java.vm"));
            files.add(new File(path + "!/ils/service/model/query/QueryWrapper.java.vm"));
            if (StringUtils.isNotEmpty(processDefinitionKey)){
                files.add(new File(path + "!/ils/service/listener/FlowMsgListener.java.vm"));
            }
        }

    }

    @Override
    public void initTemplateParamMap(CommandContext commandContext) {
        super.buildTemplates(commandContext);

        commandContext.setProjectPath(projectPath);
        commandContext.setTemplatePath(templatePath);
        commandContext.setPackageName(parentPack);
        commandContext.setPackageMid(packageMin);
        Map<String, Object> map = new HashMap<>(16);
        map.put(PARENT_PACK, parentPack);
        map.put(AUTHOR, author);
        map.put("datetime", new DateTime());
        map.put("SnowflakeIdWorker", SnowflakeIdWorker.class);
        map.put("tableName", tableName);
        String delPrefixTableName = StringUtils.underlineToHump(tableName.replaceFirst("t_" , ""));
        map.put("entityName", StringUtils.lowerCase(delPrefixTableName));
        map.put("masterTableName", StringUtils.firstUpperCase(delPrefixTableName));
        map.put("masterTableName_a", StringUtils.firstLowerCase(delPrefixTableName));
        map.put("processDefinitionKey", processDefinitionKey);
        commandContext.setTemplateParamMap(map);
    }

    @Override
    public void buildTemplateParamMap(CommandContext commandContext) {
        commandContext.setTableName(StringUtils.firstUpperCase(StringUtils.underlineToHump(tableName.replaceFirst("t_", ""))));
    }

}
