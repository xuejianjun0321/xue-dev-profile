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
 * @createTime: 2022年11月16日 15:22:07
 * @Description:
 */
@Slf4j
public class GenerateModelJavaFile extends AbstractGenerateFile<Object> {


    public GenerateModelJavaFile(ComplexGenerateParma complexGenerateParma, String projectPath ,
                                 FlagEnum primaryFlag, FlagEnum isGenerateExcelService) {
        this.complexGenerateParma = complexGenerateParma;
        this.tableName = complexGenerateParma.getTableName();
        this.moduleName = complexGenerateParma.getModuleName();
        this.moduleKey = complexGenerateParma.getModuleKey();
        this.primaryFlag = primaryFlag;
        this.isGenerateExcelService = isGenerateExcelService;
        this.projectPath = projectPath;
        this.packageName = complexGenerateParma.getParentPack();
        this.templatePath = "/ils/client";
        this.author = complexGenerateParma.getAuth();
    }

    @Override
    public void loadTemplate(CommandContext commandContext) {
        List<File> files = commandContext.getFiles();
        String path = AutoCodeManagerImpl.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        files.add(new File(path + "!/ils/client/vo/Vo.java.vm"));
        files.add(new File(path + "!/ils/client/dto/Dto.java.vm"));
        if (FlagEnum.YES.equals(isGenerateExcelService)) {
            files.add(new File(path + "!/ils/client/vo/ExcelVo.java.vm"));
        }
    }

    @Override
    public void buildTemplateParamMap(CommandContext commandContext) {
        commandContext.setPackageMid("client");
        commandContext.setTemplatePath("ils/client");
    }

}
