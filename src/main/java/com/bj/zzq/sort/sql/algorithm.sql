/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 08/04/2019 22:19:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for algorithm
-- ----------------------------
DROP TABLE IF EXISTS `algorithm`;
CREATE TABLE `algorithm`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `algorithm_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '算法类型 1-快速排序 2-希尔排序 3-归并排序 4-插入排序',
  `mark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '算法说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of algorithm
-- ----------------------------
INSERT INTO `algorithm` VALUES (1, '3', '正常归并排序');
INSERT INTO `algorithm` VALUES (2, '4', '正常插入排序');
INSERT INTO `algorithm` VALUES (3, '2', '正常希尔排序,增量为h=3h+1');
INSERT INTO `algorithm` VALUES (4, '1', 'n<=3时，对于小划分使用手动排序');
INSERT INTO `algorithm` VALUES (5, '1', 'n<=9时，对于小划分使用插入排序');
INSERT INTO `algorithm` VALUES (6, '1', 'n<=3时，对于小划分不处理，最后统一使用插入排序');
INSERT INTO `algorithm` VALUES (7, '1', 'n<=9时，对于小划分不处理，最后统一使用插入排序');

SET FOREIGN_KEY_CHECKS = 1;
