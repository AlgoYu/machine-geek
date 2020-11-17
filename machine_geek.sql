/*
 Navicat Premium Data Transfer

 Source Server         : Docker-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : machine_geek

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 17/11/2020 18:33:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_authority
-- ----------------------------
DROP TABLE IF EXISTS `system_authority`;
CREATE TABLE `system_authority` (
  `id` bigint NOT NULL COMMENT '唯一标识',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '关键字',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `sort` int DEFAULT NULL COMMENT '排序',
  `type` tinyint DEFAULT NULL COMMENT '权限类型：0是模块，1是菜单，3是接口。',
  `path` varchar(2083) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路径',
  `parent_id` bigint DEFAULT NULL COMMENT '父级权限ID',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `system_authority_id_uindex` (`id`) USING BTREE,
  UNIQUE KEY `system_authority_key_uindex` (`key`) USING BTREE,
  UNIQUE KEY `system_authority_name_uindex` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统权力';

-- ----------------------------
-- Records of system_authority
-- ----------------------------
BEGIN;
INSERT INTO `system_authority` VALUES (1, '系统设置', 'SYSTEM', '系统设置', 0, 0, '/System', 0, 0, '2020-10-26 17:47:10', '2020-10-26 17:47:12');
INSERT INTO `system_authority` VALUES (2, '数据中心', 'DATACENTER', '数据中心', 100, 0, '/DataCenter', 0, 0, '2020-10-26 17:49:22', '2020-10-26 17:49:23');
INSERT INTO `system_authority` VALUES (3, '开发者工具', 'DEVELOP', '开发者工具', 0, 0, '/DevTools', 0, 0, '2020-10-26 17:50:25', '2020-10-26 17:50:28');
INSERT INTO `system_authority` VALUES (4, '系统管理', 'MANAGEMENT', '系统管理', 0, 0, '/Management', 0, 0, '2020-10-26 17:51:35', '2020-10-26 17:51:37');
INSERT INTO `system_authority` VALUES (5, '代码生成器', 'DEVELOP:GENERATOR', '代码生成器', 0, 0, '/DevTools/CodeGenerator', 3, 0, '2020-10-26 17:53:49', '2020-10-26 17:53:52');
INSERT INTO `system_authority` VALUES (6, '数据监控', 'DEVELOP:DATAMONITOR', '数据库信息', 0, 0, '/DevTools/DataMonitor', 3, 0, '2020-10-26 17:54:47', '2020-10-26 17:54:49');
INSERT INTO `system_authority` VALUES (7, '异常信息', 'DEVELOP:SYSTEMEXCEPTION', '异常信息', 0, 0, '/DevTools/SystemException', 3, 0, '2020-10-26 17:55:44', '2020-10-26 17:55:47');
INSERT INTO `system_authority` VALUES (8, '删除异常信息', 'DEVELOP:SYSTEMEXCEPTION:DELETE', '删除异常信息', 0, 1, NULL, 7, 0, '2020-10-26 17:58:02', '2020-10-26 17:58:04');
INSERT INTO `system_authority` VALUES (9, '用户管理', 'MANAGEMENT:SYSTEMUSER', '用户管理', 0, 0, '/Management/SystemUser', 4, 0, '2020-10-29 13:13:58', '2020-10-29 13:14:06');
INSERT INTO `system_authority` VALUES (10, '角色管理', 'MANAGEMENT:SYSTEMROLE', '角色管理', 0, 0, '/Management/SystemRole', 4, 0, '2020-10-29 13:15:08', '2020-10-29 13:15:10');
INSERT INTO `system_authority` VALUES (11, '权限管理', 'MANAGEMENT:SYSTEMAUTHORITY', '权限管理', 0, 0, '/Management/SystemAuthority', 4, 0, '2020-10-29 13:16:05', '2020-10-29 13:16:07');
INSERT INTO `system_authority` VALUES (12, '增加用户', 'MANAGEMENT:SYSTEMUSER:ADD', '增加用户', 0, 1, '', 9, NULL, '2020-11-02 08:04:02', NULL);
INSERT INTO `system_authority` VALUES (13, '删除用户', 'MANAGEMENT:SYSTEMUSER:DELETE', '删除用户', 0, 1, '', 9, NULL, '2020-11-02 08:06:47', NULL);
INSERT INTO `system_authority` VALUES (14, '修改用户', 'MANAGEMENT:SYSTEMUSER:MODIFY', '修改用户', 0, 1, '', 9, NULL, '2020-11-02 08:06:54', NULL);
INSERT INTO `system_authority` VALUES (15, '查找用户', 'MANAGEMENT:SYSTEMUSER:GET', '查找用户', 0, 1, '', 9, 0, '2020-11-02 08:09:59', NULL);
INSERT INTO `system_authority` VALUES (16, '增加角色', 'MANAGEMENT:SYSTEMROLE:ADD', '增加角色', 0, 1, '', 10, 0, '2020-11-02 08:13:31', NULL);
INSERT INTO `system_authority` VALUES (17, '删除角色', 'MANAGEMENT:SYSTEMROLE:DELETE', '删除角色', 0, 1, '', 10, 0, '2020-11-02 08:13:37', NULL);
INSERT INTO `system_authority` VALUES (18, '修改角色', 'MANAGEMENT:SYSTEMROLE:MODIFY', '修改角色', 0, 1, '', 10, 0, '2020-11-02 08:13:47', NULL);
INSERT INTO `system_authority` VALUES (19, '查找角色', 'MANAGEMENT:SYSTEMROLE:GET', '查找角色', 0, 1, '', 10, 0, '2020-11-02 08:14:11', NULL);
INSERT INTO `system_authority` VALUES (20, '增加权限', 'MANAGEMENT:SYSTEMAUTHORITY:ADD', '增加权限', 0, 1, '', 11, 0, '2020-11-02 08:16:07', NULL);
INSERT INTO `system_authority` VALUES (21, '删除权限', 'MANAGEMENT:SYSTEMAUTHORITY:DELETE', '删除权限', 0, 1, '', 11, 0, '2020-11-02 08:16:19', NULL);
INSERT INTO `system_authority` VALUES (22, '修改权限', 'MANAGEMENT:SYSTEMAUTHORITY:MODIFY', '修改权限', 0, 1, '', 11, 0, '2020-11-02 08:16:27', NULL);
INSERT INTO `system_authority` VALUES (23, '查找权限', 'MANAGEMENT:SYSTEMAUTHORITY:GET', '查找权限', 0, 1, '', 11, 0, '2020-11-02 08:16:35', NULL);
INSERT INTO `system_authority` VALUES (24, '应用监控', 'DEVELOP:APPLICATIONMONITOR', '应用健康', 0, 0, '/DevTools/ApplicationMonitor', 3, 0, '2020-11-05 06:26:38', NULL);
INSERT INTO `system_authority` VALUES (25, '开发文档', 'DEVELOP:DOCUMENTATION', '开发文档', 0, 0, '/DevTools/Documentation', 3, 0, '2020-11-05 07:02:57', NULL);
INSERT INTO `system_authority` VALUES (26, '获取异常信息', 'DEVELOP:SYSTEMEXCEPTION:GET', '获取异常信息', 0, 1, NULL, 7, 0, '2020-11-06 12:12:45', NULL);
INSERT INTO `system_authority` VALUES (27, '获取数据库表', 'DEVELOP:GENERATOR:GET', '获取数据库表', 0, 1, NULL, 5, 0, '2020-11-06 17:08:38', NULL);
INSERT INTO `system_authority` VALUES (28, '生成代码', 'DEVELOP:GENERATOR:GENERATE', '生成代码', 0, 1, NULL, 5, 0, '2020-11-06 17:09:27', '2020-11-06 17:09:30');
INSERT INTO `system_authority` VALUES (29, '清空异常信息', 'DEVELOP:SYSTEMEXCEPTION:CLEAR', '清空异常信息', 0, 1, NULL, 7, 0, '2020-11-11 11:53:48', NULL);
INSERT INTO `system_authority` VALUES (30, '系统字典', 'SYSTEM:DICTIONARY', '系统字典', 0, 0, '/System/Dictionary', 1, 0, '2020-11-17 10:29:10', NULL);
COMMIT;

-- ----------------------------
-- Table structure for system_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `system_dictionary`;
CREATE TABLE `system_dictionary` (
  `key` varchar(255) NOT NULL,
  `value` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统字典';

-- ----------------------------
-- Records of system_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `system_dictionary` VALUES ('test', 'data');
COMMIT;

-- ----------------------------
-- Table structure for system_exception
-- ----------------------------
DROP TABLE IF EXISTS `system_exception`;
CREATE TABLE `system_exception` (
  `id` bigint NOT NULL COMMENT '唯一标识',
  `uri` varchar(255) DEFAULT NULL COMMENT 'URI',
  `method` varchar(8) DEFAULT NULL COMMENT '请求方法',
  `parameter` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(65) DEFAULT NULL COMMENT 'IP地址',
  `exception_class` varchar(100) DEFAULT NULL COMMENT '异常类',
  `exception_message` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统异常';

-- ----------------------------
-- Records of system_exception
-- ----------------------------
BEGIN;
INSERT INTO `system_exception` VALUES (1328602873662689282, '/systemUser/getLoginInfo', 'GET', '?', '0:0:0:0:0:0:0:1', 'org.springframework.security.access.AccessDeniedException', '不允许访问', '2020-11-17 07:36:24');
COMMIT;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` bigint NOT NULL COMMENT '唯一标识',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名称',
  `key` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '关键字',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `system_role_id_uindex` (`id`) USING BTREE,
  UNIQUE KEY `system_role_name_uindex` (`name`) USING BTREE,
  UNIQUE KEY `system_role_key_uindex` (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统角色';

-- ----------------------------
-- Records of system_role
-- ----------------------------
BEGIN;
INSERT INTO `system_role` VALUES (1, '超级管理员', 'ROLE_ADMINISTRATOR', '超级管理员', 0, '2020-10-20 17:45:21', '2020-11-17 02:30:18');
INSERT INTO `system_role` VALUES (1328272182123102210, '测试角色', NULL, '测试角色', NULL, '2020-11-15 17:42:21', '2020-11-17 07:35:09');
COMMIT;

-- ----------------------------
-- Table structure for system_role_authority_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_role_authority_relation`;
CREATE TABLE `system_role_authority_relation` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `authority_id` bigint NOT NULL COMMENT '权力ID',
  PRIMARY KEY (`role_id`,`authority_id`) USING BTREE,
  UNIQUE KEY `system_role_authority_relation_uindex` (`role_id`,`authority_id`) USING BTREE,
  KEY `system_role_authority_relation_authority_fk` (`authority_id`),
  CONSTRAINT `system_role_authority_relation_authority_fk` FOREIGN KEY (`authority_id`) REFERENCES `system_authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `system_role_authority_relation_role_fk` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色权力';

-- ----------------------------
-- Records of system_role_authority_relation
-- ----------------------------
BEGIN;
INSERT INTO `system_role_authority_relation` VALUES (1, 1);
INSERT INTO `system_role_authority_relation` VALUES (1328272182123102210, 1);
INSERT INTO `system_role_authority_relation` VALUES (1, 2);
INSERT INTO `system_role_authority_relation` VALUES (1328272182123102210, 2);
INSERT INTO `system_role_authority_relation` VALUES (1, 3);
INSERT INTO `system_role_authority_relation` VALUES (1, 4);
INSERT INTO `system_role_authority_relation` VALUES (1328272182123102210, 4);
INSERT INTO `system_role_authority_relation` VALUES (1, 5);
INSERT INTO `system_role_authority_relation` VALUES (1, 6);
INSERT INTO `system_role_authority_relation` VALUES (1, 7);
INSERT INTO `system_role_authority_relation` VALUES (1, 8);
INSERT INTO `system_role_authority_relation` VALUES (1, 9);
INSERT INTO `system_role_authority_relation` VALUES (1328272182123102210, 9);
INSERT INTO `system_role_authority_relation` VALUES (1, 10);
INSERT INTO `system_role_authority_relation` VALUES (1, 11);
INSERT INTO `system_role_authority_relation` VALUES (1, 12);
INSERT INTO `system_role_authority_relation` VALUES (1, 13);
INSERT INTO `system_role_authority_relation` VALUES (1, 14);
INSERT INTO `system_role_authority_relation` VALUES (1, 15);
INSERT INTO `system_role_authority_relation` VALUES (1, 16);
INSERT INTO `system_role_authority_relation` VALUES (1, 17);
INSERT INTO `system_role_authority_relation` VALUES (1, 18);
INSERT INTO `system_role_authority_relation` VALUES (1, 19);
INSERT INTO `system_role_authority_relation` VALUES (1, 20);
INSERT INTO `system_role_authority_relation` VALUES (1, 21);
INSERT INTO `system_role_authority_relation` VALUES (1, 22);
INSERT INTO `system_role_authority_relation` VALUES (1, 23);
INSERT INTO `system_role_authority_relation` VALUES (1, 24);
INSERT INTO `system_role_authority_relation` VALUES (1, 25);
INSERT INTO `system_role_authority_relation` VALUES (1, 26);
INSERT INTO `system_role_authority_relation` VALUES (1, 27);
INSERT INTO `system_role_authority_relation` VALUES (1328272182123102210, 27);
INSERT INTO `system_role_authority_relation` VALUES (1, 28);
INSERT INTO `system_role_authority_relation` VALUES (1, 29);
INSERT INTO `system_role_authority_relation` VALUES (1, 30);
COMMIT;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint NOT NULL COMMENT '标识',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名称',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `picture` varchar(2083) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话',
  `ip` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'IP地址',
  `disable` tinyint DEFAULT NULL COMMENT '禁用',
  `version` int DEFAULT NULL COMMENT '乐观锁',
  `last_login` datetime DEFAULT NULL COMMENT '上一次登录',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `system_user_id_uindex` (`id`) USING BTREE,
  UNIQUE KEY `system_user_username_uindex` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统用户';

-- ----------------------------
-- Records of system_user
-- ----------------------------
BEGIN;
INSERT INTO `system_user` VALUES (1, 'admin', '$2a$10$ygZQ5b508eevxCGV6NBNAuRVs3qkfSBgOqpa2PsyM11rkPloNTKkq', '/static/Administrator.jpg', '肖宇', '超级管理员', '794763733@qq.com', '18106666986', '127.0.0.1', 0, 0, '2020-10-24 09:36:43', '2020-10-24 09:36:47', '2020-11-16 08:25:31');
INSERT INTO `system_user` VALUES (1328602363387858945, 'test', '$2a$10$ygZQ5b508eevxCGV6NBNAuRVs3qkfSBgOqpa2PsyM11rkPloNTKkq', NULL, '测试用户', '测试用户', NULL, NULL, NULL, 0, NULL, NULL, '2020-11-17 07:34:22', NULL);
COMMIT;

-- ----------------------------
-- Table structure for system_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role_relation`;
CREATE TABLE `system_user_role_relation` (
  `user_id` bigint NOT NULL COMMENT '系统用户ID',
  `role_id` bigint NOT NULL COMMENT '系统角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  UNIQUE KEY `system_user_role_relation_uindex` (`user_id`,`role_id`) USING BTREE,
  KEY `system_user_role_relation_role_fk` (`role_id`),
  CONSTRAINT `system_user_role_relation_role_fk` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `system_user_role_relation_user_fk` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色关系';

-- ----------------------------
-- Records of system_user_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `system_user_role_relation` VALUES (1, 1);
INSERT INTO `system_user_role_relation` VALUES (1328602363387858945, 1328272182123102210);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
