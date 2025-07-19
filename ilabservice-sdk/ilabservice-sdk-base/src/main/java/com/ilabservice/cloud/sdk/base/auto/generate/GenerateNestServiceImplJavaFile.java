package com.ilabservice.cloud.sdk.base.auto.generate;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ilabservice.cloud.sdk.base.auto.manager.impl.AutoCodeManagerImpl;
import com.ilabservice.cloud.sdk.base.constants.CommandContext;
import com.ilabservice.cloud.sdk.base.enums.FlagEnum;
import com.ilabservice.cloud.sdk.base.model.TableObjectColumn;
import com.ilabservice.cloud.sdk.base.model.generate.SingleGenerateParma;
import com.ilabservice.cloud.sdk.base.model.generate.SubModuleParallel;
import com.ilabservice.cloud.sdk.base.template.AbstractGenerateFile;
import com.ilabservice.cloud.sdk.base.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月17日 09:44:52
 * @Description:
 */
@Slf4j
public class GenerateNestServiceImplJavaFile extends AbstractGenerateFile<Object> {


    /**
     * 模块参数
     */
    private SubModuleParallel subModuleParallel;

    public GenerateNestServiceImplJavaFile(SingleGenerateParma singleGenerateParma, SubModuleParallel subModuleParallel,
                                           String projectPath) {

        this.subModuleParallel = subModuleParallel;
        this.tableName = subModuleParallel.getTableName();
        this.moduleName = subModuleParallel.getModuleName();
        this.moduleKey = subModuleParallel.getModuleKey();
        this.projectPath = projectPath;
        this.author = singleGenerateParma.getAuth();
        this.packageName = singleGenerateParma.getParentPack();
        this.templatePath = "/ils/service";
    }


    @Override
    public void loadTemplate(CommandContext commandContext) {
        List<File> files = commandContext.getFiles();
        String path = AutoCodeManagerImpl.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        files.add(new File(path + "!/ils/service/client/service/ServiceImpl.java.vm"));

    }

    @Override
    public void buildTemplateParamMap(CommandContext commandContext) {
        Map<String, Object> map = commandContext.getTemplateParamMap();
        String tableName = StringUtils.firstUpperCase(StringUtils.underlineToHump(
                this.tableName.replaceFirst("t_", "")));
        map.put("masterTableName", StringUtils.firstUpperCase(tableName));
        map.put("masterTableName_a", StringUtils.firstLowerCase(tableName));
        commandContext.setPackageMid("service");
        commandContext.setTemplatePath("ils/service");

    }

    @Override
    public void buildTableInfo(CommandContext insContext) {
        super.buildTableInfo(insContext);

        if (ObjectUtil.isEmpty(subModuleParallel)){
            return;
        }
        List<TableObjectColumn> objectBeanColumnList = new ArrayList<>();
        List<TableObjectColumn> objectAttributeList = new ArrayList<>();
        List<TableObjectColumn> objectModuleParallelList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(subModuleParallel.getAttributeList())) {
            subModuleParallel.getAttributeList().forEach(i->{
                TableObjectColumn beanColumn = new TableObjectColumn();
                if (FlagEnum.NO.equals(i.getIsToMany())){
                    beanColumn.setAliasName_a(StringUtils.underlineToHump(i.getDependTable().replaceFirst("t_", "")));
                    beanColumn.setAliasName(StringUtils.firstUpperCase(beanColumn.getAliasName_a().replaceFirst("t_", "")));
                    objectAttributeList.add(beanColumn);
                }else {
                    beanColumn.setAliasName_a(StringUtils.underlineToHump(i.getDependTable().replaceFirst("t_", "")));
                    beanColumn.setAliasName(StringUtils.firstUpperCase(beanColumn.getAliasName_a().replaceFirst("t_", "")));
                    objectBeanColumnList.add(beanColumn);
                }
            });
        }
        if (CollectionUtil.isNotEmpty(subModuleParallel.getSubModuleList())) {
            objectModuleParallelList.addAll(subModuleParallel.getSubModuleList().stream().map(i->{
                TableObjectColumn beanColumn = new TableObjectColumn();
                beanColumn.setAliasName_a(i.getModuleKey());
                beanColumn.setAliasName(i.getModuleKeyA());
                return beanColumn;
            }).collect(Collectors.toList()));
        }
        insContext.getTemplateParamMap().put("objectBeanColumnList", objectBeanColumnList);
        insContext.getTemplateParamMap().put("objectAttributeList", objectAttributeList);
        insContext.getTemplateParamMap().put("objectModuleParallelList", objectModuleParallelList);
        commandContext.setTableName(StringUtils.firstUpperCase(moduleKey));
    }

}
