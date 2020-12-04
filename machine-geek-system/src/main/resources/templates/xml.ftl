<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.machine.geek.mapper.I${className}Mapper">
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="cn.machine.geek.entity.${className}">
        <#list data as value>
        <#if value.columnKey == "PRI">
        <id column="${value.columnName}" property="${toHump(value.columnName)}" />
        <#else>
        <result column="${value.columnName}" property="${toHump(value.columnName)}" />
        </#if>
        </#list>
    </resultMap>
    <#assign columns><#list data as value>${"`" + value.columnName + "`,"}</#list></#assign>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        ${columns[0..columns?length-2]}
    </sql>
</mapper>