/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : chb

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2017-11-30 15:53:05
*/
CREATE DATABASE chb;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for businessman
-- ----------------------------
DROP TABLE IF EXISTS `businessman`;
CREATE TABLE `businessman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `businessmanName` varchar(20) NOT NULL,
  `password` varchar(18) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `phone` int(11) DEFAULT NULL,
  `email` varchar(64) NOT NULL,
  `registerTime` datetime NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `accountBalance` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `city_name` varchar(255) NOT NULL,
  `shortName` varchar(255) NOT NULL,
  `lng` double NOT NULL,
  `lat` double NOT NULL,
  `level` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for classify_goods
-- ----------------------------
DROP TABLE IF EXISTS `classify_goods`;
CREATE TABLE `classify_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classifyName` varchar(255) NOT NULL,
  `classifyDescription` varchar(255) DEFAULT NULL,
  `shopId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `complaintTime` datetime NOT NULL,
  `acceptTime` datetime DEFAULT NULL,
  `finishTime` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for credibility
-- ----------------------------
DROP TABLE IF EXISTS `credibility`;
CREATE TABLE `credibility` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) NOT NULL,
  `recordTime` datetime NOT NULL,
  `shopId` int(11) NOT NULL,
  `changePoint` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopId` int(11) NOT NULL,
  `goodsName` varchar(255) NOT NULL,
  `goodsPrice` double NOT NULL,
  `goodsPhoto` varchar(255) NOT NULL,
  `goodsDescription` varchar(255) DEFAULT NULL,
  `goodsSales` int(11) NOT NULL,
  `isPass` int(1) NOT NULL,
  `isOnline` int(1) NOT NULL,
  `goodsPoint` int(1) DEFAULT NULL,
  `classifyGoodsId` int(11) NOT NULL,
  `goodsNumber` int(11) NOT NULL,
  `goodsRemain` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `type` int(1) NOT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL,
  `shopId` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `sumMoney` double NOT NULL,
  `payMode` int(1) NOT NULL,
  `createTime` datetime NOT NULL,
  `acceptTime` datetime DEFAULT NULL,
  `shopConfirmTime` datetime DEFAULT NULL,
  `userConfirmTime` datetime DEFAULT NULL,
  `isPay` int(1) NOT NULL,
  `paySerialsNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_comment
-- ----------------------------
DROP TABLE IF EXISTS `order_comment`;
CREATE TABLE `order_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopId` int(11) NOT NULL,
  `orderId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `servicePoint` int(1) NOT NULL,
  `time` datetime NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_goods_list
-- ----------------------------
DROP TABLE IF EXISTS `order_goods_list`;
CREATE TABLE `order_goods_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `goodsId` int(11) NOT NULL,
  `count` int(3) NOT NULL,
  `sumMoney` double NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `businessmanId` int(11) NOT NULL,
  `shopName` varchar(100) NOT NULL,
  `shopPhoto` varchar(255) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `credibility` int(3) NOT NULL,
  `lng` double DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `legalRepresentative` varchar(255) DEFAULT NULL,
  `isPass` int(1) NOT NULL,
  `isOnline` int(1) NOT NULL,
  `isOpen` int(1) NOT NULL,
  `openTime` varchar(255) DEFAULT NULL,
  `shopNotice` varchar(255) DEFAULT NULL,
  `startPrice` double DEFAULT NULL,
  `carriage` double DEFAULT NULL,
  `averageTime` int(11) DEFAULT NULL,
  `dispatchDescription` varchar(255) DEFAULT NULL,
  `takeoutAreaId` int(11) DEFAULT NULL,
  `monthSales` int(11) NOT NULL,
  `shopPoint` int(11) DEFAULT NULL,
  `shopClassifyId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for shop_address
-- ----------------------------
DROP TABLE IF EXISTS `shop_address`;
CREATE TABLE `shop_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `specificAddress` varchar(255) NOT NULL,
  `lng` double NOT NULL,
  `lat` double NOT NULL,
  `provinceId` int(11) NOT NULL,
  `cityId` int(11) DEFAULT NULL,
  `areaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for shop_classify
-- ----------------------------
DROP TABLE IF EXISTS `shop_classify`;
CREATE TABLE `shop_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for shop_in_data
-- ----------------------------
DROP TABLE IF EXISTS `shop_in_data`;
CREATE TABLE `shop_in_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopId` int(11) NOT NULL,
  `frontPhoto` varchar(255) NOT NULL,
  `insidePhoto` varchar(255) NOT NULL,
  `IDFrontPhoto` varchar(255) NOT NULL,
  `IDBackPhoto` varchar(255) NOT NULL,
  `legalRepresentative` varchar(255) NOT NULL,
  `businessLicense` varchar(255) NOT NULL,
  `cateringServiceLicense` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for takeout_area
-- ----------------------------
DROP TABLE IF EXISTS `takeout_area`;
CREATE TABLE `takeout_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `password` varchar(18) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `email` varchar(64) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `loginCount` int(11) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `registerTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `provice` int(11) NOT NULL,
  `city` int(11) NOT NULL,
  `area` int(11) NOT NULL,
  `adress` varchar(255) NOT NULL,
  `lng` double DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `postcode` varchar(32) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `name` varchar(125) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
