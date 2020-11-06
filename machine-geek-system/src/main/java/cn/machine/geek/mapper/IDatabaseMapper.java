package cn.machine.geek.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 数据库映射表
 * @Date: 2020/11/6
 */
@Mapper
public interface IDatabaseMapper{
    List<String> selectAllDatabaseName();
    List<String> selectAllTableByDatabaseName(@Param(value = "databaseName")String databaseName);
}
