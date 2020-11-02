/*
 Navicat Premium Data Transfer

 Source Server         : Docker-Mysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : machine_geek

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 02/11/2020 16:21:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_authority
-- ----------------------------
DROP TABLE IF EXISTS `system_authority`;
CREATE TABLE `system_authority`  (
  `id` bigint(0) NOT NULL COMMENT '唯一标识',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关键字',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '权限类型：0是模块，1是菜单，3是接口。',
  `path` varchar(2083) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父级权限ID',
  `disable` tinyint(0) NULL DEFAULT NULL COMMENT '禁用',
  `version` int(0) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `system_authority_id_uindex`(`id`) USING BTREE,
  UNIQUE INDEX `system_authority_key_uindex`(`key`) USING BTREE,
  UNIQUE INDEX `system_authority_name_uindex`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统权力' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_authority
-- ----------------------------
INSERT INTO `system_authority` VALUES (1, '系统设置', 'SYSTEM', '系统设置', 0, 0, '/Setting', 0, 0, 0, '2020-10-26 17:47:10', '2020-10-26 17:47:12');
INSERT INTO `system_authority` VALUES (2, '数据中心', 'DATACENTER', '数据中心', 100, 0, '/DataCenter', 0, 0, 0, '2020-10-26 17:49:22', '2020-10-26 17:49:23');
INSERT INTO `system_authority` VALUES (3, '开发者工具', 'DEVELOP', '开发者工具', 0, 0, '/DevTools', 0, 0, 0, '2020-10-26 17:50:25', '2020-10-26 17:50:28');
INSERT INTO `system_authority` VALUES (4, '系统管理', 'MANAGEMENT', '系统管理', 0, 0, '/Management', 0, 0, 0, '2020-10-26 17:51:35', '2020-10-26 17:51:37');
INSERT INTO `system_authority` VALUES (5, '代码生成器', 'DEVELOP:GENERATOR', '代码生成器', 0, 0, '/DevTools/CodeGenerator', 3, 0, 0, '2020-10-26 17:53:49', '2020-10-26 17:53:52');
INSERT INTO `system_authority` VALUES (6, '数据库信息', 'DEVELOP:DATABASE', '数据库信息', 0, 0, '/DevTools/DataInfo', 3, 0, 0, '2020-10-26 17:54:47', '2020-10-26 17:54:49');
INSERT INTO `system_authority` VALUES (7, '异常信息', 'DEVELOP:EXCEPTION', '异常信息', 0, 0, '/DevTools/ExceptionInfo', 3, 0, 0, '2020-10-26 17:55:44', '2020-10-26 17:55:47');
INSERT INTO `system_authority` VALUES (8, '删除异常信息', 'DEVELOP:EXCEPTION:DELETE', '删除异常信息', 0, 1, NULL, 7, 0, 0, '2020-10-26 17:58:02', '2020-10-26 17:58:04');
INSERT INTO `system_authority` VALUES (9, '用户管理', 'MANAGEMENT:SYSTEMUSER', '用户管理', 0, 0, '/Management/SystemUser', 4, 0, 0, '2020-10-29 13:13:58', '2020-10-29 13:14:06');
INSERT INTO `system_authority` VALUES (10, '角色管理', 'MANAGEMENT:SYSTEMROLE', '角色管理', 0, 0, '/Management/SystemRole', 4, 0, 0, '2020-10-29 13:15:08', '2020-10-29 13:15:10');
INSERT INTO `system_authority` VALUES (11, '权限管理', 'MANAGEMENT:SYSTEMAUTHORITY', '权限管理', 0, 0, '/Management/SystemAuthority', 4, 0, 0, '2020-10-29 13:16:05', '2020-10-29 13:16:07');
INSERT INTO `system_authority` VALUES (12, '增加用户', 'MANAGEMENT:SYSTEMUSER:ADD', '增加用户', 0, 1, '', 9, 0, NULL, '2020-11-02 08:04:02', NULL);
INSERT INTO `system_authority` VALUES (13, '删除用户', 'MANAGEMENT:SYSTEMUSER:DELETE', '删除用户', 0, 1, '', 9, 0, NULL, '2020-11-02 08:06:47', NULL);
INSERT INTO `system_authority` VALUES (14, '修改用户', 'MANAGEMENT:SYSTEMUSER:MODIFY', '修改用户', 0, 1, '', 9, 0, NULL, '2020-11-02 08:06:54', NULL);
INSERT INTO `system_authority` VALUES (15, '查找用户', 'MANAGEMENT:SYSTEMUSER:GET', '查找用户', 0, 1, '', 9, 0, 0, '2020-11-02 08:09:59', NULL);
INSERT INTO `system_authority` VALUES (16, '增加角色', 'MANAGEMENT:SYSTEMROLE:ADD', '增加角色', 0, 1, '', 10, 0, 0, '2020-11-02 08:13:31', NULL);
INSERT INTO `system_authority` VALUES (17, '删除角色', 'MANAGEMENT:SYSTEMROLE:DELETE', '删除角色', 0, 1, '', 10, 0, 0, '2020-11-02 08:13:37', NULL);
INSERT INTO `system_authority` VALUES (18, '修改角色', 'MANAGEMENT:SYSTEMROLE:MODIFY', '修改角色', 0, 1, '', 10, 0, 0, '2020-11-02 08:13:47', NULL);
INSERT INTO `system_authority` VALUES (19, '查找角色', 'MANAGEMENT:SYSTEMROLE:GET', '查找角色', 0, 1, '', 10, 0, 0, '2020-11-02 08:14:11', NULL);
INSERT INTO `system_authority` VALUES (20, '增加权限', 'MANAGEMENT:SYSTEMAUTHORITY:ADD', '增加权限', 0, 1, '', 11, 0, 0, '2020-11-02 08:16:07', NULL);
INSERT INTO `system_authority` VALUES (21, '删除权限', 'MANAGEMENT:SYSTEMAUTHORITY:DELETE', '删除权限', 0, 1, '', 11, 0, 0, '2020-11-02 08:16:19', NULL);
INSERT INTO `system_authority` VALUES (22, '修改权限', 'MANAGEMENT:SYSTEMAUTHORITY:MODIFY', '修改权限', 0, 1, '', 11, 0, 0, '2020-11-02 08:16:27', NULL);
INSERT INTO `system_authority` VALUES (23, '查找权限', 'MANAGEMENT:SYSTEMAUTHORITY:GET', '查找权限', 0, 1, '', 11, 0, 0, '2020-11-02 08:16:35', NULL);

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` bigint(0) NOT NULL COMMENT '唯一标识',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `key` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关键字',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `disable` tinyint(0) NULL DEFAULT NULL COMMENT '禁用',
  `version` int(0) NULL DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `system_role_id_uindex`(`id`) USING BTREE,
  UNIQUE INDEX `system_role_name_uindex`(`name`) USING BTREE,
  UNIQUE INDEX `system_role_key_uindex`(`key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1, '超级管理员', 'ROLE_ADMINISTRATOR', '超级管理员', 0, 0, '2020-10-26 17:45:21', '2020-10-26 17:45:24');

-- ----------------------------
-- Table structure for system_role_authority_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_role_authority_relation`;
CREATE TABLE `system_role_authority_relation`  (
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `authority_id` bigint(0) NOT NULL COMMENT '权力ID',
  PRIMARY KEY (`role_id`, `authority_id`) USING BTREE,
  UNIQUE INDEX `system_role_authority_relation_uindex`(`role_id`, `authority_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权力' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role_authority_relation
-- ----------------------------
INSERT INTO `system_role_authority_relation` VALUES (1, 1);
INSERT INTO `system_role_authority_relation` VALUES (1, 2);
INSERT INTO `system_role_authority_relation` VALUES (1, 3);
INSERT INTO `system_role_authority_relation` VALUES (1, 4);
INSERT INTO `system_role_authority_relation` VALUES (1, 5);
INSERT INTO `system_role_authority_relation` VALUES (1, 6);
INSERT INTO `system_role_authority_relation` VALUES (1, 7);
INSERT INTO `system_role_authority_relation` VALUES (1, 8);
INSERT INTO `system_role_authority_relation` VALUES (1, 9);
INSERT INTO `system_role_authority_relation` VALUES (1, 10);
INSERT INTO `system_role_authority_relation` VALUES (1, 11);

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `id` bigint(0) NOT NULL COMMENT '标识',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `picture` varchar(2083) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `ip` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `disable` tinyint(0) NULL DEFAULT NULL COMMENT '禁用',
  `version` int(0) NULL DEFAULT NULL COMMENT '乐观锁',
  `last_login` datetime(0) NULL DEFAULT NULL COMMENT '上一次登录',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `system_user_id_uindex`(`id`) USING BTREE,
  UNIQUE INDEX `system_user_username_uindex`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES (1, 'admin', '$2a$10$ygZQ5b508eevxCGV6NBNAuRVs3qkfSBgOqpa2PsyM11rkPloNTKkq', '/static/Administrator.jpg', '超级管理员', '超级管理员', '794763733@qq.com', '18106666986', '127.0.0.1', 0, 0, '2020-10-26 17:36:43', '2020-10-26 17:36:47', '2020-10-26 17:36:50');

-- ----------------------------
-- Table structure for system_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role_relation`;
CREATE TABLE `system_user_role_relation`  (
  `user_id` bigint(0) NOT NULL COMMENT '系统用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '系统角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  UNIQUE INDEX `system_user_role_relation_uindex`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user_role_relation
-- ----------------------------
INSERT INTO `system_user_role_relation` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
