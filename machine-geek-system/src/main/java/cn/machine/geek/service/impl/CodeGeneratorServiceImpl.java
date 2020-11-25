package cn.machine.geek.service.impl;

import cn.machine.geek.freemarker.FreeMarkerHump;
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
        Template apiTemplate = configuration.getTemplate("api.ftl");
        Template vueTemplate = configuration.getTemplate("vue.ftl");
        // 获取数据
        Map<String,Object> map = new HashMap<>();
        String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,tableName);
        String instanceName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,tableName);
        List<DatabaseTableColumn> databaseTableColumns =getColumnsByTableName(tableName);
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
        // 创建目录下的子文件
        File entityFile = new File(directory,className + ".java");
        File xmlFile = new File(directory,className + "Mapper.xml");
        File mapperFile = new File(directory,"I" + className + "Mapper.java");
        File serviceFile = new File(directory,"/I" + className + "Service.java");
        File serviceImplFile = new File(directory,className + "ServiceImpl.java");
        File controllerFile = new File(directory,className + "Controller.java");
        File apiFile = new File(directory,className+"Api.js");
        File vueFile = new File(directory,className+".vue");
        // 创建新文件
        entityFile.createNewFile();
        xmlFile.createNewFile();
        mapperFile.createNewFile();
        serviceFile.createNewFile();
        serviceImplFile.createNewFile();
        controllerFile.createNewFile();
        apiFile.createNewFile();
        vueFile.createNewFile();
        // 生成代码
        entityTemplate.process(map,new BufferedWriter(new FileWriter(entityFile)));
        xmlTemplate.process(map,new BufferedWriter(new FileWriter(xmlFile)));
        mapperTemplate.process(map,new BufferedWriter(new FileWriter(mapperFile)));
        serviceTemplate.process(map,new BufferedWriter(new FileWriter(serviceFile)));
        serviceImplTemplate.process(map,new BufferedWriter(new FileWriter(serviceImplFile)));
        controllerTemplate.process(map,new BufferedWriter(new FileWriter(controllerFile)));
        apiTemplate.process(map,new BufferedWriter(new FileWriter(apiFile)));
        vueTemplate.process(map,new BufferedWriter(new FileWriter(vueFile)));
        return directory;
    }

    /**
    * @Author: MachineGeek
    * @Description: 根据表名获取表字段
    * @Date: 1:26 下午
     * @param tableName
    * @Return: java.util.List<cn.machine.geek.entity.DatabaseTableColumn>
    */
    private List<DatabaseTableColumn> getColumnsByTableName(String tableName){
        return databaseMapper.listColumnByTableName(tableName);
    }
}