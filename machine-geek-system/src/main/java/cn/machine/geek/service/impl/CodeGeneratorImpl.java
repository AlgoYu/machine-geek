package cn.machine.geek.service.impl;

import cn.machine.geek.custom.FreeMarkerHump;
import cn.machine.geek.entity.DatabaseTableColumn;
import cn.machine.geek.mapper.IDatabaseMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

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
public class CodeGeneratorImpl {
    private Configuration configuration;
    private IDatabaseMapper databaseMapper;

    @Autowired
    public CodeGeneratorImpl(Configuration configuration, IDatabaseMapper databaseMapper) {
        this.configuration = configuration;
        this.databaseMapper = databaseMapper;
        // 加入驼峰函数
        this.configuration.setSharedVariable("toHump",new FreeMarkerHump());
    }

    @SneakyThrows
    public void generate(String tableName, String moduleName){
        Template entityTemplate = configuration.getTemplate("entity.ftl");
        Template mapperTemplate = configuration.getTemplate("mapper.ftl");
        Map<String,Object> map = new HashMap<>();
        List<DatabaseTableColumn> databaseTableColumns = databaseMapper.selectColumnByTableName(tableName);
        map.put("data",databaseTableColumns);
        map.put("tableName",tableName);
        map.put("moduleName",moduleName);
        map.put("date", LocalDate.now());
        String entityStr = FreeMarkerTemplateUtils.processTemplateIntoString(entityTemplate, map);
        //String mapperStr = FreeMarkerTemplateUtils.processTemplateIntoString(mapperTemplate, map);
        System.out.println(entityStr);
    }
}