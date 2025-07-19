package com.ilabservice.cloud.sdk.base.auto.generate;

import com.ilabservice.cloud.sdk.base.auto.manager.impl.AutoCodeManagerImpl;
import com.ilabservice.cloud.sdk.base.constants.CommandContext;
import com.ilabservice.cloud.sdk.base.enums.FlagEnum;
import com.ilabservice.cloud.sdk.base.template.AbstractGenerateFile;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月17日 11:18:26
 * @Description:
 */
@Slf4j
public class GenerateDaoJavaFile extends AbstractGenerateFile<Object> {

    public GenerateDaoJavaFile(String tableName, String moduleKey, String moduleName,String projectPath ,
                               String parentPack, FlagEnum primaryFlag,String author) {
        this.tableName = tableName;
        this.moduleName = moduleName;
        this.moduleKey = moduleKey;
        this.primaryFlag = primaryFlag;
        this.projectPath = projectPath;
        this.packageName = parentPack;
        this.templatePath = "/ils/service";
        this.author = author;
    }


    @Override
    public void loadTemplate(CommandContext commandContext) {
        List<File> files = commandContext.getFiles();
        String path = AutoCodeManagerImpl.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        files.add(new File(path + "!/ils/service/convertor/Convertor.java.vm"));
        files.add(new File(path + "!/ils/service/mapper/Dao.xml.vm"));
        files.add(new File(path + "!/ils/service/model/dao/Dao.java.vm"));
        files.add(new File(path + "!/ils/service/model/domain/Domain.java.vm"));
        files.add(new File(path + "!/ils/service/model/domain/impl/DomainImpl.java.vm"));
        files.add(new File(path + "!/ils/service/model/entity/.java.vm"));
        files.add(new File(path + "!/ils/service/model/query/QueryWrapper.java.vm"));
    }

    @Override
    public void buildTemplateParamMap(CommandContext commandContext) {
        commandContext.setPackageMid("service");
        commandContext.setTemplatePath("ils/service");
    }
}
