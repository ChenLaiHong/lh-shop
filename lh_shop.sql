/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : lh_shop

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-12-01 21:52:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_level_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `city` varchar(64) DEFAULT NULL COMMENT '所做城市',
  `job` varchar(100) DEFAULT NULL COMMENT '职业',
  `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
  `integration` int(11) DEFAULT NULL COMMENT '积分',
  `growth` int(11) DEFAULT NULL COMMENT '成长值',
  `luckey_count` int(11) DEFAULT NULL COMMENT '剩余抽奖次数',
  `history_integration` int(11) DEFAULT NULL COMMENT '历史积分数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`user_name`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', '4', 'test', '202cb962ac59075b964b07152d234b70', 'windir', '18061581849', '1', '2018-08-02 10:35:44', null, '1', '2009-06-01', '上海', '学生', 'test', null, '5000', null, null, null);
INSERT INTO `member` VALUES ('3', '4', 'windy', 'e10adc3949ba59abbe56e057f20f883e', 'windy', '18061581848', '1', '2018-08-03 16:46:38', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member` VALUES ('4', '4', 'zhengsan', 'e10adc3949ba59abbe56e057f20f883e', 'zhengsan', '18061581847', '1', '2018-11-12 14:12:04', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member` VALUES ('5', '4', 'lisi', 'e10adc3949ba59abbe56e057f20f883e', 'lisi', '18061581841', '1', '2018-11-12 14:12:38', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member` VALUES ('6', '4', 'wangwu', 'e10adc3949ba59abbe56e057f20f883e', 'wangwu', '18061581842', '1', '2018-11-12 14:13:09', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member` VALUES ('7', '4', 'lion', 'e10adc3949ba59abbe56e057f20f883e', 'lion', '18061581845', '1', '2018-11-12 14:21:39', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member` VALUES ('8', '4', 'shari', 'e10adc3949ba59abbe56e057f20f883e', 'shari', '18061581844', '1', '2018-11-12 14:22:00', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member` VALUES ('9', '4', 'aewen', 'e10adc3949ba59abbe56e057f20f883e', 'aewen', '18061581843', '1', '2018-11-12 14:22:55', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member` VALUES ('10', '4', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', null, '17512080612', '1', '2019-03-06 17:51:56', null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for member_level
-- ----------------------------
DROP TABLE IF EXISTS `member_level`;
CREATE TABLE `member_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `growth_point` int(11) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认等级：0->不是；1->是',
  `free_freight_point` decimal(10,2) DEFAULT NULL COMMENT '免运费标准',
  `comment_growth_point` int(11) DEFAULT NULL COMMENT '每次评价获取的成长值',
  `priviledge_free_freight` int(1) DEFAULT NULL COMMENT '是否有免邮特权',
  `priviledge_sign_in` int(1) DEFAULT NULL COMMENT '是否有签到特权',
  `priviledge_comment` int(1) DEFAULT NULL COMMENT '是否有评论获奖励特权',
  `priviledge_promotion` int(1) DEFAULT NULL COMMENT '是否有专享活动特权',
  `priviledge_member_price` int(1) DEFAULT NULL COMMENT '是否有会员价格特权',
  `priviledge_birthday` int(1) DEFAULT NULL COMMENT '是否有生日特权',
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员等级表';

-- ----------------------------
-- Records of member_level
-- ----------------------------
INSERT INTO `member_level` VALUES ('1', '黄金会员', '1000', '0', '199.00', '5', '1', '1', '1', '1', '1', '1', null);
INSERT INTO `member_level` VALUES ('2', '白金会员', '5000', '0', '99.00', '10', '1', '1', '1', '1', '1', '1', null);
INSERT INTO `member_level` VALUES ('3', '钻石会员', '15000', '0', '69.00', '15', '1', '1', '1', '1', '1', '1', null);
INSERT INTO `member_level` VALUES ('4', '普通会员', '1', '1', '199.00', '20', '1', '1', '1', '1', '0', '0', null);

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

-- ----------------------------
-- Table structure for users_member_receive_address
-- ----------------------------
DROP TABLE IF EXISTS `users_member_receive_address`;
CREATE TABLE `users_member_receive_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '收货人名称',
  `phone_number` varchar(64) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认',
  `post_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `province` varchar(100) DEFAULT NULL COMMENT '省份/直辖市',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `region` varchar(100) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址(街道)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员收货地址表';

-- ----------------------------
-- Records of users_member_receive_address
-- ----------------------------
INSERT INTO `users_member_receive_address` VALUES ('1', '1', '大梨', '18033441849', '0', '518000', '广东省', '深圳市', '南山区', '科兴科学园');
INSERT INTO `users_member_receive_address` VALUES ('3', '1', '大梨', '18033441849', '0', '518000', '广东省', '深圳市', '福田区', '清水河街道');
INSERT INTO `users_member_receive_address` VALUES ('4', '1', '大梨', '18033441849', '1', '518000', '广东省', '深圳市', '福田区', '东晓街道');
