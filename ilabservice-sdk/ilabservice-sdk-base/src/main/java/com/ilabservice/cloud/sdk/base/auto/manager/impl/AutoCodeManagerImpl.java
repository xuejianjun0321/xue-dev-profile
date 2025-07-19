package com.ilabservice.cloud.sdk.base.auto.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ilabservice.cloud.common.service.error.AssertUtils;
import com.ilabservice.cloud.sdk.base.auto.generate.GenerateApiImplJavaFile;
import com.ilabservice.cloud.sdk.base.auto.generate.GenerateApiJavaFile;
import com.ilabservice.cloud.sdk.base.auto.generate.GenerateDaoJavaFile;
import com.ilabservice.cloud.sdk.base.auto.generate.GenerateModelJavaFile;
import com.ilabservice.cloud.sdk.base.auto.generate.GenerateNestModelJavaFile;
import com.ilabservice.cloud.sdk.base.auto.generate.GenerateNestServiceImplJavaFile;
import com.ilabservice.cloud.sdk.base.auto.generate.GenerateNestServiceJavaFile;
import com.ilabservice.cloud.sdk.base.auto.generate.GenerateProjectFile;
import com.ilabservice.cloud.sdk.base.auto.generate.GenerateServiceImplJavaFile;
import com.ilabservice.cloud.sdk.base.auto.generate.GenerateServiceJavaFile;
import com.ilabservice.cloud.sdk.base.auto.manager.AutoCodeManager;
import com.ilabservice.cloud.sdk.base.command.impl.CommonEngineServiceImpl;
import com.ilabservice.cloud.sdk.base.enums.GeneralError;
import com.ilabservice.cloud.sdk.base.model.generate.ComplexGenerateParma;
import com.ilabservice.cloud.sdk.base.model.generate.Module;
import com.ilabservice.cloud.sdk.base.model.generate.NestGenerateParma;
import com.ilabservice.cloud.sdk.base.model.generate.SingleGenerateParma;
import com.ilabservice.cloud.sdk.base.model.generate.SubModule;
import com.ilabservice.cloud.sdk.base.model.generate.SubModuleParallel;
import com.ilabservice.cloud.sdk.base.util.JacksonUtil;
import com.ilabservice.cloud.sdk.base.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.ilabservice.cloud.sdk.base.enums.FlagEnum.NO;
import static com.ilabservice.cloud.sdk.base.enums.FlagEnum.YES;

/**
 * @Description 自动生成代码实现类
 * @ClassName AutoCodeManagerImpl
 * @Author xuejianjun
 * @date 2021.05.18 14:25
 */
@Slf4j
@Component
public class AutoCodeManagerImpl extends CommonEngineServiceImpl<Object> implements AutoCodeManager {

    @Override
    public void generateJavaCode(SingleGenerateParma singleGenerateParma) {
        log.info("AutoCodeManager 单表结构自动生成Java代码 开始！{}", JacksonUtil.toJsonString(singleGenerateParma));
        AssertUtils.isNotNull(singleGenerateParma, GeneralError.PARAM_ERROR, "参数错误");
        AssertUtils.isNotNull(singleGenerateParma.getTableName(), GeneralError.PARAM_ERROR, "表名不能为空");
        AssertUtils.isNotNull(singleGenerateParma.getParentPack(), GeneralError.PARAM_ERROR, "parentPack 不能为空");

        String tableName = singleGenerateParma.getTableName();
        String projectPathClient = singleGenerateParma.getProjectPathClient();
        String projectPathService = singleGenerateParma.getProjectPathService();
        String parentPack = singleGenerateParma.getParentPack();
        String auth = singleGenerateParma.getAuth();

        auth = StringUtils.isEmpty(auth) ? "auto" : auth;
        parentPack = StringUtils.isEmpty(parentPack) ? "com.ils.intelab.auto" : parentPack;
        projectPathClient = StringUtils.isEmpty(projectPathClient) ?
                System.getProperty("user.dir") + "/auto/lab-auto/lab-auto-client" : projectPathClient;
        projectPathService = StringUtils.isEmpty(projectPathService) ?
                System.getProperty("user.dir") + "/auto/lab-auto/lab-auto-service" : projectPathService;

        macroCommandExecutor.addCommand(new GenerateProjectFile(tableName, parentPack,
                "client", auth, projectPathClient, "/ils/client"
                ,singleGenerateParma.getProcessDefinitionKey()));
        macroCommandExecutor.addCommand(new GenerateProjectFile(tableName, parentPack,
                "service", auth, projectPathService, "/ils/service"
                ,singleGenerateParma.getProcessDefinitionKey()));
        macroCommandExecutor.syncExecute();

        log.info("AutoCodeManager 单表结构自动生成Java代码 完成！");
    }

    @Override
    public void generateJavaCode(ComplexGenerateParma complexGenerateParma) {
        log.info("AutoCodeManager 复杂表结构自动生成Java代码 开始！{}", JacksonUtil.toJsonString(complexGenerateParma));
        AssertUtils.isNotNull(complexGenerateParma, GeneralError.PARAM_ERROR, "参数错误");
        AssertUtils.isNotNull(complexGenerateParma.getModuleKey(), GeneralError.PARAM_ERROR, "ModuleKey不能为空");
        AssertUtils.isNotNull(complexGenerateParma.getModuleName(), GeneralError.PARAM_ERROR, "ModuleName不能为空");
        AssertUtils.isNotNull(complexGenerateParma.getTableName(), GeneralError.PARAM_ERROR, "表名不能为空");
        AssertUtils.isNotNull(complexGenerateParma.getParentPack(), GeneralError.PARAM_ERROR, "parentPack 不能为空");

        String projectPathClient = complexGenerateParma.getProjectPathClient();
        String projectPathService = complexGenerateParma.getProjectPathService();
        String moduleKey = complexGenerateParma.getModuleKey();
        String moduleName = complexGenerateParma.getModuleName();
        String masterTable = complexGenerateParma.getTableName();
        projectPathClient = StringUtils.isEmpty(projectPathClient) ?
                System.getProperty("user.dir") + "/auto/lab-auto/lab-auto-client" : projectPathClient;
        projectPathService = StringUtils.isEmpty(projectPathService) ?
                System.getProperty("user.dir") + "/auto/lab-auto/lab-auto-service" : projectPathService;
        // 添加命令
        addModuleCommand(complexGenerateParma, projectPathClient, projectPathService);
        addSingleCommand(complexGenerateParma, projectPathClient, projectPathService, moduleKey, moduleName, masterTable);
        macroCommandExecutor.syncExecute();

        log.info("AutoCodeManager 复杂表结构自动生成Java代码 完成！");
    }

    @Override
    public void generateJavaCode(NestGenerateParma nestGenerateParma) {
        log.info("AutoCodeManager 套娃模型关系自动生成Java代码 开始！{}", JacksonUtil.toJsonString(nestGenerateParma));
        AssertUtils.isNotNull(nestGenerateParma, GeneralError.PARAM_ERROR, "参数错误");
        AssertUtils.isNotNull(nestGenerateParma.getTableName(), GeneralError.PARAM_ERROR, "表名不能为空");
        AssertUtils.isNotNull(nestGenerateParma.getParentPack(), GeneralError.PARAM_ERROR, "parentPack 不能为空");
        AssertUtils.isNotNull(nestGenerateParma.getModuleKey(), GeneralError.PARAM_ERROR, "ModuleKey不能为空");
        AssertUtils.isNotNull(nestGenerateParma.getModuleName(), GeneralError.PARAM_ERROR, "ModuleName不能为空");

        String masterTable = nestGenerateParma.getTableName();
        String parentPack = nestGenerateParma.getParentPack();
        String auth = nestGenerateParma.getAuth();
        String projectPathClient = nestGenerateParma.getProjectPathClient();
        String projectPathService = nestGenerateParma.getProjectPathService();
        projectPathClient = StringUtils.isEmpty(projectPathClient) ?
                System.getProperty("user.dir") + "/auto/lab-auto/lab-auto-client" : projectPathClient;
        projectPathService = StringUtils.isEmpty(projectPathService) ?
                System.getProperty("user.dir") + "/auto/lab-auto/lab-auto-service" : projectPathService;

        // 遍历获取所有的单表
        Set<String> tableNameList = getAllTable(nestGenerateParma, masterTable);

        if (CollectionUtil.isNotEmpty(tableNameList)){
            String finalProjectPathClient1 = projectPathClient;
            String finalProjectPathService = projectPathService;
            tableNameList.forEach(tableName->{
                macroCommandExecutor.addCommand(new GenerateProjectFile(tableName, parentPack,
                        "client", auth, finalProjectPathClient1, "/ils/client"
                        ,nestGenerateParma.getProcessDefinitionKey()));
                macroCommandExecutor.addCommand(new GenerateProjectFile(tableName, parentPack,
                        "service", auth, finalProjectPathService, "/ils/service"
                        ,nestGenerateParma.getProcessDefinitionKey()));
            });
        }


        // 遍历获取所有的模块
        List<SubModuleParallel> subModuleList = getSubModuleParallels(nestGenerateParma);

        if (CollectionUtil.isNotEmpty(subModuleList)) {
            SingleGenerateParma singleGenerateParma = new SingleGenerateParma();
            singleGenerateParma.setAuth(nestGenerateParma.getAuth());
            singleGenerateParma.setProjectPathClient(nestGenerateParma.getProjectPathClient());
            singleGenerateParma.setProjectPathService(nestGenerateParma.getProjectPathService());
            singleGenerateParma.setParentPack(nestGenerateParma.getParentPack());

            String finalProjectPathClient = projectPathClient;
            String finalProjectPathService1 = projectPathService;
            subModuleList.forEach(module -> {
                macroCommandExecutor.addCommand(new GenerateNestModelJavaFile(singleGenerateParma,module, finalProjectPathClient));
                macroCommandExecutor.addCommand(new GenerateNestServiceJavaFile(singleGenerateParma,module, finalProjectPathClient));
                macroCommandExecutor.addCommand(new GenerateNestServiceImplJavaFile(singleGenerateParma,module, finalProjectPathService1));
            });

        }

        ComplexGenerateParma complexGenerateParma = new ComplexGenerateParma();
        complexGenerateParma.setModuleKey(nestGenerateParma.getModuleKey());
        complexGenerateParma.setModuleName(nestGenerateParma.getModuleName());
        complexGenerateParma.setAttributeList(nestGenerateParma.getAttributeList());
        complexGenerateParma.setTableName(nestGenerateParma.getTableName());
        complexGenerateParma.setParentPack(nestGenerateParma.getParentPack());
        complexGenerateParma.setAuth(nestGenerateParma.getAuth());
        complexGenerateParma.setProjectPathClient(nestGenerateParma.getProjectPathClient());
        complexGenerateParma.setProjectPathService(nestGenerateParma.getProjectPathService());

        macroCommandExecutor.addCommand(new GenerateApiJavaFile(complexGenerateParma, projectPathClient, YES, NO));
        macroCommandExecutor.addCommand(new GenerateApiImplJavaFile(complexGenerateParma, projectPathService, YES, NO));

        macroCommandExecutor.syncExecute();



    }

    @NotNull
    private List<SubModuleParallel> getSubModuleParallels(NestGenerateParma nestGenerateParma) {
        List<SubModuleParallel> subModuleList = new ArrayList<>();
        SubModuleParallel master = new SubModuleParallel();
        master.setTableName(nestGenerateParma.getTableName());
        master.setModuleKey(nestGenerateParma.getModuleKey());
        master.setModuleName(nestGenerateParma.getModuleName());
        master.setAttributeList(nestGenerateParma.getAttributeList());

        if (CollectionUtil.isNotEmpty(nestGenerateParma.getSubModuleList())) {
            List<Module> subModules = new ArrayList<>();
            nestGenerateParma.getSubModuleList().forEach(subModule -> {
                Module module = new Module();
                module.setTableName(subModule.getTableName());
                module.setModuleKey(subModule.getModuleKey());
                module.setModuleKeyA(module.getModuleKey_A());
                module.setModuleName(subModule.getModuleName());
                subModules.add(module);
                getModuleLst(subModuleList, subModule);
            });
            master.setSubModuleList(subModules);
        }
        subModuleList.add(master);
        return subModuleList;
    }

    /**
     * 获取所有表
     * @param nestGenerateParma 嵌套模型关系入参
     * @param masterTable 主表
     * @return 表 set
     */
    @NotNull
    private Set<String> getAllTable(NestGenerateParma nestGenerateParma, String masterTable) {
        Set<String> tableNameList = new HashSet<>();
        tableNameList.add(masterTable);
        if (CollectionUtil.isNotEmpty(nestGenerateParma.getAttributeList())) {
            nestGenerateParma.getAttributeList().forEach(attribute -> tableNameList.add(attribute.getDependTable()));
        }
        getTableNme(nestGenerateParma.getSubModuleList(), tableNameList);
        return tableNameList;
    }

    /**
     * 获取所有模块列表
     * @param subModuleList 子模块列表
     * @param subModule 子模块
     */
    private void getModuleLst(List<SubModuleParallel> subModuleList, SubModule subModule) {
        if (ObjectUtil.isNotEmpty(subModule)) {
            SubModuleParallel moduleParallel = new SubModuleParallel();
            moduleParallel.setTableName(subModule.getTableName());
            moduleParallel.setModuleKey(subModule.getModuleKey());
            moduleParallel.setModuleName(subModule.getModuleName());
            moduleParallel.setAttributeList(subModule.getAttributeList());
            if (CollectionUtil.isNotEmpty(subModule.getSubModuleList())) {
                List<Module> subModules = new ArrayList<>();
                subModule.getSubModuleList().forEach(module -> {
                    Module module1 = new Module();
                    module1.setTableName(module.getTableName());
                    module1.setModuleKey(module.getModuleKey());
                    module1.setModuleKeyA(module1.getModuleKey_A());
                    module1.setModuleName(module.getModuleName());
                    subModules.add(module1);
                    if (CollectionUtil.isNotEmpty(module.getSubModuleList())) {
                        getModuleLst(subModuleList, module);
                    }
                });
                moduleParallel.setSubModuleList(subModules);
            }
            subModuleList.add(moduleParallel);
        }
    }

    /**
     * 获取所有表名
     * @param subModuleList 子模块列表
     * @param tableNameList 表名列表
     */
    private void getTableNme(List<SubModule> subModuleList, Set<String> tableNameList) {
        if (CollectionUtil.isNotEmpty(subModuleList)) {
            subModuleList.forEach(subModule -> {
                tableNameList.add(subModule.getTableName());
                if (CollectionUtil.isNotEmpty(subModule.getAttributeList())) {
                    subModule.getAttributeList().forEach(attribute -> tableNameList.add(attribute.getDependTable()));
                }
                getTableNme(subModule.getSubModuleList(), tableNameList);
            });
        }
    }

    /**
     * 添加单表命令
     *
     * @param complexGenerateParma 复杂表关系生产Java类文件入参
     * @param projectPathClient    项目client路径
     * @param projectPathService   项目Service路径
     * @param moduleKey            模块key
     * @param moduleName           模块名称
     * @param masterTable          主表
     */
    private void addSingleCommand(ComplexGenerateParma complexGenerateParma, String projectPathClient, String projectPathService, String moduleKey, String moduleName, String masterTable) {


        if (CollectionUtils.isNotEmpty(complexGenerateParma.getAttributeList())) {
            ComplexGenerateParma subConfig = new ComplexGenerateParma(complexGenerateParma.getTableName());
            subConfig.setModuleKey(complexGenerateParma.getModuleKey());
            subConfig.setParentPack(complexGenerateParma.getParentPack());
            subConfig.setAuth(complexGenerateParma.getAuth());
            subConfig.setProjectPathClient(complexGenerateParma.getProjectPathClient());
            subConfig.setProjectPathService(complexGenerateParma.getProjectPathService());
            macroCommandExecutor.addCommand(new GenerateApiJavaFile(subConfig, projectPathClient, NO, NO));
            macroCommandExecutor.addCommand(new GenerateApiImplJavaFile(subConfig, projectPathService, NO, NO));
            macroCommandExecutor.addCommand(new GenerateServiceJavaFile(subConfig, projectPathClient, NO));
            macroCommandExecutor.addCommand(new GenerateServiceImplJavaFile(subConfig, projectPathService, NO));
            macroCommandExecutor.addCommand(new GenerateDaoJavaFile(complexGenerateParma.getTableName(), moduleKey, moduleName, projectPathService,
                    complexGenerateParma.getParentPack(), NO,complexGenerateParma.getAuth()));
            macroCommandExecutor.addCommand(new GenerateModelJavaFile(subConfig, projectPathClient, NO, NO));
            complexGenerateParma.getAttributeList().forEach(i -> {
                subConfig.setTableName(i.getDependTable());
                macroCommandExecutor.addCommand(
                        new GenerateModelJavaFile(subConfig, projectPathClient, NO, i.getIsGenerateExcelService()));
                macroCommandExecutor.addCommand(new GenerateApiJavaFile(subConfig, projectPathClient, NO, i.getIsGenerateExcelService()));
                macroCommandExecutor.addCommand(new GenerateApiImplJavaFile(subConfig, projectPathService, NO, i.getIsGenerateExcelService()));
                macroCommandExecutor.addCommand(new GenerateServiceJavaFile(subConfig, projectPathClient, NO));
                macroCommandExecutor.addCommand(new GenerateServiceImplJavaFile(subConfig, projectPathService, NO));
                macroCommandExecutor.addCommand(new GenerateDaoJavaFile(i.getDependTable(), moduleKey, moduleName, projectPathService,
                        complexGenerateParma.getParentPack(), NO,complexGenerateParma.getAuth()));
            });
        }
    }

    /**
     * 添加模块命令
     *
     * @param complexGenerateParma 复杂表关系生产Java类文件入参
     * @param projectPathClient    项目client路径
     * @param projectPathService   项目Service路径
     */
    private void addModuleCommand(ComplexGenerateParma complexGenerateParma, String projectPathClient, String projectPathService) {
        macroCommandExecutor.addCommand(new GenerateModelJavaFile(complexGenerateParma, projectPathClient, YES, NO));

        macroCommandExecutor.addCommand(new GenerateServiceImplJavaFile(complexGenerateParma, projectPathService, YES));

        macroCommandExecutor.addCommand(new GenerateServiceJavaFile(complexGenerateParma, projectPathClient, YES));

        macroCommandExecutor.addCommand(new GenerateApiJavaFile(complexGenerateParma, projectPathClient, YES, NO));

        macroCommandExecutor.addCommand(new GenerateApiImplJavaFile(complexGenerateParma, projectPathService, YES, NO));
    }


}
