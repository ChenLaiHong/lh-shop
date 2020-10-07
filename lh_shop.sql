/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : lh_shop

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2020-10-08 00:39:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址主键',
  `receiver` varchar(50) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '收货人名称',
  `phone` varchar(11) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '收货人手机号',
  `location` varchar(100) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '省市区',
  `full_address` varchar(100) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '详细地址',
  `default_address` int(2) DEFAULT '1' COMMENT '是否是默认地址（0：是；1：否）',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(2) DEFAULT '0' COMMENT '状态（0：使用，1：被删除）',
  `user_id` int(11) DEFAULT NULL COMMENT '地址所属账号',
  PRIMARY KEY (`address_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `person` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='用户地址表';

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('7', '李兴', '13692548756', '广东省/广州市/白云区', '金兰花苑11栋', '0', '2020-03-14 17:29:40', '2020-03-14 17:29:40', '0', '2');
INSERT INTO `address` VALUES ('8', '张晓', '13698562323', '北京市/北京市/朝阳区', '信仰街道', '1', '2020-03-14 21:23:32', '2020-03-14 21:23:32', '1', '2');
INSERT INTO `address` VALUES ('10', '李四', '13756545263', '广西壮族自治区/柳州市/柳南区', '那零花园', '1', '2020-03-14 22:54:49', '2020-03-14 22:54:49', '0', '2');
INSERT INTO `address` VALUES ('11', '李四', '13565456325', '广东省/茂名市/电白区', '金兰花园', '0', '2020-05-04 14:16:03', '2020-05-04 14:16:03', '0', '3');
INSERT INTO `address` VALUES ('12', '李四华', '13654265325', '贵州省/黔西南布依族苗族自治州/兴仁县', '广厦大道', '1', '2020-05-04 21:45:03', '2020-05-04 21:45:03', '0', '3');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页轮播图主键',
  `banner_name` varchar(100) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT 'banner图名称',
  `banner_image` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT 'banner图地址',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(2) DEFAULT '1' COMMENT '使用状态（1：使用，0：废弃）',
  PRIMARY KEY (`banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='轮播图表';

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES ('1', '双11', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5R_DaAOQ0TAASYNAKxrwk899.jpg', '2020-02-23 12:14:46', '1');
INSERT INTO `banner` VALUES ('2', '抢购是', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5R-2qAYm8-AAWSugReeng268.jpg', '2020-02-23 12:11:22', '1');
INSERT INTO `banner` VALUES ('3', '啊啊', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5R-fmAZdP0AAMuOFQbpCc103.jpg', '2020-02-23 12:05:14', '1');

-- ----------------------------
-- Table structure for browse_record
-- ----------------------------
DROP TABLE IF EXISTS `browse_record`;
CREATE TABLE `browse_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '浏览记录主键',
  `product_id` int(11) DEFAULT NULL COMMENT '浏览商品的主键',
  `browse_time` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '浏览时间',
  `frequency` int(11) DEFAULT NULL COMMENT '浏览次数',
  `user_id` int(11) DEFAULT NULL COMMENT '浏览者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='浏览商品的记录';

-- ----------------------------
-- Records of browse_record
-- ----------------------------
INSERT INTO `browse_record` VALUES ('1', '1', '20200405100408', '12', '2');
INSERT INTO `browse_record` VALUES ('2', '3', '20200516010503', '12', '2');
INSERT INTO `browse_record` VALUES ('3', '4', '20200516100506', '15', '2');
INSERT INTO `browse_record` VALUES ('4', '5', '20200504060533', '8', '2');
INSERT INTO `browse_record` VALUES ('5', '8', '20200504110513', '8', '2');
INSERT INTO `browse_record` VALUES ('6', '3', '20200516040554', '8', '3');
INSERT INTO `browse_record` VALUES ('7', '4', '20200515100536', '10', '3');
INSERT INTO `browse_record` VALUES ('8', '16', '20200504110511', '3', '3');
INSERT INTO `browse_record` VALUES ('9', '7', '20200504100536', '4', '3');
INSERT INTO `browse_record` VALUES ('10', '13', '20200504100537', '2', '3');
INSERT INTO `browse_record` VALUES ('11', '6', '20200504110515', '4', '2');
INSERT INTO `browse_record` VALUES ('12', '6', '20200504090512', '2', '3');
INSERT INTO `browse_record` VALUES ('13', '15', '20200504090513', '2', '3');
INSERT INTO `browse_record` VALUES ('14', '5', '20200504050525', '1', '3');
INSERT INTO `browse_record` VALUES ('15', '9', '20200504050548', '1', '3');
INSERT INTO `browse_record` VALUES ('16', '9', '20200504110557', '7', '2');
INSERT INTO `browse_record` VALUES ('17', '11', '20200504050517', '1', '2');
INSERT INTO `browse_record` VALUES ('18', '2', '20200515090523', '2', '2');
INSERT INTO `browse_record` VALUES ('19', '19', '20200504060521', '1', '2');
INSERT INTO `browse_record` VALUES ('20', '16', '20200504060547', '1', '2');
INSERT INTO `browse_record` VALUES ('21', '7', '20200504060509', '3', '2');
INSERT INTO `browse_record` VALUES ('22', '10', '20200504110500', '3', '2');
INSERT INTO `browse_record` VALUES ('23', '17', '20200515100508', '6', '2');
INSERT INTO `browse_record` VALUES ('24', '12', '20200504060510', '1', '2');
INSERT INTO `browse_record` VALUES ('25', '12', '20200504090529', '1', '3');
INSERT INTO `browse_record` VALUES ('26', '17', '20200504110523', '1', '3');
INSERT INTO `browse_record` VALUES ('27', '10', '20200513080530', '4', '3');

-- ----------------------------
-- Table structure for catalog_one
-- ----------------------------
DROP TABLE IF EXISTS `catalog_one`;
CREATE TABLE `catalog_one` (
  `one_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `one_name` varchar(50) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '一级名称',
  `one_image` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '图片地址',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(2) DEFAULT '1' COMMENT '使用状态（1：使用，0：废弃）',
  PRIMARY KEY (`one_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='一级分类表';

-- ----------------------------
-- Records of catalog_one
-- ----------------------------
INSERT INTO `catalog_one` VALUES ('1', '运动服饰', 'http://47.102.212.171/group1/M00/00/00/rBEaFF47zO6AZwzgAAACxfoCEF4041.png', '2020-02-26 23:38:32', '1');
INSERT INTO `catalog_one` VALUES ('2', '电子产品', 'http://47.102.212.171/group1/M00/00/00/rBEaFF47zTWAKtakAAAB99cNec8391.png', '2020-02-06 16:24:22', '1');
INSERT INTO `catalog_one` VALUES ('3', '得到', '', '2020-02-06 10:19:58', '0');
INSERT INTO `catalog_one` VALUES ('4', '存储', '', '2020-02-06 12:06:30', '0');
INSERT INTO `catalog_one` VALUES ('5', '哈哈', '', '2020-02-06 12:07:32', '0');
INSERT INTO `catalog_one` VALUES ('6', '食品', 'http://47.102.212.171/group1/M00/00/00/rBEaFF471DiALvE-AAAC-od4Nwo252.png', '2020-02-06 16:54:16', '1');
INSERT INTO `catalog_one` VALUES ('7', 'cc', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5Q95iAKT0kAAAF6MnosJ0198.png', '2020-02-22 17:42:48', '0');
INSERT INTO `catalog_one` VALUES ('8', '测试', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5WieGACOAEAANdte1VLLw624.jpg', '2020-02-26 23:08:18', '0');
INSERT INTO `catalog_one` VALUES ('9', '乐器', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5nsb-AINr-AAAEDCdGBf8767.png', '2020-03-10 23:26:55', '1');
INSERT INTO `catalog_one` VALUES ('10', '艺术', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5nsfuAE_gzAAAFm-mMEt8508.png', '2020-03-10 23:27:55', '1');
INSERT INTO `catalog_one` VALUES ('11', '图书', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5nsjKAKj8BAAACD9NMBxU172.png', '2020-03-10 23:28:50', '1');
INSERT INTO `catalog_one` VALUES ('12', '生鲜', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5nss2Afi63AAAF5rXFrt4658.png', '2020-03-10 23:31:25', '1');
INSERT INTO `catalog_one` VALUES ('13', '家用电器', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5nsxiAB7ZSAAAElh1PzR8796.png', '2020-03-10 23:32:41', '1');
INSERT INTO `catalog_one` VALUES ('14', '医药保健', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ns1GAJ2-LAAACWZnwxQs487.png', '2020-03-10 23:33:38', '1');

-- ----------------------------
-- Table structure for catalog_three
-- ----------------------------
DROP TABLE IF EXISTS `catalog_three`;
CREATE TABLE `catalog_three` (
  `three_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '三级主键',
  `three_name` varchar(50) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '三级名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(2) DEFAULT '1' COMMENT '使用状态（1：使用，0：废弃）',
  `two_id` int(11) DEFAULT NULL COMMENT '二级主键为外键',
  PRIMARY KEY (`three_id`),
  KEY `two_id` (`two_id`),
  CONSTRAINT `catalog_three_ibfk_1` FOREIGN KEY (`two_id`) REFERENCES `catalog_two` (`two_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='三级分类表';

-- ----------------------------
-- Records of catalog_three
-- ----------------------------
INSERT INTO `catalog_three` VALUES ('1', '华为', '2020-02-10 16:48:56', '1', '4');
INSERT INTO `catalog_three` VALUES ('2', '小米', '2020-02-10 16:49:05', '1', '4');
INSERT INTO `catalog_three` VALUES ('3', '魅族', '2020-02-10 16:52:36', '1', '4');
INSERT INTO `catalog_three` VALUES ('4', '测试', '2020-02-10 16:52:42', '0', '4');
INSERT INTO `catalog_three` VALUES ('5', '曲奇', '2020-02-10 16:53:06', '1', '2');
INSERT INTO `catalog_three` VALUES ('6', '夹心饼干', '2020-02-10 16:53:16', '1', '2');
INSERT INTO `catalog_three` VALUES ('7', '慕斯', '2020-02-10 16:53:29', '1', '1');
INSERT INTO `catalog_three` VALUES ('8', '安踏', '2020-02-10 16:53:51', '1', '5');
INSERT INTO `catalog_three` VALUES ('9', '李宁', '2020-02-10 16:53:58', '1', '5');
INSERT INTO `catalog_three` VALUES ('10', '安踏运动套装', '2020-03-10 23:22:11', '1', '6');
INSERT INTO `catalog_three` VALUES ('11', '李宁运动服套装', '2020-03-10 23:22:30', '1', '6');
INSERT INTO `catalog_three` VALUES ('12', '头绳', '2020-03-10 23:35:19', '1', '7');
INSERT INTO `catalog_three` VALUES ('13', '护膝', '2020-03-10 23:35:31', '1', '7');
INSERT INTO `catalog_three` VALUES ('14', '华硕', '2020-03-10 23:36:07', '1', '3');
INSERT INTO `catalog_three` VALUES ('15', '苹果', '2020-03-10 23:36:13', '1', '3');
INSERT INTO `catalog_three` VALUES ('16', '戴尔', '2020-03-10 23:36:19', '1', '3');

-- ----------------------------
-- Table structure for catalog_two
-- ----------------------------
DROP TABLE IF EXISTS `catalog_two`;
CREATE TABLE `catalog_two` (
  `two_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '二级主键',
  `two_name` varchar(50) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '二级名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(2) DEFAULT '1' COMMENT '使用状态（1：使用，0：废弃）',
  `one_id` int(11) DEFAULT NULL COMMENT '一级主键为外键',
  PRIMARY KEY (`two_id`),
  KEY `one_id` (`one_id`),
  CONSTRAINT `catalog_two_ibfk_1` FOREIGN KEY (`one_id`) REFERENCES `catalog_one` (`one_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='二级分类表';

-- ----------------------------
-- Records of catalog_two
-- ----------------------------
INSERT INTO `catalog_two` VALUES ('1', '蛋糕', '2020-02-10 15:05:20', '1', '6');
INSERT INTO `catalog_two` VALUES ('2', '饼干', '2020-02-10 15:05:10', '1', '6');
INSERT INTO `catalog_two` VALUES ('3', '电脑', '2020-02-10 16:44:04', '1', '2');
INSERT INTO `catalog_two` VALUES ('4', '手机', '2020-02-10 16:44:11', '1', '2');
INSERT INTO `catalog_two` VALUES ('5', '跑步鞋', '2020-02-10 16:53:41', '1', '1');
INSERT INTO `catalog_two` VALUES ('6', '运动套装', '2020-03-10 23:21:00', '1', '1');
INSERT INTO `catalog_two` VALUES ('7', '运动配饰', '2020-03-10 23:21:34', '1', '1');
INSERT INTO `catalog_two` VALUES ('8', '钢琴', '2020-03-10 23:37:06', '1', '9');
INSERT INTO `catalog_two` VALUES ('9', '吉他', '2020-03-10 23:37:12', '1', '9');
INSERT INTO `catalog_two` VALUES ('10', '电子琴', '2020-03-10 23:37:20', '1', '9');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价主键',
  `comment_level` varchar(5) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '评价等级（1：好评，2：中评，3：差评）',
  `comment` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '评价内容',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  `user_id` int(11) DEFAULT NULL COMMENT '评价用户',
  `item_id` int(11) DEFAULT NULL COMMENT '评价的商品',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='商品评价表';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '很好', '2020-05-10 17:57:32', '2', '5');
INSERT INTO `comment` VALUES ('2', '1', '很好啊', '2020-05-10 17:57:32', '2', '12');
INSERT INTO `comment` VALUES ('3', '1', 'ee', '2020-05-10 18:20:01', '2', '16');
INSERT INTO `comment` VALUES ('4', '2', '还行吧', '2020-05-10 22:51:13', '2', '34');
INSERT INTO `comment` VALUES ('5', '3', '不行', '2020-05-10 23:01:44', '2', '17');
INSERT INTO `comment` VALUES ('6', '3', '嗯嗯', '2020-05-10 23:11:33', '2', '32');
INSERT INTO `comment` VALUES ('7', '1', '沸沸汤汤', '2020-05-10 23:13:28', '2', '33');
INSERT INTO `comment` VALUES ('8', '1', '大多数', '2020-05-10 23:18:10', '2', '39');
INSERT INTO `comment` VALUES ('9', '1', '丰富的', '2020-05-10 23:22:39', '2', '30');
INSERT INTO `comment` VALUES ('10', '2', '呃呃呃呃呃', '2020-05-11 23:12:05', '3', '8');
INSERT INTO `comment` VALUES ('11', '2', '她天天让人', '2020-05-11 23:17:11', '3', '10');
INSERT INTO `comment` VALUES ('12', '2', '嗯嗯嗯', '2020-05-11 23:18:14', '3', '36');
INSERT INTO `comment` VALUES ('13', '1', '还行', '2020-05-11 23:20:27', '3', '14');
INSERT INTO `comment` VALUES ('14', '1', '嗯嗯方法试试', '2020-05-11 23:30:09', '3', '27');
INSERT INTO `comment` VALUES ('15', '3', '不怎样', '2020-05-12 20:52:57', '3', '37');

-- ----------------------------
-- Table structure for express_company
-- ----------------------------
DROP TABLE IF EXISTS `express_company`;
CREATE TABLE `express_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '快递公司主键',
  `company_name` varchar(100) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '快递公司名称',
  `company_icon` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '公司图标',
  `company_address` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '公司总部地址',
  `company_phone` varchar(20) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '官方电话',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` varchar(3) COLLATE utf8_general_mysql500_ci DEFAULT '1' COMMENT '状态（1：使用，0：停用）',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='快递公司管理表';

-- ----------------------------
-- Records of express_company
-- ----------------------------
INSERT INTO `express_company` VALUES ('1', '圆通速运', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6-OLCANCOcAAAUyHkAEdo287.png', 'www.yto.net.cn', '95554', '2020-03-22 12:01:23', '2020-05-15 14:37:37', '1');
INSERT INTO `express_company` VALUES ('2', '韵达', 'http://47.102.212.171/group1/M00/00/00/rBEaFF528GCANu-GAAAPp9Pz2AI965.png', 'www.yundaex.com', '95546', '2020-03-22 12:58:10', '2020-03-22 12:58:10', '1');
INSERT INTO `express_company` VALUES ('3', '顺丰', 'http://47.102.212.171/group1/M00/00/00/rBEaFF528MeAakBkAAAVJxqZr6I301.png', 'www.sf-express.com', '95338', '2020-03-22 13:00:25', '2020-03-22 13:00:25', '1');
INSERT INTO `express_company` VALUES ('4', '中通', 'http://47.102.212.171/group1/M00/00/00/rBEaFF528SOAJdy7AAAjJR_fplg928.png', 'www.zto.com', '95311', '2020-03-22 13:01:25', '2020-03-22 13:01:25', '1');
INSERT INTO `express_company` VALUES ('5', '申通', 'http://47.102.212.171/group1/M00/00/00/rBEaFF528VyAd7OPAAAWLnFkcAU654.png', 'www.sto.cn', '95543', '2020-03-22 13:02:21', '2020-03-22 13:02:21', '1');

-- ----------------------------
-- Table structure for head_lines
-- ----------------------------
DROP TABLE IF EXISTS `head_lines`;
CREATE TABLE `head_lines` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻主键',
  `news_title` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '新闻标题',
  `new_content` text COLLATE utf8_general_mysql500_ci COMMENT '新闻内容',
  `image_url` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '新闻内容中的图片路径',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(255) DEFAULT '1' COMMENT '使用状态（1：使用，0：废弃）',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='新闻头条表';

-- ----------------------------
-- Records of head_lines
-- ----------------------------
INSERT INTO `head_lines` VALUES ('1', 'iPhone 12正式确认！支持5G，七千档起步！', '<span><span><span><span><span><span><p>在2019年的时候，苹果库克明确给大家表示，2019年不会推出5G手机，因为实在是不成熟。不过与库克看法非常不一样的国产手机厂商们可没有坐以待毙，而是主动出击发布自家的5G手机。</p><span><p>所以在2019年以来国产中就相继有5G手机面世，而且数量还不少。关于5G条件的成熟与否现在是不好下定论的，因为老实说，苹果的5G技术的确是赶不上华为，所以库克坚持在2019年不发布5G手机也是情有可原，而华为在2019年就大量发布5G手机也是情有可原的，毕竟人家技术都成熟了为什么不抢先发布5G手机呢？<img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5TYEqAR3qXAACZDd6yUK8220.jpg\" style=\"max-width: 100%;\"></p><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p>', '<img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5TYEqAR3qXAACZDd6yUK8220.jpg\" style=\"max-width: 100%;\">', '2020-02-26 23:57:40', '1');
INSERT INTO `head_lines` VALUES ('2', '还在为买不到口罩发愁吗？国家发声了', '<span><span><span><span><span><span><span><span></span><p>面对传播速度超快的疫情，如今人们最关心的物品，可就要属口罩了。</p><p>口罩一时之间被抢售一空，充斥在朋友圈中最多的信息都是，口罩应该怎么选、用过的口罩该如何处理、一次性的医用口罩可以抵抗病毒的侵袭吗？N95&amp;KN95口罩也是热搜不断。</p><p>近日，对于全国各地均反映买不到口罩的问题，党组成员、总工程师田玉龙在会上表示，我国口罩总体产能在每天2000多万只，是全球最大的，供应能力是够的，但产能恢复需要时间。</p><p>国家发展改革委副主任连维良进一步表示，医用N95口罩各方面汇总的需求很大，已经按照一倍以上的规模组织产能。他说：“现在不少企业担心扩产或将带来产能过剩。疫情过后，富余的产量政府将进行收储，符合标准的企业可以开足马力进行生产。”</p><p><img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5X0tWAIhojAAAF6MnosJ0141.png\" style=\"max-width:100%;\"></p></span><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p>', '<img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5X0tWAIhojAAAF6MnosJ0141.png\" style=\"max-width:100%;\">', '2020-02-28 23:23:47', '1');
INSERT INTO `head_lines` VALUES ('3', '霍尼韦尔KN95防尘防霾口罩，守护您的呼吸健康!', '<span><span><span><span><span><span></span><p>\n\n<span>防范多种污染，防尘防霾防颗粒物，防飞沫。低阻舒适透气，无憋闷感，佩戴舒适。专利呼吸阀，吸气时关闭，呼气时打开。亲肤柔软贴脸，对肌肤无刺激。表面均匀密布气孔，内层轻柔光滑。</span>\n\n</p><p><img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5X04uAL9lrAACZDd6yUK8285.jpg\" style=\"max-width:100%;\"></p></span><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p></span><p><br></p>', '<img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5X04uAL9lrAACZDd6yUK8285.jpg\" style=\"max-width:100%;\">', '2020-02-27 22:35:52', '1');
INSERT INTO `head_lines` VALUES ('4', '20款防护口罩大横评！预防病毒流感外出神兵利器推荐', '<span></span><p>春季是传染疾病的多发季节，人们在这段时间里需特别注意身体健康防护，而佩戴口罩是最重要最有效，也是操作最为方便的防控手段之一。</p><p>口罩产品不仅种类繁多，防护性能也各不相同，为了让大家能更好地了解和使用口罩产品，我们选取市面上常见的口罩产品，并针对口罩防护性能和佩戴舒适性进行了一次大盘点，总共20款。言归正传，现在就让我们开始吧！</p><p>普通医用口罩与类似产品，满足一般防护</p><p>目前普通用户家中最常见的要属医用口罩了，此类口罩大多采用内中外三层结构，其中外层为特殊材料抑菌层，中层为隔离过滤层，内层为亲肤材质无纺布或卫生纱布。</p><p>根据YY0469-2004《医用外科口罩技术要求》，普通医用口罩能够有效阻隔口腔和鼻腔呼出的喷溅物，适用于一般卫生护理活动，但对雾霾的防护性较弱。</p><p><img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5WiOeAcoO0AARrnY8aEAM779.jpg\" style=\"max-width:100%;\"></p>', '<img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5WiOeAcoO0AARrnY8aEAM779.jpg\" style=\"max-width:100%;\">', '2020-02-26 23:04:11', '1');
INSERT INTO `head_lines` VALUES ('5', '黑科技来袭，你见过能充电的手机壳吗？', '<span></span><p>&nbsp;现代社会已经有越来越多的人离不开手机了，最头疼的事儿莫过于手机电量告警，相信很多人为了避免这种担忧都会随身携带大容量移动电源、数据线、充电头，而且常常是一个零件忘带就把我们搞的头大。下面就给大家介绍一款充电神器—背夹移动电源，充电手机壳二合一，随时充电，摆脱线控，个性便携！</p><p><img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5ZL_qAblzmAABaOJ8ZHt4332.png\" style=\"max-width: 100%;\">&nbsp;&nbsp;</p>', '<img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5ZL_qAblzmAABaOJ8ZHt4332.png\" style=\"max-width: 100%;\">', '2020-02-28 23:21:32', '1');
INSERT INTO `head_lines` VALUES ('6', 'tws', '<span></span><p>sdfdsdf</p>', '', '2020-02-28 23:26:48', '0');

-- ----------------------------
-- Table structure for logistic_info
-- ----------------------------
DROP TABLE IF EXISTS `logistic_info`;
CREATE TABLE `logistic_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '物流信息主键',
  `company_name` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '承运公司',
  `order_number` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '订单编号',
  `courier_number` int(11) DEFAULT NULL COMMENT '快递单号',
  `logistic_time` datetime DEFAULT NULL COMMENT '物流时间',
  `logistic_place` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '物流地点',
  `logistic_explain` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '物流说明',
  `logistic_state` varchar(3) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '物流状态(1:已发货,2:已揽件,3:运输中,4:派送中,5:待取件,6:已签收)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='物流信息表';

-- ----------------------------
-- Records of logistic_info
-- ----------------------------

-- ----------------------------
-- Table structure for order_basics
-- ----------------------------
DROP TABLE IF EXISTS `order_basics`;
CREATE TABLE `order_basics` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单主键',
  `order_number` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL COMMENT '订单编号',
  `user_id` int(11) DEFAULT NULL COMMENT '下单用户',
  `receiver_name` varchar(50) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '收货人手机',
  `receiver_address` varchar(150) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '收货人地址',
  `total_money` decimal(22,2) DEFAULT NULL COMMENT '订单总价格',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `pay_id` int(11) DEFAULT NULL COMMENT '支付方式主键',
  `company_id` int(11) DEFAULT NULL COMMENT '物流公司主键',
  `state` varchar(5) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '订单状态（1:待支付;2：已付款,未发货;3:已发货,未收货;4:已收货,未评价;5:已评价;6:申请退货;7:退货中;8:退货成功;9:已取消）',
  `remarks` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_basics_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `person` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='订单基础表';

-- ----------------------------
-- Records of order_basics
-- ----------------------------
INSERT INTO `order_basics` VALUES ('5', '15849657890250000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '7688.00', '2020-03-23 20:16:29', '1', '1', '2', '');
INSERT INTO `order_basics` VALUES ('6', '15857524937210000002', '2', null, null, null, '2099.00', '2020-04-01 22:48:14', '3', '2', '5', null);
INSERT INTO `order_basics` VALUES ('7', '15885686891810000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '2074.00', '2020-05-04 13:04:49', '1', '1', '4', '');
INSERT INTO `order_basics` VALUES ('8', '15885728749020000003', '3', null, null, null, '15578.00', '2020-05-04 14:14:35', '3', '2', '5', null);
INSERT INTO `order_basics` VALUES ('9', '15885729909920000003', '3', null, null, null, '6199.00', '2020-05-04 14:16:31', '3', '2', '1', null);
INSERT INTO `order_basics` VALUES ('10', '15885732603510000003', '3', null, null, null, '175.00', '2020-05-04 14:21:00', '3', '2', '5', null);
INSERT INTO `order_basics` VALUES ('11', '15885831199420000002', '2', null, null, null, '3669.00', '2020-05-04 17:05:20', '2', '2', '9', null);
INSERT INTO `order_basics` VALUES ('12', '15885833134530000002', '2', null, null, null, '3669.00', '2020-05-04 17:08:33', '1', '3', '5', null);
INSERT INTO `order_basics` VALUES ('13', '15885833762790000002', '2', null, null, null, '3669.00', '2020-05-04 17:09:36', '3', '3', '1', null);
INSERT INTO `order_basics` VALUES ('14', '15885841516070000003', '3', null, null, null, '136.00', '2020-05-04 17:22:32', '2', '3', '5', null);
INSERT INTO `order_basics` VALUES ('15', '15885844763170000003', '3', null, null, null, '89.00', '2020-05-04 17:27:56', '2', '3', '1', null);
INSERT INTO `order_basics` VALUES ('16', '15885859435570000002', '2', null, null, null, '3599.00', '2020-05-04 17:52:24', '3', '4', '5', null);
INSERT INTO `order_basics` VALUES ('17', '15885866067010000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '159.00', '2020-05-04 18:03:27', '1', '1', '5', '');
INSERT INTO `order_basics` VALUES ('18', '15885867545650000002', '2', null, null, null, '6199.00', '2020-05-04 18:05:55', '2', '1', '1', null);
INSERT INTO `order_basics` VALUES ('19', '15885869786870000002', '2', null, null, null, '89.00', '2020-05-04 18:09:39', '1', '3', '1', null);
INSERT INTO `order_basics` VALUES ('20', '15885872392440000002', '2', null, null, null, '2798.00', '2020-05-04 18:13:59', '2', '4', '1', null);
INSERT INTO `order_basics` VALUES ('21', '15885875300310000002', '2', null, null, null, '3269.00', '2020-05-04 18:18:50', '2', '4', '1', null);
INSERT INTO `order_basics` VALUES ('22', '15885879274400000002', '2', null, null, null, '7299.00', '2020-05-04 18:25:27', '3', '5', '1', null);
INSERT INTO `order_basics` VALUES ('23', '15885880947270000002', '2', null, null, null, '156.00', '2020-05-04 18:28:15', '3', '5', '1', null);
INSERT INTO `order_basics` VALUES ('24', '15885888390020000002', '2', null, null, null, '148.00', '2020-05-04 18:40:39', '3', '5', '1', null);
INSERT INTO `order_basics` VALUES ('25', '15885989528390000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '2798.00', '2020-05-04 21:29:13', '1', '1', '1', '嘻嘻');
INSERT INTO `order_basics` VALUES ('26', '15885990641900000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '7299.00', '2020-05-04 21:31:04', '1', '1', '4', '解决');
INSERT INTO `order_basics` VALUES ('27', '15885993957040000003', '3', '李四', '13565456325', '广东省/茂名市/电白区 金兰花园', '156.00', '2020-05-04 21:36:36', '1', '1', '5', '嗯嗯');
INSERT INTO `order_basics` VALUES ('28', '15885999184380000003', '3', null, null, 'null null', '136.00', '2020-05-04 21:45:18', '2', '5', '1', '');
INSERT INTO `order_basics` VALUES ('29', '15886012625460000003', '3', null, null, 'null null', '175.00', '2020-05-04 22:07:43', '2', '2', '1', '快快快');
INSERT INTO `order_basics` VALUES ('30', '15886038728920000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '3669.00', '2020-05-04 22:51:13', '1', '1', '5', '快快快');
INSERT INTO `order_basics` VALUES ('31', '15886051398580000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '3669.00', '2020-05-04 23:12:20', '1', '1', '4', '嗯嗯嗯');
INSERT INTO `order_basics` VALUES ('32', '15886055399300000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '7299.00', '2020-05-04 23:19:00', '1', '1', '5', '去去去曲奇');
INSERT INTO `order_basics` VALUES ('33', '15886057313710000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '2798.00', '2020-05-04 23:22:11', '1', '1', '5', '噗噗噗噗噗');
INSERT INTO `order_basics` VALUES ('34', '15886058959220000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '7299.00', '2020-05-04 23:24:56', '1', '1', '5', '');
INSERT INTO `order_basics` VALUES ('35', '15886060301600000003', '3', '李四', '13565456325', '广东省/茂名市/电白区 金兰花园', '6199.00', '2020-05-04 23:27:10', '1', '1', '4', 'lkj来几个');
INSERT INTO `order_basics` VALUES ('36', '15886063312110000003', '3', '李四', '13565456325', '广东省/茂名市/电白区 金兰花园', '7789.00', '2020-05-04 23:32:11', '1', '1', '5', '我问的是');
INSERT INTO `order_basics` VALUES ('37', '15886065286480000003', '3', '李四', '13565456325', '广东省/茂名市/电白区 金兰花园', '7299.00', '2020-05-04 23:35:29', '1', '1', '5', '大幅度爽肤水');
INSERT INTO `order_basics` VALUES ('38', '15886073548080000003', '3', '李四', '13565456325', '广东省/茂名市/电白区 金兰花园', '6199.00', '2020-05-04 23:49:15', '1', '1', '4', '');
INSERT INTO `order_basics` VALUES ('39', '15886580167810000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '7299.00', '2020-05-05 13:53:37', '1', '1', '5', '快快快');
INSERT INTO `order_basics` VALUES ('40', '15893729596900000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '5789.00', '2020-05-13 20:29:20', '1', '1', '2', '');
INSERT INTO `order_basics` VALUES ('41', '15893733648300000002', '2', '李四', '13756545263', '广西壮族自治区/柳州市/柳南区 那零花园', '296.00', '2020-05-13 20:36:05', '1', '1', '3', '');
INSERT INTO `order_basics` VALUES ('42', '15895256175760000003', '3', '李四', '13565456325', '广东省/茂名市/电白区 金兰花园', '175.00', '2020-05-15 14:53:38', '1', '1', '2', '');
INSERT INTO `order_basics` VALUES ('43', '15895263398600000003', '3', '李四', '13565456325', '广东省/茂名市/电白区 金兰花园', '148.00', '2020-05-15 15:05:40', '1', '1', '3', '');
INSERT INTO `order_basics` VALUES ('44', '15895508834250000003', '3', '李四', '13565456325', '广东省/茂名市/电白区 金兰花园', '7299.00', '2020-05-15 21:54:43', '1', '1', '3', '');
INSERT INTO `order_basics` VALUES ('45', '15895529579970000003', '3', '李四', '13565456325', '广东省/茂名市/电白区 金兰花园', '175.00', '2020-05-15 22:29:18', '1', '1', '2', '');
INSERT INTO `order_basics` VALUES ('46', '15895955681610000002', '2', null, null, null, '7789.00', '2020-05-16 10:19:28', null, null, '1', null);
INSERT INTO `order_basics` VALUES ('47', '15895967627550000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '7789.00', '2020-05-16 10:39:23', '1', '1', '2', '');
INSERT INTO `order_basics` VALUES ('48', '15895974597930000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '15578.00', '2020-05-16 10:51:00', '1', '1', '2', '');
INSERT INTO `order_basics` VALUES ('49', '15896057947050000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '15578.00', '2020-05-16 13:09:55', '1', '1', '2', '');
INSERT INTO `order_basics` VALUES ('50', '15896177798880000003', '3', '李四', '13565456325', '广东省/茂名市/电白区 金兰花园', '7789.00', '2020-05-16 16:29:40', '1', '1', '2', '');
INSERT INTO `order_basics` VALUES ('51', '15896223296790000002', '2', '李兴', '13692548756', '广东省/广州市/白云区 金兰花苑11栋', '3669.00', '2020-05-16 17:45:30', '1', '1', '2', '');

-- ----------------------------
-- Table structure for order_items
-- ----------------------------
DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单明细主键',
  `product_name` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '商品名称',
  `producr_icon` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '商品图标',
  `product_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `product_price` decimal(10,2) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL COMMENT '订单编号',
  `specs_id` int(11) DEFAULT NULL COMMENT '规格主键',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_basics` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='订单明细表';

-- ----------------------------
-- Records of order_items
-- ----------------------------
INSERT INTO `order_items` VALUES ('3', 'i5-9300H 8G 512SSD GTX1650', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '1', '5789.00', '5', '3');
INSERT INTO `order_items` VALUES ('4', 'i5-9300H 8G 512SSD GTX1650', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '1', '1899.00', '5', '3');
INSERT INTO `order_items` VALUES ('5', '紫玉幻境 全网通4G(8G+256G)', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5dJQ6AEuuUAARp9zErVsA116.png', '1', '2099.00', '6', '2');
INSERT INTO `order_items` VALUES ('6', '深海微光 全网通4G(6G+128G)', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5dJQ6AEuuUAARp9zErVsA116.png', '1', '1899.00', '7', '1');
INSERT INTO `order_items` VALUES ('7', '2磅', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5o5jOAOdR4AASWsXi47yg191.png', '1', '175.00', '7', '13');
INSERT INTO `order_items` VALUES ('8', 'i7-9750H 16G 512SSD GTX1660Ti', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '2', '7789.00', '8', '4');
INSERT INTO `order_items` VALUES ('9', 'i5-9300H 8G 512G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '6199.00', '9', '16');
INSERT INTO `order_items` VALUES ('10', '2磅', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5o5jOAOdR4AASWsXi47yg191.png', '1', '175.00', '10', '13');
INSERT INTO `order_items` VALUES ('11', '全网通5G版（8G+128G）', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkDiAA1n3AALtsQhCZEg751.png', '1', '3669.00', '11', '6');
INSERT INTO `order_items` VALUES ('12', '全网通5G版（8G+128G）', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkDiAA1n3AALtsQhCZEg751.png', '1', '3669.00', '12', '6');
INSERT INTO `order_items` VALUES ('13', '全网通5G版（8G+128G）', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkDiAA1n3AALtsQhCZEg751.png', '1', '3669.00', '13', '6');
INSERT INTO `order_items` VALUES ('14', '水果味', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5osryAEqUHAAlmhvx0S3k521.png', '1', '136.00', '14', '15');
INSERT INTO `order_items` VALUES ('15', '明月蓝 L/175', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5osT-AFcGsAAeabu8_0wQ299.png', '1', '89.00', '15', '9');
INSERT INTO `order_items` VALUES ('16', '全网通（8G+256G）', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5pA-WAGij1AAUckLm_1AY686.png', '1', '3599.00', '16', '11');
INSERT INTO `order_items` VALUES ('17', '大红/深红色', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5b2gyAStllAAgj4BJIsu0063.jpg', '1', '159.00', '17', '19');
INSERT INTO `order_items` VALUES ('18', 'i5-9300H 8G 512G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '6199.00', '18', '16');
INSERT INTO `order_items` VALUES ('19', '基础黑 L/175', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5osT-AFcGsAAeabu8_0wQ299.png', '1', '89.00', '19', '7');
INSERT INTO `order_items` VALUES ('20', '全网通（6G+128G）', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5pA-WAGij1AAUckLm_1AY686.png', '1', '2798.00', '20', '10');
INSERT INTO `order_items` VALUES ('21', '全网通5G版', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkDiAA1n3AALtsQhCZEg751.png', '1', '3269.00', '21', '5');
INSERT INTO `order_items` VALUES ('22', 'i7-9750H 8G 512G GTX1660TiMQ 6G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '7299.00', '22', '17');
INSERT INTO `order_items` VALUES ('23', '1磅', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5o5jOAOdR4AASWsXi47yg191.png', '1', '156.00', '23', '12');
INSERT INTO `order_items` VALUES ('24', '短袖灰色+短裤黑色 L/175', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5osT-AFcGsAAeabu8_0wQ299.png', '1', '148.00', '24', '8');
INSERT INTO `order_items` VALUES ('25', '全网通（6G+128G）', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5pA-WAGij1AAUckLm_1AY686.png', '1', '2798.00', '25', '10');
INSERT INTO `order_items` VALUES ('26', 'i7-9750H 8G 512G GTX1660TiMQ 6G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '7299.00', '26', '17');
INSERT INTO `order_items` VALUES ('27', '1磅', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5o5jOAOdR4AASWsXi47yg191.png', '1', '156.00', '27', '12');
INSERT INTO `order_items` VALUES ('28', '水果味', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5osryAEqUHAAlmhvx0S3k521.png', '1', '136.00', '28', '15');
INSERT INTO `order_items` VALUES ('29', '2磅', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5o5jOAOdR4AASWsXi47yg191.png', '1', '175.00', '29', '13');
INSERT INTO `order_items` VALUES ('30', '全网通5G版（8G+128G）', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkDiAA1n3AALtsQhCZEg751.png', '1', '3669.00', '30', '6');
INSERT INTO `order_items` VALUES ('31', '全网通5G版（8G+128G）', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkDiAA1n3AALtsQhCZEg751.png', '1', '3669.00', '31', '6');
INSERT INTO `order_items` VALUES ('32', 'i7-9750H 8G 512G GTX1660TiMQ 6G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '7299.00', '32', '17');
INSERT INTO `order_items` VALUES ('33', '全网通（6G+128G）', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5pA-WAGij1AAUckLm_1AY686.png', '1', '2798.00', '33', '10');
INSERT INTO `order_items` VALUES ('34', 'i7-9750H 8G 512G GTX1660TiMQ 6G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '7299.00', '34', '17');
INSERT INTO `order_items` VALUES ('35', 'i5-9300H 8G 512G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '6199.00', '35', '16');
INSERT INTO `order_items` VALUES ('36', 'i7-9750H 16G 512SSD GTX1660Ti', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '1', '7789.00', '36', '4');
INSERT INTO `order_items` VALUES ('37', 'i7-9750H 8G 512G GTX1660TiMQ 6G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '7299.00', '37', '17');
INSERT INTO `order_items` VALUES ('38', 'i5-9300H 8G 512G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '6199.00', '38', '16');
INSERT INTO `order_items` VALUES ('39', 'i7-9750H 8G 512G GTX1660TiMQ 6G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '7299.00', '39', '17');
INSERT INTO `order_items` VALUES ('40', 'i5-9300H 8G 512SSD GTX1650', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '1', '5789.00', '40', '3');
INSERT INTO `order_items` VALUES ('41', '短袖灰色+短裤黑色 L/175', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5osT-AFcGsAAeabu8_0wQ299.png', '2', '148.00', '41', '8');
INSERT INTO `order_items` VALUES ('42', '2磅', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5o5jOAOdR4AASWsXi47yg191.png', '1', '175.00', '42', '13');
INSERT INTO `order_items` VALUES ('43', '短袖灰色+短裤黑色 L/175', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5osT-AFcGsAAeabu8_0wQ299.png', '1', '148.00', '43', '8');
INSERT INTO `order_items` VALUES ('44', 'i7-9750H 8G 512G GTX1660TiMQ 6G', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '1', '7299.00', '44', '17');
INSERT INTO `order_items` VALUES ('45', '2磅', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5o5jOAOdR4AASWsXi47yg191.png', '1', '175.00', '45', '13');
INSERT INTO `order_items` VALUES ('46', 'i7-9750H 16G 512SSD GTX1660Ti', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '1', '7789.00', '46', '4');
INSERT INTO `order_items` VALUES ('47', 'i7-9750H 16G 512SSD GTX1660Ti', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '1', '7789.00', '47', '4');
INSERT INTO `order_items` VALUES ('48', 'i7-9750H 16G 512SSD GTX1660Ti', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '2', '7789.00', '48', '4');
INSERT INTO `order_items` VALUES ('49', 'i7-9750H 16G 512SSD GTX1660Ti', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '2', '7789.00', '49', '4');
INSERT INTO `order_items` VALUES ('50', 'i7-9750H 16G 512SSD GTX1660Ti', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '1', '7789.00', '50', '4');
INSERT INTO `order_items` VALUES ('51', '全网通5G版（8G+128G）', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkDiAA1n3AALtsQhCZEg751.png', '1', '3669.00', '51', '6');

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付方式主键',
  `pay_name` varchar(100) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '支付名称',
  `pay_icon` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '支付图标',
  `add_time` datetime DEFAULT NULL COMMENT '上传时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` varchar(3) COLLATE utf8_general_mysql500_ci DEFAULT '1' COMMENT '状态（1：使用，0：停用）',
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='支付方式管理';

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES ('1', '支付宝', 'http://47.102.212.171/group1/M00/00/00/rBEaFF53EhSATLGyAADQOO_gqLs305.jpg', '2020-03-22 15:22:01', '2020-03-22 15:22:01', '1');
INSERT INTO `payment` VALUES ('2', '微信', 'http://47.102.212.171/group1/M00/00/01/rBEaFF53E-2AE-lZAACRgF4EF_A012.jpg', '2020-03-22 15:29:51', '2020-03-22 15:29:51', '1');
INSERT INTO `payment` VALUES ('3', '银行卡', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6-ONSARQm-AACmML4IUF8551.jpg', '2020-03-22 15:35:52', '2020-05-15 14:38:13', '1');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `user_name` varchar(100) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '用户昵称',
  `user_password` varchar(100) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '用户密码',
  `salt` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '用于加密的盐',
  `gender` int(2) DEFAULT '3' COMMENT '性别（1：男，2：女，3：未知）',
  `phone` varchar(20) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '邮箱',
  `birth` varchar(50) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '生日',
  `address` varchar(50) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '地址',
  `image_url` varchar(100) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '用户头像',
  `state` int(2) DEFAULT '1' COMMENT '状态（1：使用，2：注销）',
  `create_time` datetime DEFAULT NULL COMMENT '用户注册的时间',
  `update_time` datetime DEFAULT NULL COMMENT '用户最后一次登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='用户表';

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', null, '3', null, null, null, null, null, '1', null, '2020-03-04 17:02:12');
INSERT INTO `person` VALUES ('2', 'zhangsan2', 'e99a18c428cb38d5f260853678922e03', null, '2', '13654563652', '1185630400@qq.com', '1998-01-13', null, 'http://47.102.212.171/group1/M00/00/00/rBEaFF5sZgqAe2UdAAVzzB-3Lm0389.jpg', '1', null, '2020-03-14 13:10:09');
INSERT INTO `person` VALUES ('3', 'lisi', 'e99a18c428cb38d5f260853678922e03', null, '3', null, null, null, null, null, '1', null, null);
INSERT INTO `person` VALUES ('4', 'wangwu', 'e99a18c428cb38d5f260853678922e03', null, '3', null, null, null, null, null, '1', null, null);
INSERT INTO `person` VALUES ('5', 'zhaoliu', 'e99a18c428cb38d5f260853678922e03', null, '3', null, null, null, null, null, '1', null, null);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品主键',
  `product_name` varchar(50) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '商品名称',
  `product_one_image` varchar(100) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '商品首页显示的图片',
  `images` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '详情里面的图片，逗号隔开',
  `shop_price` decimal(10,2) DEFAULT NULL COMMENT '商城价格，首页显示',
  `product_detail` text COLLATE utf8_general_mysql500_ci COMMENT '商品详情',
  `product_state` int(2) DEFAULT NULL COMMENT '商品状态（1：上架，0：下架）',
  `update_time` datetime DEFAULT NULL COMMENT '商品更新时间',
  `three_id` int(11) DEFAULT NULL COMMENT '商品所属分类',
  PRIMARY KEY (`product_id`),
  KEY `three_id` (`three_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`three_id`) REFERENCES `catalog_three` (`three_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='商品表，首页显示';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '小米 Redmi 红米K30', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5dJQ6AEuuUAARp9zErVsA116.png', '', '1378.00', '<span><span><span></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\"><ul id=\"parameter-brand\"><li title=\"小米（MI）\">品牌：&nbsp;<a href=\"https://list.jd.com/list.html?cat=9987,653,655&amp;ev=exbrand_18374\" clstag=\"shangpin|keycount|product|pinpai_1\" target=\"_blank\">小米（MI）</a></li></ul><ul><li title=\"小米 Redmi 红米K30 王一博同款手机 深海微光 4G版 全网通6G+128G\">商品名称：小米 Redmi 红米K30 王一博同款手机 深海微光 4G版 全网通6G+128G</li><li title=\"63115799361\">商品编号：63115799361</li><li title=\"1.0kg\">商品毛重：1.0kg</li><li title=\"骁龙730G\">CPU型号：骁龙730G</li><li title=\"6GB\">运行内存：6GB</li><li title=\"其它存储\">机身存储：其它存储</li><li title=\"支持MicroSD(TF)\">存储卡：支持MicroSD(TF)</li><li title=\"后置四摄\">摄像头数量：后置四摄</li><li title=\"6400万像素\">后摄主摄像素：6400万像素</li><li title=\"2000万像素\">前摄主摄像素：2000万像素</li><li title=\"6.67英寸\">主屏幕尺寸（英寸）：6.67英寸</li><li title=\"其它分辨率\">分辨率：其它分辨率</li><li title=\"其他\">屏幕前摄组合：其他</li><li title=\"4500mAh（typ）\">电池容量（mAh）：4500mAh（typ）</li><li title=\"其他\">充电器：其他</li><li title=\"蓝色\">机身颜色：蓝色</li><li title=\"Android(安卓)\">操作系统：Android(安卓)</li></ul></span></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\"><p><br></p>,', '0', '2020-03-02 23:23:57', '2');
INSERT INTO `product` VALUES ('2', '跑步鞋', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5b2gyAStllAAgj4BJIsu0063.jpg', '', '125.00', '<span></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\" value=\"\"><p>的梵蒂冈电饭锅电饭锅地方水电费地方地方</p>', '1', '2020-03-01 23:51:40', '9');
INSERT INTO `product` VALUES ('3', '华硕(ASUS) 飞行堡垒7', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5ntmKAHTELAAXgj1BHumk978.png', '', '6489.00', '<span></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\"><ul id=\"parameter-brand\"><li title=\"华硕（ASUS）\">品牌：&nbsp;<a href=\"https://list.jd.com/list.html?cat=670,671,1105&amp;ev=exbrand_8551\" clstag=\"shangpin|keycount|product|pinpai_1\" target=\"_blank\">华硕（ASUS）</a></li></ul><ul><li title=\"华硕飞行堡垒7\">商品名称：华硕飞行堡垒7</li><li title=\"100005011366\">商品编号：100005011366</li><li title=\"3.46kg\">商品毛重：3.46kg</li><li title=\"中国大陆\">商品产地：中国大陆</li><li title=\"Windows 10\">系统：Windows 10</li><li title=\"120HZ\">刷新率：120HZ</li><li title=\"15.6英寸\">屏幕尺寸：15.6英寸</li><li title=\"8G\">内存容量：8G</li><li title=\"小于5小时\">待机时长：小于5小时</li><li title=\"发烧级\">游戏性能：发烧级</li><li title=\"GTX1650\">显卡型号：GTX1650</li><li title=\"Intel i7标准电压版\">处理器：Intel i7标准电压版</li><li title=\"其他\">特性：其他</li><li title=\"华硕-飞行堡垒系列\">系列：华硕-飞行堡垒系列</li><li title=\"2-2.5kg\">裸机重量：2-2.5kg</li><li title=\"一年质保，7*24H咨询\">优选服务：一年质保，7*24H咨询</li><li title=\"4G\">显存容量：4G</li><li title=\"512GB SSD\">硬盘容量：512GB SSD</li><li title=\"25.0mm以上\">厚度：25.0mm以上</li></ul>', '1', '2020-03-10 23:46:42', '14');
INSERT INTO `product` VALUES ('4', '戴尔DELL游匣G3', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5orceAIzBTAAVpZo5_ipQ419.png', '', '5889.00', '<span></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\"><ul id=\"parameter-brand\"><li title=\"戴尔（DELL）\">品牌：&nbsp;<a href=\"https://list.jd.com/list.html?cat=670,671,1105&amp;ev=exbrand_5821\" clstag=\"shangpin|keycount|product|pinpai_1\" target=\"_blank\">戴尔（DELL）</a></li></ul><ul><li title=\"戴尔G3\">商品名称：戴尔G3</li><li title=\"100005724680\">商品编号：100005724680</li><li title=\"3.42kg\">商品毛重：3.42kg</li><li title=\"中国大陆\">商品产地：中国大陆</li><li title=\"Windows 10\">系统：Windows 10</li><li title=\"GTX1650\">显卡型号：GTX1650</li><li title=\"15.6英寸\">屏幕尺寸：15.6英寸</li><li title=\"512GB SSD\">硬盘容量：512GB SSD</li><li title=\"9小时以上\">待机时长：9小时以上</li><li title=\"骨灰级\">游戏性能：骨灰级</li><li title=\"72%NTSC\">色域：72%NTSC</li><li title=\"Intel i5标准电压版\">处理器：Intel i5标准电压版</li><li title=\"PCIE高速固态硬盘\">特性：PCIE高速固态硬盘</li><li title=\"戴尔 - 游匣\">系列：戴尔 - 游匣</li><li title=\"2-2.5kg\">裸机重量：2-2.5kg</li><li title=\"两年质保\">优选服务：两年质保</li><li title=\"4G\">显存容量：4G</li><li title=\"8G\">内存容量：8G</li><li title=\"20.0mm—25.0mm\">厚度：20.0mm—25.0mm</li></ul>', '1', '2020-03-11 17:22:16', '16');
INSERT INTO `product` VALUES ('5', '安踏运动套装男春季', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5osT-AFcGsAAeabu8_0wQ299.png', '', '89.90', '<span><span></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\"><ul><li title=\"安踏运动套装男春季外套短裤跑步健身训练足球速干衣薄款篮球运动服男装官方短袖t恤旗舰 -4基础黑 L/175\">商品名称：安踏运动套装男春季外套短裤跑步健身训练足球速干衣薄款篮球运动服男装官方短袖t恤旗舰 -4基础黑 L/175</li><li title=\"11324942647\">商品编号：11324942647</li><li title=\"宏义运动专营店\">店铺：&nbsp;<a href=\"https://hongyi-yd.jd.com/\" target=\"_blank\">宏义运动专营店</a></li><li title=\"500.00g\">商品毛重：500.00g</li><li title=\"中国大陆\">商品产地：中国大陆</li><li title=\"安踏套装\">货号：安踏套装</li><li title=\"运动休闲，跑步，综合训练\">适用运动：运动休闲，跑步，综合训练</li><li title=\"男士\">适用人群：男士</li><li title=\"其它运动套装\">类别：其它运动套装</li><li title=\"圆领\">领型：圆领</li><li title=\"品牌LOGO，图案，字母\">流行元素：品牌LOGO，图案，字母</li><li title=\"常规\">上装衣长：常规</li><li title=\"白色，蓝色，黑色\">颜色：白色，蓝色，黑色</li><li title=\"涤纶，棉，聚酯纤维\">材质：涤纶，棉，聚酯纤维</li><li title=\"短裤\">下装款式：短裤</li><li title=\"短袖\">上装款式：短袖</li><li title=\"短袖\">袖长：短袖</li><li title=\"四季\">适用季节：四季</li><li title=\"速干，超轻，透气\">功能：速干，超轻，透气</li><li title=\"套头\">款式：套头</li><li title=\"2020年春季\">上市时间：2020年春季</li></ul></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\"><p><br></p>,', '1', '2020-03-11 23:30:18', '10');
INSERT INTO `product` VALUES ('6', '慕斯抹茶芝士提拉米苏味', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5osryAEqUHAAlmhvx0S3k521.png', '<img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5osreAN-E1AAYRvQHqqD0934.png\" style=\"max-width:100%;\">', '136.00', '<span></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\"><ul><li title=\"生日蛋糕巧克力慕斯抹茶芝士提拉米苏味送女友冷冻蛋糕北京上海广州西安沈阳武汉同城配送 十全十美（镇店款） 8寸\">商品名称：生日蛋糕巧克力慕斯抹茶芝士提拉米苏味送女友冷冻蛋糕北京上海广州西安沈阳武汉同城配送 十全十美（镇店款） 8寸</li><li title=\"29840851963\">商品编号：29840851963</li><li title=\"果果香食品专营店\">店铺：&nbsp;<a href=\"https://ggxsp.jd.com/\" target=\"_blank\">果果香食品专营店</a></li><li title=\"0.85kg\">商品毛重：0.85kg</li><li title=\"蛋糕\">类别：蛋糕</li><li title=\"原味\">口味：原味</li><li title=\"添加糖\">是否添加糖：添加糖</li><li title=\"礼盒装\">包装：礼盒装</li><li title=\"礼盒装\"><img src=\"http://47.102.212.171/group1/M00/00/00/rBEaFF5osreAN-E1AAYRvQHqqD0934.png\" style=\"max-width:100%;\"></li></ul>', '1', '2020-03-11 17:43:25', '7');
INSERT INTO `product` VALUES ('7', '双莓慕斯+草莓', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5o5jOAOdR4AASWsXi47yg191.png', '', '300.00', null, '1', '2020-03-11 21:22:59', '7');
INSERT INTO `product` VALUES ('8', '华为 nova6 SE', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkDiAA1n3AALtsQhCZEg751.png', '', '2199.50', '<span></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\"><ul><li title=\"华为（HUAWEI）华为nova6 5G版\">商品名称：华为（HUAWEI）华为nova6 5G版</li><li title=\"61659649129\">商品编号：61659649129</li><li title=\"500.00g\">商品毛重：500.00g</li><li title=\"其他\">CPU型号：其他</li><li title=\"其它内存\">运行内存：其它内存</li><li title=\"128GB\">机身存储：128GB</li><li title=\"不支持存储卡\">存储卡：不支持存储卡</li><li title=\"其他\">摄像头数量：其他</li><li title=\"4000万像素\">后摄主摄像素：4000万像素</li><li title=\"3200万像素\">前摄主摄像素：3200万像素</li><li title=\"其他英寸\">主屏幕尺寸（英寸）：其他英寸</li><li title=\"全高清FHD+\">分辨率：全高清FHD+</li><li title=\"其它屏幕比例\">屏幕比例：其它屏幕比例</li><li title=\"其他\">屏幕前摄组合：其他</li><li title=\"10V/4A\">充电器：10V/4A</li><li title=\"Android(安卓)\">操作系统：Android(安卓)</li></ul>', '1', '2020-04-12 11:51:20', '1');
INSERT INTO `product` VALUES ('9', '魅族 16s Pro', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5pA-WAGij1AAUckLm_1AY686.png', '', '2989.00', '<span></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\"><ul><li title=\"魅族16s Pro\">商品名称：魅族16s Pro</li><li title=\"100007765500\">商品编号：100007765500</li><li title=\"445.00g\">商品毛重：445.00g</li><li title=\"中国大陆\">商品产地：中国大陆</li><li title=\"骁龙855plus\">CPU型号：骁龙855plus</li><li title=\"8GB\">运行内存：8GB</li><li title=\"128GB\">机身存储：128GB</li><li title=\"不支持存储卡\">存储卡：不支持存储卡</li><li title=\"后置三摄\">摄像头数量：后置三摄</li><li title=\"4800万像素\">后摄主摄像素：4800万像素</li><li title=\"2000万像素\">前摄主摄像素：2000万像素</li><li title=\"后置三摄，光学防抖\">拍照特点：后置三摄，光学防抖</li><li title=\"6.2英寸\">主屏幕尺寸（英寸）：6.2英寸</li><li title=\"其它分辨率\">分辨率：其它分辨率</li><li title=\"其它屏幕比例\">屏幕比例：其它屏幕比例</li><li title=\"其他\">屏幕前摄组合：其他</li><li title=\"3600\">电池容量（mAh）：3600</li><li title=\"其他\">充电器：其他</li><li title=\"白色物语\">机身颜色：白色物语</li><li title=\"屏幕指纹，NFC\">热点：屏幕指纹，NFC</li><li title=\"其他\">游戏性能：其他</li><li title=\"VOLTE 4G通话\">4G LTE网络特性：VOLTE 4G通话</li><li title=\"游戏性能模式\">游戏配置：游戏性能模式</li><li title=\"Android(安卓)\">操作系统：Android(安卓)</li><li title=\"20-26W\">充电功率：20-26W</li><li title=\"超大字体\">特殊功能：超大字体</li><li title=\"≥91%\">屏占比：≥91%</li></ul>', '1', '2020-03-11 23:29:41', '3');
INSERT INTO `product` VALUES ('10', '测试', 'http://47.102.212.171/group1/M00/00/01/rBEaFF676oCAPC4-AAKtslAi7OQ509.jpg', '', '25.00', '<span></span><input type=\"hidden\" id=\"productDetail\" name=\"productDetail\"><p>测试</p>', '1', '2020-05-13 20:39:28', '6');

-- ----------------------------
-- Table structure for product_collect
-- ----------------------------
DROP TABLE IF EXISTS `product_collect`;
CREATE TABLE `product_collect` (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏夹主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户主键',
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `state` int(3) DEFAULT '1' COMMENT '是否失效（1：否，0：是）',
  PRIMARY KEY (`collect_id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_collect_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `person` (`user_id`),
  CONSTRAINT `product_collect_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='商品收藏';

-- ----------------------------
-- Records of product_collect
-- ----------------------------
INSERT INTO `product_collect` VALUES ('2', '2', '3', '2020-04-05 22:46:58', '1');
INSERT INTO `product_collect` VALUES ('5', '2', '5', '2020-04-12 12:18:20', '1');

-- ----------------------------
-- Table structure for product_image
-- ----------------------------
DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `image_name` varchar(200) DEFAULT NULL COMMENT '图片名称',
  `image_url` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `state` int(2) DEFAULT '1' COMMENT '图片状态（1：正常，0：抛弃）',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

-- ----------------------------
-- Records of product_image
-- ----------------------------
INSERT INTO `product_image` VALUES ('1', '1', '正面', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5dJbaAJ9Q2AAH-WHiNYBc917.png', '1', '2020-03-02 23:26:53');
INSERT INTO `product_image` VALUES ('157', '1', '侧面', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5dJeGAfa99AACLcGejZ7g452.png', '1', '2020-03-02 23:27:30');
INSERT INTO `product_image` VALUES ('158', '1', '正反对比', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5dJf6AdPNEAALVTMvzAKg809.png', '1', '2020-03-02 23:28:01');
INSERT INTO `product_image` VALUES ('159', '4', '左侧', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5or6GAV_w6AAUI_iBzpWo507.png', '1', '2020-03-11 17:30:14');
INSERT INTO `product_image` VALUES ('160', '4', '正面', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5or7KAfhEMAAJKB-y21mk127.png', '1', '2020-03-11 17:30:33');
INSERT INTO `product_image` VALUES ('161', '4', '背面', 'http://47.102.212.171/group1/M00/00/00/rBEaFF5or7-AGK-SAAG3igqk-To050.png', '1', '2020-03-11 17:30:44');
INSERT INTO `product_image` VALUES ('162', '8', '正面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkLmARDVVAALtsQhCZEg189.png', '1', '2020-04-12 11:53:30');
INSERT INTO `product_image` VALUES ('163', '8', '介绍', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkMyAN5DfAATdRdPipko891.png', '1', '2020-04-12 11:53:50');
INSERT INTO `product_image` VALUES ('164', '8', '侧面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkNqABxmgAAXk8Htl7bg835.png', '1', '2020-04-12 11:54:04');
INSERT INTO `product_image` VALUES ('165', '8', '右侧', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkOuAW_l6AAR_Wm1bgI4572.png', '1', '2020-04-12 11:54:20');
INSERT INTO `product_image` VALUES ('166', '5', '前', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkgqAWL6_AAgMdctJFmM054.png', '1', '2020-04-12 11:59:08');
INSERT INTO `product_image` VALUES ('167', '5', '侧面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkhuAO2SoAAeabu8_0wQ636.png', '1', '2020-04-12 11:59:24');
INSERT INTO `product_image` VALUES ('168', '5', '背面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkiaAK6nJAAPTGJxbNpk770.png', '1', '2020-04-12 11:59:35');
INSERT INTO `product_image` VALUES ('169', '9', '正面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6Skj-AUdIdAAOWFtqn0TA206.png', '1', '2020-04-12 12:00:01');
INSERT INTO `product_image` VALUES ('170', '9', '正反', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkkyATuVkAAUckLm_1AY963.png', '1', '2020-04-12 12:00:13');
INSERT INTO `product_image` VALUES ('171', '9', '侧面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SklqADFYOAAGOpDlzci8187.png', '1', '2020-04-12 12:00:27');
INSERT INTO `product_image` VALUES ('172', '7', '正面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SksqAWoDYAASWsXi47yg372.png', '1', '2020-04-12 12:02:24');
INSERT INTO `product_image` VALUES ('173', '7', '块', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SktmAdG5RAATCO073eDE109.png', '1', '2020-04-12 12:02:39');
INSERT INTO `product_image` VALUES ('174', '6', '上面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkxqAWJWWAAYRvQHqqD0611.png', '1', '2020-04-12 12:03:44');
INSERT INTO `product_image` VALUES ('175', '6', '正面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SkyiALZPRAAkU02s4Xio889.png', '1', '2020-04-12 12:03:57');
INSERT INTO `product_image` VALUES ('176', '2', '正面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SlJOAGl_0AAXzJU4l0ao316.png', '1', '2020-04-12 12:09:59');
INSERT INTO `product_image` VALUES ('177', '2', '后面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SlJ6AMtEqAAelQnonDTA014.png', '1', '2020-04-12 12:10:11');
INSERT INTO `product_image` VALUES ('178', '2', '侧面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF6SlKqAUK_cAAcE-ZFcvEA428.png', '1', '2020-04-12 12:10:22');
INSERT INTO `product_image` VALUES ('179', '10', '正面', 'http://47.102.212.171/group1/M00/00/01/rBEaFF676pCAKTDHAAF9uvxFmGE025.jpg', '1', '2020-05-13 20:39:46');
INSERT INTO `product_image` VALUES ('180', '10', '散装', 'http://47.102.212.171/group1/M00/00/01/rBEaFF676p-AW0V5AAJYZYSdbCQ777.jpg', '1', '2020-05-13 20:40:01');

-- ----------------------------
-- Table structure for product_specs
-- ----------------------------
DROP TABLE IF EXISTS `product_specs`;
CREATE TABLE `product_specs` (
  `specs_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格主键',
  `specs_name` varchar(100) COLLATE utf8_general_mysql500_ci DEFAULT NULL COMMENT '规格名称',
  `specs_price` decimal(10,2) DEFAULT NULL COMMENT '规格价格',
  `specs_stock` int(11) DEFAULT '0' COMMENT '规格库存',
  `product_id` int(11) DEFAULT NULL COMMENT '所属商品',
  `state` int(11) DEFAULT '1' COMMENT '状态（1：正常，0：物理删除）',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`specs_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_specs_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci COMMENT='商品规格表，商品详情可选择';

-- ----------------------------
-- Records of product_specs
-- ----------------------------
INSERT INTO `product_specs` VALUES ('1', '深海微光 全网通4G(6G+128G)', '1899.00', '120', '1', '1', '2020-03-03 21:50:54');
INSERT INTO `product_specs` VALUES ('2', '紫玉幻境 全网通4G(8G+256G)', '2099.00', '2', '1', '1', '2020-03-03 21:51:04');
INSERT INTO `product_specs` VALUES ('3', 'i5-9300H 8G 512SSD GTX1650', '5789.00', '1040', '3', '1', '2020-05-16 09:13:40');
INSERT INTO `product_specs` VALUES ('4', 'i7-9750H 16G 512SSD GTX1660Ti', '7789.00', '995', '3', '1', '2020-03-10 23:48:13');
INSERT INTO `product_specs` VALUES ('5', '全网通5G版', '3269.00', '2503', '8', '1', '2020-05-16 09:12:31');
INSERT INTO `product_specs` VALUES ('6', '全网通5G版（8G+128G）', '3669.00', '999', '8', '1', '2020-04-12 11:52:49');
INSERT INTO `product_specs` VALUES ('7', '基础黑 L/175', '89.00', '1000', '5', '1', '2020-04-12 11:57:00');
INSERT INTO `product_specs` VALUES ('8', '短袖灰色+短裤黑色 L/175', '148.00', '100', '5', '1', '2020-04-12 11:57:23');
INSERT INTO `product_specs` VALUES ('9', '明月蓝 L/175', '89.00', '200', '5', '1', '2020-04-12 11:57:45');
INSERT INTO `product_specs` VALUES ('10', '全网通（6G+128G）', '2798.00', '114', '9', '1', '2020-05-16 09:12:48');
INSERT INTO `product_specs` VALUES ('11', '全网通（8G+256G）', '3599.00', '100', '9', '1', '2020-04-12 12:01:48');
INSERT INTO `product_specs` VALUES ('12', '1磅', '156.00', '170', '7', '1', '2020-05-16 09:13:04');
INSERT INTO `product_specs` VALUES ('13', '2磅', '175.00', '100', '7', '1', '2020-04-12 12:03:20');
INSERT INTO `product_specs` VALUES ('14', '1.5磅', '123.00', '410', '6', '1', '2020-05-16 09:13:22');
INSERT INTO `product_specs` VALUES ('15', '水果味', '136.00', '100', '6', '1', '2020-04-12 12:04:37');
INSERT INTO `product_specs` VALUES ('16', 'i5-9300H 8G 512G', '6199.00', '100', '4', '1', '2020-04-12 12:06:18');
INSERT INTO `product_specs` VALUES ('17', 'i7-9750H 8G 512G GTX1660TiMQ 6G', '7299.00', '100', '4', '1', '2020-04-12 12:06:45');
INSERT INTO `product_specs` VALUES ('18', '正黑/浅灰', '125.00', '25', '2', '1', '2020-05-16 09:12:03');
INSERT INTO `product_specs` VALUES ('19', '大红/深红色', '159.00', '100', '2', '1', '2020-04-12 12:08:14');
INSERT INTO `product_specs` VALUES ('20', '夹心', '25.00', '12', '10', '1', '2020-05-13 20:40:45');
INSERT INTO `product_specs` VALUES ('21', '测试2', '36.00', '10', '10', '1', '2020-05-13 20:41:57');
