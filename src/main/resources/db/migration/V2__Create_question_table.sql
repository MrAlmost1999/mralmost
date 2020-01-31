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

 Date: 31/01/2020 21:07:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `gmt_create` bigint(20) NULL DEFAULT NULL,
  `gmt_modified` bigint(20) NULL DEFAULT NULL,
  `creator` bigint(11) NULL DEFAULT NULL,
  `comment_count` int(11) NULL DEFAULT 0,
  `view_count` int(11) NULL DEFAULT 0,
  `like_count` int(11) NULL DEFAULT 0,
  `tag` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `question`(`creator`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, '修改bug', '修改登录bug', 1579094714332, 1579696851533, 1, 0, 19, 0, 'bug1');
INSERT INTO `question` VALUES (2, '不想使用mybatis generator', '改起来太麻烦', 1579094724976, 1579673420973, 1, 0, 9, 0, '头痛');
INSERT INTO `question` VALUES (3, '烦到不想说话', '......', 1579094724976, 1579749734836, 1, 0, 8, 0, '123123');
INSERT INTO `question` VALUES (4, '年少有为', '年少有为', 1579094724976, 1580385924954, 1, 0, 1, 0, '123123');
INSERT INTO `question` VALUES (5, '5', '123123', 1579094724976, NULL, 1, 0, 1, 0, '123123');
INSERT INTO `question` VALUES (6, '6', '123123', 1579094724976, NULL, 1, 0, 1, 0, '123123');
INSERT INTO `question` VALUES (7, '7', '123123', 1579094724976, NULL, 1, 0, 0, 0, '123123');
INSERT INTO `question` VALUES (8, '8', '123123', 1579094724976, NULL, 1, 0, 0, 0, '123123');
INSERT INTO `question` VALUES (9, '9', '123123', 1579094724976, NULL, 1, 0, 0, 0, '123123');
INSERT INTO `question` VALUES (10, '10', '123123', 1579094724976, NULL, 1, 0, 0, 0, '123123');
INSERT INTO `question` VALUES (11, '11', '123123', 1579094724976, NULL, 1, 0, 0, 0, '123123');
INSERT INTO `question` VALUES (12, '12', '123123', 1579094724976, NULL, 1, 0, 0, 0, '123123');
INSERT INTO `question` VALUES (13, '13', '123123', 1579094724976, NULL, 1, 0, 0, 0, '123123');
INSERT INTO `question` VALUES (14, '14', '123123', 1579094724976, NULL, 1, 0, 0, 0, '123123');
INSERT INTO `question` VALUES (15, '15', '123123', 1579094724976, NULL, 1, 0, 0, 0, '123123');
INSERT INTO `question` VALUES (16, '16', '123123', 1579094724976, NULL, 1, 0, 0, 0, '123123');
INSERT INTO `question` VALUES (17, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (18, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (19, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (20, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (21, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (22, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (23, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (24, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (25, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (26, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (27, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (28, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (29, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (30, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (31, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (32, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (33, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (34, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (35, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (36, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (37, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (38, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (39, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (40, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (41, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (42, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (43, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (44, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (45, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (46, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (47, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (48, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (49, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (50, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (51, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (52, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (53, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (54, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (55, '测试', '123456789', 1579094714332, NULL, 1, 0, 1, 0, '标题1,标题2');
INSERT INTO `question` VALUES (56, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (57, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (58, '测试', '123456789', 1579094714332, NULL, 1, 0, 0, 0, '标题1,标题2');
INSERT INTO `question` VALUES (59, '测试', '123456789', 1579094714332, NULL, 1, 0, 1, 0, '标题1,标题2');

SET FOREIGN_KEY_CHECKS = 1;
