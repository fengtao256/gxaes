/*
Navicat MySQL Data Transfer

Source Server         : .
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : ks

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-12-11 15:19:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `stuId` int(11) DEFAULT NULL,
  `queId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES ('1', '1');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `queId` int(11) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `optionA` varchar(100) DEFAULT NULL,
  `optionB` varchar(100) DEFAULT NULL,
  `optionC` varchar(100) DEFAULT NULL,
  `optionD` varchar(100) DEFAULT NULL,
  `correctAnswer` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '1+2=', '0', '1', '2', '3', 'D');
INSERT INTO `question` VALUES ('2', '答案为A', '0', '1', '2', '3', 'A');
INSERT INTO `question` VALUES ('3', '答案为B', '0', '1', '2', '3', 'B');
INSERT INTO `question` VALUES ('4', '答案为C', '0', '1', '2', '3', 'C');
INSERT INTO `question` VALUES ('5', '答案为D', '0', '1', '2', '3', 'D');
INSERT INTO `question` VALUES ('6', '答案为A', '0', '1', '2', '3', 'A');
INSERT INTO `question` VALUES ('7', '答案为B', '0', '1', '2', '3', 'B');
INSERT INTO `question` VALUES ('8', '答案为C', '0', '1', '2', '3', 'C');
INSERT INTO `question` VALUES ('9', '答案为D', '0', '1', '2', '3', 'D');
INSERT INTO `question` VALUES ('10', '答案为A', '0', '1', '2', '3', 'A');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stuId` int(11) DEFAULT NULL,
  `loginName` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'lrw', '666', '574224320@qq.com', '15874427716');
INSERT INTO `student` VALUES (null, 'liujie', '666', '0', '0');
INSERT INTO `student` VALUES (null, 'zsj', '666', '0', '0');
INSERT INTO `student` VALUES (null, 'lp', '666', '0', '0');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `stuId` int(11) DEFAULT NULL,
  `testId` int(11) DEFAULT NULL,
  `queCount` int(11) DEFAULT NULL,
  `correctCount` int(11) DEFAULT NULL,
  `accuracy` double DEFAULT NULL,
  `beginTime` date DEFAULT NULL,
  `endTime` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------

-- ----------------------------
-- Table structure for testdetails
-- ----------------------------
DROP TABLE IF EXISTS `testdetails`;
CREATE TABLE `testdetails` (
  `testId` int(11) DEFAULT NULL,
  `queId` int(11) DEFAULT NULL,
  `actualAnswer` varchar(10) DEFAULT NULL,
  `correct` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of testdetails
-- ----------------------------
