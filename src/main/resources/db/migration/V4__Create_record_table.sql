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

 Date: 31/01/2020 21:08:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_id` bigint(11) NOT NULL,
  `question_id` bigint(11) NOT NULL,
  `record_date` date NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_FK`(`user_id`) USING BTREE,
  INDEX `question_id_FK`(`question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (35, 1, 1, '2020-01-31');
INSERT INTO `record` VALUES (36, 1, 2, '2020-01-31');
INSERT INTO `record` VALUES (37, 1, 4, '2020-01-31');
INSERT INTO `record` VALUES (38, 1, 5, '2020-01-30');
INSERT INTO `record` VALUES (39, 1, 6, '2020-01-30');
INSERT INTO `record` VALUES (40, 1, 59, '2020-01-31');
INSERT INTO `record` VALUES (41, 1, 55, '2020-01-31');

SET FOREIGN_KEY_CHECKS = 1;
