package com.ilabservice.cloud.sdk.base.auto.generate;

import com.ilabservice.cloud.sdk.base.auto.manager.impl.AutoCodeManagerImpl;
import com.ilabservice.cloud.sdk.base.constants.CommandContext;
import com.ilabservice.cloud.sdk.base.enums.FlagEnum;
import com.ilabservice.cloud.sdk.base.model.generate.ComplexGenerateParma;
import com.ilabservice.cloud.sdk.base.template.AbstractGenerateFile;
import com.ilabservice.cloud.sdk.base.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月17日 09:44:52
 * @Description:
 */
@Slf4j
public class GenerateServiceImplJavaFile extends AbstractGenerateFile<Object> {


    public GenerateServiceImplJavaFile(ComplexGenerateParma complexGenerateParma,String projectPath ,
                                       FlagEnum primaryFlag) {
        this.complexGenerateParma = complexGenerateParma;
        this.tableName = complexGenerateParma.getTableName();
        this.moduleName = complexGenerateParma.getModuleName();
        this.moduleKey = complexGenerateParma.getModuleKey();
        this.primaryFlag = primaryFlag;
        this.projectPath = projectPath;
        this.packageName = complexGenerateParma.getParentPack();
        this.templatePath = "/ils/service";
        this.author = complexGenerateParma.getAuth();
    }


    @Override
    public void loadTemplate(CommandContext commandContext) {
        List<File> files = commandContext.getFiles();
        String path = AutoCodeManagerImpl.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        files.add(new File(path + "!/ils/service/client/service/ServiceImpl.java.vm"));
        if (StringUtils.isNotEmpty(complexGenerateParma.getProcessDefinitionKey())){
            files.add(new File(path + "!/ils/service/listener/FlowMsgListener.java.vm"));
        }

    }

    @Override
    public void buildTemplateParamMap(CommandContext commandContext) {
        Map<String, Object> map = commandContext.getTemplateParamMap();
        String tableName = StringUtils.firstUpperCase(StringUtils.underlineToHump(
                this.tableName.replaceFirst("t_", "")));
        map.put("masterTableName", StringUtils.firstUpperCase(tableName));
        map.put("masterTableName_a", StringUtils.firstLowerCase(tableName));
        map.put("processDefinitionKey", complexGenerateParma.getProcessDefinitionKey());
        commandContext.setPackageMid("service");
        commandContext.setTemplatePath("ils/service");

    }

}
