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

 Date: 26/10/2020 18:12:09
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
  `key` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关键字',
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
INSERT INTO `system_authority` VALUES (1, '系统设置', 'SYSTEM', '系统设置', 0, 0, NULL, NULL, 0, 0, '2020-10-26 17:47:10', '2020-10-26 17:47:12');
INSERT INTO `system_authority` VALUES (2, '代码生成器', 'GENERATOR', '代码生成器', 0, 0, NULL, NULL, 0, 0, '2020-10-26 17:49:22', '2020-10-26 17:49:23');
INSERT INTO `system_authority` VALUES (3, '开发者工具', 'DEVELOP', '开发者工具', 0, 0, NULL, NULL, 0, 0, '2020-10-26 17:50:25', '2020-10-26 17:50:28');
INSERT INTO `system_authority` VALUES (4, '系统管理', 'MANAGEMENT', '系统管理', 0, 0, NULL, NULL, 0, 0, '2020-10-26 17:51:35', '2020-10-26 17:51:37');
INSERT INTO `system_authority` VALUES (5, '服务信息', 'DEVELOP:SERVER', '服务信息', 0, 1, NULL, 3, 0, 0, '2020-10-26 17:53:49', '2020-10-26 17:53:52');
INSERT INTO `system_authority` VALUES (6, '数据库信息', 'DEVELOP:DATABASE', '数据库信息', 0, 1, NULL, 3, 0, 0, '2020-10-26 17:54:47', '2020-10-26 17:54:49');
INSERT INTO `system_authority` VALUES (7, '异常信息', 'DEVELOP:EXCEPTION', '异常信息', 0, 1, NULL, 3, 0, 0, '2020-10-26 17:55:44', '2020-10-26 17:55:47');
INSERT INTO `system_authority` VALUES (8, '删除异常信息', 'DEVELOP:EXCEPTION:DELETE', '删除异常信息', 0, 2, NULL, 7, 0, 0, '2020-10-26 17:58:02', '2020-10-26 17:58:04');

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
INSERT INTO `system_user` VALUES (1, 'admin', '$2a$10$SkUoVkbWjlPgzIKBSgvLvefWCdGmw00MZDLyADYfj9aKA8Ud1pvC6', '/static/picture.png', '超级管理员', '超级管理员', '794763733@qq.com', '18106666986', '127.0.0.1', 0, 0, '2020-10-26 17:36:43', '2020-10-26 17:36:47', '2020-10-26 17:36:50');

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
