/*
 Navicat Premium Data Transfer

 Source Server         : 172.18.150.49
 Source Server Type    : MySQL
 Source Server Version : 50619
 Source Host           : 172.18.150.49:3306
 Source Schema         : gxaes

 Target Server Type    : MySQL
 Target Server Version : 50619
 File Encoding         : 65001

 Date: 15/01/2020 11:55:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `stuId` int(11) NOT NULL,
  `queId` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES (1, 1);
INSERT INTO `favorite` VALUES (1, 5);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `queId` int(11) NOT NULL,
  `topic` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optionA` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optionB` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optionC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optionD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `correctAnswer` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`queId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, '1+2=', '0', '1', '2', '3', 'D');
INSERT INTO `question` VALUES (2, '答案为A', '0', '1', '2', '3', 'A');
INSERT INTO `question` VALUES (3, '答案为B', '0', '1', '2', '3', 'B');
INSERT INTO `question` VALUES (4, '答案为C', '0', '1', '2', '3', 'C');
INSERT INTO `question` VALUES (5, '答案为D', '0', '1', '2', '3', 'D');
INSERT INTO `question` VALUES (6, '答案为A', '0', '1', '2', '3', 'A');
INSERT INTO `question` VALUES (7, '答案为B', '0', '1', '2', '3', 'B');
INSERT INTO `question` VALUES (8, '答案为C', '0', '1', '2', '3', 'C');
INSERT INTO `question` VALUES (9, '答案为D', '0', '1', '2', '3', 'D');
INSERT INTO `question` VALUES (10, '答案为A', '0', '1', '2', '3', 'A');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stuId` int(11) NOT NULL,
  `loginName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stuId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'lrw', '666', '574224320@qq.com', '15874427716');
INSERT INTO `student` VALUES (2, 'liujie', '666', '0', '0');
INSERT INTO `student` VALUES (3, 'zsj', '666', '0', '0');
INSERT INTO `student` VALUES (4, 'lp', '666', '0', '0');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `stuId` int(11) NOT NULL,
  `testId` int(11) NOT NULL,
  `queCount` int(11) NULL DEFAULT NULL,
  `correctCount` int(11) NULL DEFAULT NULL,
  `accuracy` double NULL DEFAULT NULL,
  `beginTime` timestamp(0) NULL DEFAULT NULL,
  `endTime` timestamp(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, 824554108, 10, 8, 0.8, '2020-01-15 11:46:57', '2020-01-15 11:48:16');

-- ----------------------------
-- Table structure for testdetails
-- ----------------------------
DROP TABLE IF EXISTS `testdetails`;
CREATE TABLE `testdetails`  (
  `testId` int(11) NOT NULL,
  `queId` int(11) NOT NULL,
  `actualAnswer` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `correct` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of testdetails
-- ----------------------------
INSERT INTO `testdetails` VALUES (824554108, 10, 'A', 1);
INSERT INTO `testdetails` VALUES (824554108, 1, 'C', 0);
INSERT INTO `testdetails` VALUES (824554108, 5, 'C', 0);
INSERT INTO `testdetails` VALUES (824554108, 7, 'B', 1);
INSERT INTO `testdetails` VALUES (824554108, 3, 'B', 1);
INSERT INTO `testdetails` VALUES (824554108, 6, 'A', 1);
INSERT INTO `testdetails` VALUES (824554108, 8, 'C', 1);
INSERT INTO `testdetails` VALUES (824554108, 4, 'C', 1);
INSERT INTO `testdetails` VALUES (824554108, 9, 'D', 1);
INSERT INTO `testdetails` VALUES (824554108, 2, 'A', 1);

SET FOREIGN_KEY_CHECKS = 1;
