/*
 Navicat Premium Data Transfer

 Source Server         : develop
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : mralmost

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 02/03/2020 19:27:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题标题',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题描述',
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `modified_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `creator` bigint(11) NOT NULL COMMENT '发布人id',
  `comment_count` int(11) NULL DEFAULT 0 COMMENT '评论数',
  `view_count` int(11) NULL DEFAULT 0 COMMENT '查阅数',
  `like_count` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `tag` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签',
  `login_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录类型(github,注册...)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `question`(`creator`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
