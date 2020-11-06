package cn.machine.geek.mapper;

import cn.machine.geek.entity.DatabaseTable;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    IPage<DatabaseTable> selectTableByCondition(IPage<DatabaseTable> page,@Param(value = "databaseName")String databaseName,@Param(value = "keyWord") String keyWord);
}
