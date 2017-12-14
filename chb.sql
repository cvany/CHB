/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : chb

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2017-12-12 17:36:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for businessman
-- ----------------------------
DROP TABLE IF EXISTS `businessman`;
CREATE TABLE `businessman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `businessmanName` varchar(20) NOT NULL,
  `password` varchar(18) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `email` varchar(64) NOT NULL,
  `registerTime` datetime NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `accountBalance` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of businessman
-- ----------------------------
INSERT INTO `businessman` VALUES ('1', '王道', '123456', '123456', '13428100755', '814675492@qq.com', '2017-12-07 14:41:47', null, '0');
INSERT INTO `businessman` VALUES ('2', '李丽', '123456', '123456', '13428244788', '458156875@qq.com', '2017-12-07 14:53:51', null, '0');
INSERT INTO `businessman` VALUES ('3', '刘亦菲', '123456', '123456', '13428200799', '8456449812@qq.com', '2017-12-12 08:52:42', null, '0');
INSERT INTO `businessman` VALUES ('4', '詹三', '123456', '123456', '13428877599', '8616696516@qq.com', '2017-12-12 09:14:12', null, '0');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `city_name` varchar(255) NOT NULL,
  `shortName` varchar(255) DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `level` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classify_goods
-- ----------------------------
INSERT INTO `classify_goods` VALUES ('1', '小吃', '小吃', '1');
INSERT INTO `classify_goods` VALUES ('2', '快餐', '快餐', '1');
INSERT INTO `classify_goods` VALUES ('3', '水果', '水果类', '1');
INSERT INTO `classify_goods` VALUES ('4', '饮料', null, '1');
INSERT INTO `classify_goods` VALUES ('5', '休闲食品', null, '1');

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
-- Records of complaint
-- ----------------------------

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
-- Records of credibility
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '1', '烤鸡', '17', 'upload/business/goods', '好香的烤鸡', '120', '1', '1', '4', '1', '100', '100');
INSERT INTO `goods` VALUES ('2', '1', '番茄炒鸡蛋', '12', 'upload/business/goods', '好吃的番茄炒鸡蛋哦', '110', '1', '1', '4', '1', '20', '20');
INSERT INTO `goods` VALUES ('3', '1', '隆江猪脚饭', '14', 'upload/business/goods', '正宗的隆江猪脚饭', '150', '1', '1', '4', '1', '30', '20');
INSERT INTO `goods` VALUES ('4', '2', '叉烧饭', '10', 'upload/business/goods', '', '180', '1', '1', '4', '1', '30', '20');
INSERT INTO `goods` VALUES ('5', '1', '叉烧饭', '10', 'upload/business/goods', '', '110', '1', '1', '4', '1', '30', '20');
INSERT INTO `goods` VALUES ('6', '2', '鸡腿饭', '10', 'upload/business/goods', '', '190', '1', '1', '4', '1', '52', '30');
INSERT INTO `goods` VALUES ('7', '2', '红烧牛肉饭', '18', 'upload/business/goods', '', '220', '1', '1', '4', '1', '44', '30');
INSERT INTO `goods` VALUES ('8', '2', '铁板烧', '13', 'upload/business/goods', '', '200', '1', '1', '4', '1', '33', '30');
INSERT INTO `goods` VALUES ('9', '3', '港式手撕鸡十叉烧煲仔饭', '30', 'upload/business/', '', '123', '1', '1', '4', '2', '50', '10');
INSERT INTO `goods` VALUES ('10', '3', '黄金鸡腿十港式叉烧煲仔饭', '20', 'upload/business/', '', '153', '1', '1', '4', '2', '40', '10');
INSERT INTO `goods` VALUES ('11', '3', '港式手撕鸡十脆骨肠十卤蛋煲仔饭', '20', 'upload/business/', '', '153', '1', '1', '4', '2', '40', '10');
INSERT INTO `goods` VALUES ('12', '3', '港式手撕鸡十嫩香腿排煲仔饭', '20', 'upload/business/', '', '153', '1', '1', '4', '2', '40', '10');
INSERT INTO `goods` VALUES ('13', '4', '鸡蛋炒米线', '9', 'upload/business/', '', '143', '1', '1', '4', '2', '40', '10');
INSERT INTO `goods` VALUES ('14', '4', '砂锅瘦肉米线', '11', 'upload/business/', '', '167', '1', '1', '4', '2', '40', '10');
INSERT INTO `goods` VALUES ('15', '4', '砂锅辣鸡云吞米线(面，河粉)', '12', 'upload/business/', '', '167', '1', '1', '4', '2', '40', '10');
INSERT INTO `goods` VALUES ('16', '4', '鲜虾米线', '13', 'upload/business/', '', '187', '1', '1', '4', '2', '40', '10');

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
-- Records of message
-- ----------------------------

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
-- Records of order
-- ----------------------------

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
-- Records of order_comment
-- ----------------------------

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
-- Records of order_goods_list
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '1', '加斯顿炸鸡汉堡店', null, '13428100755', '100', '113.253123', '23.45123', '湖光路1号广东海洋大学校门对面', '王道', '1', '1', '1', '8:30-22:00', '叫外卖，上饿了么！餐厅联系电话：18802508719；\r\n					短号：668719 不好意思同学们，\r\n					使用红包者无效一律不接单，使用红包不能超过5元，超过5元一律无效', '8', '9', '45', '尽快送达', '1', '888', '4', '3');
INSERT INTO `shop` VALUES ('2', '2', '韩国料理', null, '13428244788', '100', '113.154254', '22.154233', '海大路1号附近', '李丽', '1', '1', '1', '9：00-23:00', '新用户下单立减1元', '9', '10', '50', '尽快送达', '1', '777', '4', '1');
INSERT INTO `shop` VALUES ('3', '3', '港式飘香煲仔饭', null, '13428200799', '100', '113.154254', '22.154233', '海大路1号附近', '刘亦菲', '1', '1', '1', '8：00-22:00', '实体店正式推出韩式烤肉.火锅，欢迎大家到店品尝！外卖配送时间上午10:40下午4:40由外卖团队配送', '9', '10', '40', '尽快送达', '1', '678', '4', '1');
INSERT INTO `shop` VALUES ('4', '4', '福建千里香砂锅店', null, '13428877599', '100', '113.254215', '22.154213', '海大路1号附近', '詹三', '1', '1', '1', '8：30-22：30', '欢迎各位新老顾客同学们好，本店已重新开通饿了么外卖，希望继续光临，谢谢大家，电话13692406511、短号666511、优佳团队送的，可送上楼哦！', '9', '10', '35', '尽快送达', '1', '1020', '4', '2');

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
-- Records of shop_address
-- ----------------------------

-- ----------------------------
-- Table structure for shop_classify
-- ----------------------------
DROP TABLE IF EXISTS `shop_classify`;
CREATE TABLE `shop_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_classify
-- ----------------------------
INSERT INTO `shop_classify` VALUES ('1', '快餐便当');
INSERT INTO `shop_classify` VALUES ('2', '特色菜系');
INSERT INTO `shop_classify` VALUES ('3', '小吃宵夜');
INSERT INTO `shop_classify` VALUES ('4', '异国料理');
INSERT INTO `shop_classify` VALUES ('5', '美食');
INSERT INTO `shop_classify` VALUES ('6', '甜品饮品');
INSERT INTO `shop_classify` VALUES ('7', '商店超市');
INSERT INTO `shop_classify` VALUES ('8', '果树生鲜');

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
-- Records of shop_in_data
-- ----------------------------

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
-- Records of takeout_area
-- ----------------------------

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
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '小花', '12335sdfs', '132', '123@qq.com', null, '0', null, '2017-11-30 15:58:03', null);

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `provinceId` int(11) NOT NULL,
  `cityId` int(11) NOT NULL,
  `areaId` int(11) NOT NULL,
  `adress` varchar(255) NOT NULL,
  `lng` double DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `postcode` varchar(32) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `name` varchar(125) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_address
-- ----------------------------
