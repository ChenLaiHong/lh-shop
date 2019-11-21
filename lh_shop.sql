/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : lh_shop

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-11-21 21:31:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `is_leaf` int(3) DEFAULT NULL,
  `parentid` bigint(255) DEFAULT NULL,
  `type_levels` int(3) DEFAULT NULL COMMENT '层次',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_type
-- ----------------------------
INSERT INTO `product_type` VALUES ('1', '运动服饰', '0', '0', '1');
INSERT INTO `product_type` VALUES ('4', '运动裤', '1', '1', '2');
INSERT INTO `product_type` VALUES ('5', '背心', '1', '1', '2');
INSERT INTO `product_type` VALUES ('6', '短袖', '1', '1', '2');
INSERT INTO `product_type` VALUES ('7', '长袖', '1', '1', '2');
INSERT INTO `product_type` VALUES ('8', '电子产品', '0', '0', '1');
INSERT INTO `product_type` VALUES ('9', '手机', '0', '8', '2');
INSERT INTO `product_type` VALUES ('10', '电脑', '0', '8', '2');
INSERT INTO `product_type` VALUES ('20', '零食', '0', '0', '1');
INSERT INTO `product_type` VALUES ('23', '干果', '1', '20', '2');
INSERT INTO `product_type` VALUES ('24', '膨化食品', '1', '20', '2');
INSERT INTO `product_type` VALUES ('49', '电视', '1', '8', '2');
INSERT INTO `product_type` VALUES ('59', '三星', '1', '9', '3');
INSERT INTO `product_type` VALUES ('60', '小米', '1', '9', '3');
INSERT INTO `product_type` VALUES ('61', '华为', '1', '9', '3');
INSERT INTO `product_type` VALUES ('62', '蛋糕', '0', '20', '2');
INSERT INTO `product_type` VALUES ('63', '巧克力味', '1', '62', '3');
INSERT INTO `product_type` VALUES ('64', '慕斯', '1', '62', '3');
INSERT INTO `product_type` VALUES ('65', '帽子', '1', '1', '2');
INSERT INTO `product_type` VALUES ('66', '护件', '1', '1', '2');
INSERT INTO `product_type` VALUES ('70', '篮球服', '1', '1', '2');
INSERT INTO `product_type` VALUES ('71', '家具', '0', '0', '1');
INSERT INTO `product_type` VALUES ('72', '冰箱', '1', '71', '2');
INSERT INTO `product_type` VALUES ('73', '空调', '1', '71', '2');

-- ----------------------------
-- Table structure for product_type2
-- ----------------------------
DROP TABLE IF EXISTS `product_type2`;
CREATE TABLE `product_type2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品类别主键',
  `is_leaf` int(3) DEFAULT NULL COMMENT '是否为叶子节点（0不是，1是）',
  `parentid` bigint(20) NOT NULL COMMENT '商品类别父节点',
  `name` varchar(50) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '商品类别名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- ----------------------------
-- Records of product_type2
-- ----------------------------
INSERT INTO `product_type2` VALUES ('1', '0', '0', '手机');
INSERT INTO `product_type2` VALUES ('2', '0', '0', '电脑');
INSERT INTO `product_type2` VALUES ('3', '0', '0', '电器');
INSERT INTO `product_type2` VALUES ('4', '1', '1', '华为手机');
INSERT INTO `product_type2` VALUES ('5', '1', '1', '小米9');
INSERT INTO `product_type2` VALUES ('6', '1', '2', '戴尔G7');
INSERT INTO `product_type2` VALUES ('7', '0', '2', '华硕');
INSERT INTO `product_type2` VALUES ('8', '1', '3', '冰箱');
INSERT INTO `product_type2` VALUES ('9', '1', '3', '电视');
INSERT INTO `product_type2` VALUES ('10', '1', '7', '华硕128SSD');
