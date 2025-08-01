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
 * @createTime: 2022年11月18日 10:05:16
 * @Description:
 */
@Slf4j
public class GenerateApiImplJavaFile extends AbstractGenerateFile<Object> {


    public GenerateApiImplJavaFile(ComplexGenerateParma complexGenerateParma,String projectPath ,
                                   FlagEnum primaryFlag, FlagEnum isGenerateExcelService) {
        this.complexGenerateParma = complexGenerateParma;
        this.tableName = complexGenerateParma.getTableName();
        this.moduleName = complexGenerateParma.getModuleName();
        this.moduleKey = complexGenerateParma.getModuleKey();
        this.primaryFlag = primaryFlag;
        this.isGenerateExcelService = isGenerateExcelService;
        this.projectPath = projectPath;
        this.templatePath = "/ils/service";
        this.author = complexGenerateParma.getAuth();
        this.packageName = complexGenerateParma.getParentPack();
    }

    @Override
    public void loadTemplate(CommandContext commandContext) {
        List<File> files = commandContext.getFiles();
        String path = AutoCodeManagerImpl.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        files.add(new File(path + "!/ils/service/client/api/ApiImpl.java.vm"));
        if (FlagEnum.YES.equals(isGenerateExcelService)) {
            files.add(new File(path + "!/ils/service/client/api/ExcelApiImpl.java.vm"));
        }
    }


    @Override
    public void buildTemplateParamMap(CommandContext commandContext) {
        Map<String, Object> map = commandContext.getTemplateParamMap();
        String tableName = StringUtils.firstUpperCase(StringUtils.underlineToHump(
                this.tableName.replaceFirst("t_", "")));
        map.put("tableName", this.tableName);
        map.put("moduleName", moduleName);
        map.put("masterTableName", StringUtils.firstUpperCase(tableName));
        map.put("masterTableName_a", StringUtils.firstLowerCase(tableName));
        commandContext.setPackageMid("service");
        commandContext.setTemplatePath("ils/service");


    }

}
