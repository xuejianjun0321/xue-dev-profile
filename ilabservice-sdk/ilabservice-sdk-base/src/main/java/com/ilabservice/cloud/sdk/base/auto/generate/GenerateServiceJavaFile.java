package com.ilabservice.cloud.sdk.base.auto.generate;

import com.ilabservice.cloud.sdk.base.auto.manager.impl.AutoCodeManagerImpl;
import com.ilabservice.cloud.sdk.base.constants.CommandContext;
import com.ilabservice.cloud.sdk.base.enums.FlagEnum;
import com.ilabservice.cloud.sdk.base.model.generate.ComplexGenerateParma;
import com.ilabservice.cloud.sdk.base.template.AbstractGenerateFile;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月17日 11:14:22
 * @Description:
 */
@Slf4j
public class GenerateServiceJavaFile extends AbstractGenerateFile<Object> {


    public GenerateServiceJavaFile(ComplexGenerateParma complexGenerateParma,String projectPath,
                                   FlagEnum primaryFlag) {
        this.complexGenerateParma = complexGenerateParma;
        this.tableName = complexGenerateParma.getTableName();
        this.moduleName = complexGenerateParma.getModuleName();
        this.moduleKey = complexGenerateParma.getModuleKey();
        this.primaryFlag = primaryFlag;
        this.projectPath = projectPath;
        this.packageName = complexGenerateParma.getParentPack();
        this.author = complexGenerateParma.getAuth();
        this.templatePath = "/ils/client";
    }

    @Override
    public void loadTemplate(CommandContext commandContext) {
        List<File> files = commandContext.getFiles();
        String path = AutoCodeManagerImpl.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        files.add(new File(path + "!/ils/client/service/Service.java.vm"));
    }


    @Override
    public void buildTemplateParamMap(CommandContext commandContext) {
        commandContext.setPackageMid("client");
        commandContext.setTemplatePath("ils/client");
    }


}
