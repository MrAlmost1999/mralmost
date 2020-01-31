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

 Date: 31/01/2020 21:09:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`            bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '主键',
    `parent_id`     bigint(20)                                               NOT NULL COMMENT '父类id',
    `type`          int(11)                                                  NOT NULL COMMENT '父类类型',
    `commentator`   int(11)                                                  NOT NULL COMMENT '评论人id',
    `gmt_create`    bigint(20)                                               NOT NULL COMMENT '创建时间',
    `gmt_modified`  bigint(20)                                               NOT NULL COMMENT '修改时间',
    `like_count`    bigint(20)                                               NOT NULL COMMENT '点赞数',
    `comment_count` bigint(20)                                               NOT NULL COMMENT '评论数',
    `content`       varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
