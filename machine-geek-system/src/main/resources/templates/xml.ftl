<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.machine.geek.mapper.ISystemAuthorityMapper">
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="cn.machine.geek.${}">
        <id column="id" property="id" />
        <result column="name" property="name" />
    </resultMap>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        `id`,`name`,`key`,`description`,`type`,`path`,`parent_id`,`sort`,`disable`,`version`,`create_time`,`update_time`
    </sql>
</mapper>