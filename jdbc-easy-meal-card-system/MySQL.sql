/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : meal_card

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 22/01/2019 21:27:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` varchar(20) NOT NULL,
  `name` varchar(35) DEFAULT NULL,
  `class_name` varchar(30) DEFAULT NULL,
  `money` double DEFAULT '100',
  `password` varchar(30) DEFAULT '000000',
  `num_of_bank_card` double DEFAULT '1000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
BEGIN;
INSERT INTO `card` VALUES ('1', 'test1', 'class1', 90, '123456', 1000);
INSERT INTO `card` VALUES ('4', 'test4', 'class4', 100, '123456', 1000);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
