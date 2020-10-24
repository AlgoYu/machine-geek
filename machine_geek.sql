/*
 Navicat Premium Data Transfer

 Source Server         : Docker-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : machine_geek

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 24/10/2020 16:37:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_authority
-- ----------------------------
DROP TABLE IF EXISTS `system_authority`;
CREATE TABLE `system_authority` (
  `id` bigint NOT NULL COMMENT '唯一标识',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `key` varchar(30) DEFAULT NULL COMMENT '关键字',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `sort` int DEFAULT NULL COMMENT '排序',
  `type` tinyint DEFAULT NULL COMMENT '权限类型：0是模块，1是菜单，3是接口。',
  `path` varchar(2083) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路径',
  `parent_id` bigint DEFAULT NULL COMMENT '父级权限ID',
  `disable` tinyint DEFAULT NULL COMMENT '禁用',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `system_authority_id_uindex` (`id`),
  UNIQUE KEY `system_authority_key_uindex` (`key`),
  UNIQUE KEY `system_authority_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统权力';

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` bigint NOT NULL COMMENT '唯一标识',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `key` varchar(30) DEFAULT NULL COMMENT '关键字',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `disable` tinyint DEFAULT NULL COMMENT '禁用',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `system_role_id_uindex` (`id`),
  UNIQUE KEY `system_role_name_uindex` (`name`),
  UNIQUE KEY `system_role_key_uindex` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色';

-- ----------------------------
-- Table structure for system_role_authority_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_role_authority_relation`;
CREATE TABLE `system_role_authority_relation` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `authority_id` bigint NOT NULL COMMENT '权力ID',
  PRIMARY KEY (`role_id`,`authority_id`),
  UNIQUE KEY `system_role_authority_relation_authority_id_uindex` (`authority_id`),
  UNIQUE KEY `system_role_authority_relation_role_id_uindex` (`role_id`),
  CONSTRAINT `system_role_authority_relation_authority_fk` FOREIGN KEY (`authority_id`) REFERENCES `system_authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `system_role_authority_relation_role_fk` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权力';

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint NOT NULL COMMENT '标识',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `password` char(60) DEFAULT NULL COMMENT '密码',
  `picture` varchar(2083) DEFAULT NULL COMMENT '图片',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `ip` varchar(65) DEFAULT NULL COMMENT 'IP地址',
  `disable` tinyint DEFAULT NULL COMMENT '禁用',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  `last_login` datetime DEFAULT NULL COMMENT '上一次登录',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `system_user_id_uindex` (`id`),
  UNIQUE KEY `system_user_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户';

-- ----------------------------
-- Table structure for system_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role_relation`;
CREATE TABLE `system_user_role_relation` (
  `user_id` bigint NOT NULL COMMENT '系统用户ID',
  `role_id` bigint NOT NULL COMMENT '系统角色ID',
  PRIMARY KEY (`user_id`,`role_id`),
  UNIQUE KEY `system_user_role_relation_role_id_uindex` (`role_id`),
  UNIQUE KEY `system_user_role_relation_user_id_uindex` (`user_id`),
  CONSTRAINT `system_user_role_relation_role_fk` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `system_user_role_relation_user_fk` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关系';

SET FOREIGN_KEY_CHECKS = 1;
