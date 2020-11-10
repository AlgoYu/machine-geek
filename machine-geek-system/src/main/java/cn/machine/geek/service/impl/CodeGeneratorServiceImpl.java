package cn.machine.geek.service.impl;

import cn.machine.geek.custom.FreeMarkerHump;
import cn.machine.geek.entity.DatabaseTableColumn;
import cn.machine.geek.mapper.IDatabaseMapper;
import cn.machine.geek.service.ICodeGeneratorService;
import cn.machine.geek.utils.RandomUtil;
import com.google.common.base.CaseFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: 代码生成器实现类
 * @Date: 2020/11/05
 */
@Service
public class CodeGeneratorServiceImpl implements ICodeGeneratorService {
    private Configuration configuration;
    private IDatabaseMapper databaseMapper;
    @Value("${generatePath}")
    private String generatePath;

    @Autowired
    public CodeGeneratorServiceImpl(Configuration configuration, IDatabaseMapper databaseMapper) {
        this.configuration = configuration;
        this.databaseMapper = databaseMapper;
        // 加入驼峰函数
        this.configuration.setSharedVariable("toHump",new FreeMarkerHump());
    }

    /**
    * @Author: MachineGeek
    * @Description: 生成代码
    * @Date: 12:58 下午
     * @param tableName
     * @param moduleName
    * @Return: java.lang.String
    */
    @SneakyThrows
    public String generate(String tableName, String moduleName){
        // 获取模板
        Template entityTemplate = configuration.getTemplate("entity.ftl");
        Template xmlTemplate = configuration.getTemplate("xml.ftl");
        Template mapperTemplate = configuration.getTemplate("mapper.ftl");
        Template serviceTemplate = configuration.getTemplate("service.ftl");
        Template serviceImplTemplate = configuration.getTemplate("serviceimpl.ftl");
        Template controllerTemplate = configuration.getTemplate("controller.ftl");
        // 获取表字段
        Map<String,Object> map = new HashMap<>();
        List<DatabaseTableColumn> databaseTableColumns = databaseMapper.selectColumnByTableName(tableName);
        String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,tableName);
        String instanceName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,tableName);
        // 设置数据
        map.put("data",databaseTableColumns);
        map.put("tableName",tableName);
        map.put("className", className);
        map.put("instanceName", instanceName);
        map.put("moduleName",moduleName);
        map.put("date", LocalDate.now());
        // 创建目录
        String directory = this.generatePath + RandomUtil.generateRandomString(5);
        File file = new File(directory);
        file.mkdirs();
        // 生成代码
        entityTemplate.process(map,new BufferedWriter(new FileWriter(directory + "/" + className + ".java")));
        xmlTemplate.process(map,new BufferedWriter(new FileWriter(directory + "/" + className + "Mapper.xml")));
        mapperTemplate.process(map,new BufferedWriter(new FileWriter(directory + "/I" + className + "Mapper.java")));
        serviceTemplate.process(map,new BufferedWriter(new FileWriter(directory + "/I" + className + "Service.java")));
        serviceImplTemplate.process(map,new BufferedWriter(new FileWriter(directory + "/" + className + "ServiceImpl.java")));
        controllerTemplate.process(map,new BufferedWriter(new FileWriter(directory + "/" + className + "Controller.java")));
        return directory;
    }
}