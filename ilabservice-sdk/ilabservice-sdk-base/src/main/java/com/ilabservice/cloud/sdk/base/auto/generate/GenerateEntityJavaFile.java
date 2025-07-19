package com.ilabservice.cloud.sdk.base.auto.generate;

import com.ilabservice.cloud.sdk.base.auto.manager.impl.AutoCodeManagerImpl;
import com.ilabservice.cloud.sdk.base.constants.CommandContext;
import com.ilabservice.cloud.sdk.base.enums.FlagEnum;
import com.ilabservice.cloud.sdk.base.model.generate.ComplexGenerateParma;
import com.ilabservice.cloud.sdk.base.template.AbstractGenerateFile;

import java.io.File;
import java.util.List;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月18日 11:31:39
 * @Description:
 */
public class GenerateEntityJavaFile extends AbstractGenerateFile<Object> {

    public GenerateEntityJavaFile(ComplexGenerateParma complexGenerateParma,String projectPath , FlagEnum primaryFlag) {
        this.complexGenerateParma = complexGenerateParma;
        this.tableName = complexGenerateParma.getTableName();
        this.moduleName = complexGenerateParma.getModuleName();
        this.moduleKey = complexGenerateParma.getModuleKey();
        this.primaryFlag = primaryFlag;
        this.projectPath = projectPath;
        this.packageName = complexGenerateParma.getParentPack();
        this.author = complexGenerateParma.getAuth();
        this.templatePath = "/ils/service";
    }

    @Override
    public void loadTemplate(CommandContext commandContext) {
        List<File> files = commandContext.getFiles();
        String path = AutoCodeManagerImpl.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        files.add(new File(path + "!/ils/service/model/entity/.java.vm"));
        files.add(new File(path + "!/ils/service/model/query/QueryWrapper.java.vm"));
    }

    @Override
    public void buildTemplateParamMap(CommandContext commandContext) {
        commandContext.setPackageMid("service");
        commandContext.setTemplatePath("ils/service");
    }
}
