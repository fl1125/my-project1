/*
MySQL Data Transfer
Source Host: localhost
Source Database: gymshoesbbs_db
Target Host: localhost
Target Database: gymshoesbbs_db
Date: 2019-05-06 16:55:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for banzhu
-- ----------------------------
DROP TABLE IF EXISTS `banzhu`;
CREATE TABLE `banzhu` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL,
  `fid` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bbstype
-- ----------------------------
DROP TABLE IF EXISTS `bbstype`;
CREATE TABLE `bbstype` (
  `id` int(11) NOT NULL auto_increment,
  `typename` varchar(255) default NULL,
  `fatherid` varchar(255) default NULL,
  `delstatus` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bzapplyrecord
-- ----------------------------
DROP TABLE IF EXISTS `bzapplyrecord`;
CREATE TABLE `bzapplyrecord` (
  `id` int(11) NOT NULL auto_increment,
  `fid` int(255) default NULL,
  `note` text,
  `memberid` int(255) default NULL,
  `shstatus` varchar(255) default NULL,
  `savetime` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` int(20) NOT NULL auto_increment,
  `msg` text,
  `hfmsg` text,
  `savetime` varchar(255) default NULL,
  `memberid` int(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fans
-- ----------------------------
DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL,
  `gzmemberid` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for infos
-- ----------------------------
DROP TABLE IF EXISTS `infos`;
CREATE TABLE `infos` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `note` text,
  `savetime` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for infospj
-- ----------------------------
DROP TABLE IF EXISTS `infospj`;
CREATE TABLE `infospj` (
  `id` int(11) NOT NULL auto_increment,
  `infoid` int(255) default NULL,
  `mid` int(255) default NULL,
  `note` text,
  `savetime` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jbrecord
-- ----------------------------
DROP TABLE IF EXISTS `jbrecord`;
CREATE TABLE `jbrecord` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(255) default NULL,
  `jbmemberid` int(255) default NULL,
  `note` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(20) NOT NULL auto_increment,
  `uname` varchar(255) default NULL,
  `upass` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `tname` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `addr` varchar(255) default NULL,
  `qq` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  `delstatus` varchar(255) default NULL,
  `savetime` varchar(255) default NULL,
  `isjy` varchar(255) default NULL,
  `filename` varchar(255) default NULL,
  `isfh` varchar(255) default NULL,
  `shstatus` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mgword
-- ----------------------------
DROP TABLE IF EXISTS `mgword`;
CREATE TABLE `mgword` (
  `id` int(11) NOT NULL auto_increment,
  `wordnote` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(20) NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `content` text,
  `filename` varchar(255) default NULL,
  `savetime` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pbinfo
-- ----------------------------
DROP TABLE IF EXISTS `pbinfo`;
CREATE TABLE `pbinfo` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(255) default NULL,
  `pbmemberid` int(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for signin
-- ----------------------------
DROP TABLE IF EXISTS `signin`;
CREATE TABLE `signin` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL,
  `savetime` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sxinfo
-- ----------------------------
DROP TABLE IF EXISTS `sxinfo`;
CREATE TABLE `sxinfo` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(255) default NULL,
  `sxmemberid` int(255) default NULL,
  `note` text,
  `hfnote` text,
  `savetime` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tzhtinfo
-- ----------------------------
DROP TABLE IF EXISTS `tzhtinfo`;
CREATE TABLE `tzhtinfo` (
  `id` int(11) NOT NULL auto_increment,
  `tzid` int(255) default NULL,
  `note` text,
  `author` int(255) default NULL,
  `savetime` varchar(255) default NULL,
  `dznum` int(11) default NULL,
  `canht` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tzinfo
-- ----------------------------
DROP TABLE IF EXISTS `tzinfo`;
CREATE TABLE `tzinfo` (
  `id` int(11) NOT NULL auto_increment,
  `fid` int(255) default NULL,
  `sid` int(255) default NULL,
  `title` varchar(255) default NULL,
  `note` text,
  `author` int(11) default NULL,
  `savetime` varchar(255) default NULL,
  `dznum` int(11) default NULL,
  `looknum` int(11) default NULL,
  `isjh` varchar(255) default NULL,
  `istop` varchar(255) default NULL,
  `updatetime` varchar(255) default NULL,
  `canht` varchar(255) default NULL,
  `filename` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL auto_increment,
  `usertype` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `userpassword` varchar(255) default NULL,
  `realname` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `addr` varchar(255) default NULL,
  `delstatus` varchar(255) default NULL,
  `savetime` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yqlink
-- ----------------------------
DROP TABLE IF EXISTS `yqlink`;
CREATE TABLE `yqlink` (
  `id` int(20) NOT NULL auto_increment,
  `linkname` varchar(255) default NULL,
  `linkurl` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `banzhu` VALUES ('5', '41', '24');
INSERT INTO `bbstype` VALUES ('24', '装备交流区', '0', '0');
INSERT INTO `bbstype` VALUES ('25', '求助问答', '24', '0');
INSERT INTO `bbstype` VALUES ('26', '足球鞋综合讨论', '24', '0');
INSERT INTO `bbstype` VALUES ('27', '球衣装备综合讨论', '24', '0');
INSERT INTO `bbstype` VALUES ('31', '互动话题区', '0', '0');
INSERT INTO `bbstype` VALUES ('32', '足球健身与训练', '31', '0');
INSERT INTO `bbstype` VALUES ('33', '交易服务区', '0', '0');
INSERT INTO `bbstype` VALUES ('34', '足球装备团购', '33', '0');
INSERT INTO `bbstype` VALUES ('35', '品牌专区', '0', '0');
INSERT INTO `bbstype` VALUES ('36', '美津浓MUZINO', '35', '0');
INSERT INTO `bbstype` VALUES ('37', '灌水区', '0', '0');
INSERT INTO `bbstype` VALUES ('38', '大厅', '37', '0');
INSERT INTO `bbstype` VALUES ('39', '足球话题', '31', '0');
INSERT INTO `bbstype` VALUES ('40', '足球装备交易', '33', '0');
INSERT INTO `bbstype` VALUES ('41', '求购与交换', '33', '0');
INSERT INTO `bbstype` VALUES ('42', '安踏足球专区', '35', '0');
INSERT INTO `bzapplyrecord` VALUES ('6', '24', '我要当版主', '41', '通过', '2019-05-06 14:39:38');
INSERT INTO `chat` VALUES ('3', '管理员你好', '你好', '2019-04-25 00:00:34', '41');
INSERT INTO `chat` VALUES ('4', '啊啊啊', '123123', '2019-05-06 14:47:18', '42');
INSERT INTO `fans` VALUES ('4', '41', '40');
INSERT INTO `fans` VALUES ('5', '41', '42');
INSERT INTO `infos` VALUES ('12', '反重力方向，更适合飞翔——AIR JORDAN XXXI 测评', '<p>\r\n	<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">&nbsp; &nbsp; &nbsp; 回顾 Michael Jordan 亲着黑红配色 Air Jordan 1 “Bred” 腾飞扣篮的画面，这极具爆发力和美感的瞬间，也似乎注入到 XXXI 之中，让人回味无穷。加上 Swoosh 到飞人标志的渐变，以及鞋帮内侧的 Air Jordan 飞翼标志，这一份致敬之意再明显不过了！</span>\r\n</p>\r\n<p style=\"text-align:center;\">\r\n	<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\"><img src=\"/gymshoesbbs/attached/image/20190506/20190506144349_669.jpeg\" alt=\"\" /><br />\r\n</span>\r\n</p>\r\n<p style=\"text-align:left;\">\r\n	<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">\r\n	<p style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">\r\n		&nbsp; &nbsp; &nbsp; Air Jordan 正代球鞋毕竟是定位于实战，在经历了只有前掌气垫的 XX9 和 XXX 后，XXXI 再度带来全掌 Zoom Air 气垫的完美配备，对于球场实战性能的支持，显然是满分！\r\n	</p>\r\n	<p style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">\r\n		本次还采纳了 Michael Jordan 乔帮主本人的意见，Flight Speed 中底结合 Zoom Air 全掌气垫，以更为贴近地面的设计呈现，在 “贴地飞行” 的视觉效果之外，带来的是更低的重心，更佳的反馈性和横向支撑性能。<br />\r\n&nbsp; &nbsp; &nbsp; 这一份鞋底配置，早已让我忍不住有赶紧上脚一试的冲动！\r\n	</p>\r\n	<p style=\"text-align:center;color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">\r\n		<img src=\"/gymshoesbbs/attached/image/20190506/20190506144416_591.jpeg\" alt=\"\" />\r\n	</p>\r\n	<p style=\"text-align:left;color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">\r\n		<p style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">\r\n			&nbsp; &nbsp; &nbsp; 从1986年的AJ2代开始AIR JORDAN就逐渐淡化SWOOSH（勾子）的形象，直到1992年的AIR JORDAN 7，AIR JORDAN彻底在鞋身上取消了SWOOSH（勾子）的形象，经过多年的迭代，AIR JORDAN作为NIKE的高端子品牌深深的印在了鞋迷的脑海中。AIR JORDAN与NIKE的关系就好比“雷克萨斯”与“丰田”，“奥迪”与“大众”。\r\n		</p>\r\n		<p style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">\r\n			&nbsp; &nbsp; &nbsp; AIR JORDAN 1发售于1985年，时隔多年后的AIR JORDAN 31是对于经典的传承与延续，更多的吸取了AIR JORDAN 1的设计元素以及创新科技的灵活运用。\r\n		</p>\r\n	</p>\r\n<br />\r\n</span>\r\n</p>', '2019-05-06 14:44:29', '2');
INSERT INTO `infos` VALUES ('13', '测试的', '123123123', '2019-05-06 14:46:55', '1');
INSERT INTO `infospj` VALUES ('8', '12', '42', '不错，期待开售', '2019-05-06 14:44:48');
INSERT INTO `infospj` VALUES ('9', '13', '42', '<img src=\"http://localhost:8080/gymshoesbbs/kindeditor/plugins/emoticons/images/20.gif\" border=\"0\" alt=\"\" />', '2019-05-06 14:47:02');
INSERT INTO `member` VALUES ('35', 'xm', '123', 'songxiaomei@126.com', '小明', '女', '湖北武汉金地中心城', '4398249082', '13854898541', '0', '2019-05-05 21:20:34', 'no', '1539828998553.jpg', 'no', '通过审核');
INSERT INTO `member` VALUES ('36', 'dd', '123', 'calvin@126.com', '丁丁', '男', '湖北武汉关谷一路', '4839028402', '13894389431', '0', '2019-05-05 22:10:14', 'no', '1539871764306.jpg', 'no', '通过审核');
INSERT INTO `member` VALUES ('37', 'Tom', '123', 'tom@126.com', '汤姆', '男', '北京市昌平区', '480384028', '13904830431', '0', '2019-05-05 16:21:45', 'no', '1539937260073.jpg', 'no', '通过审核');
INSERT INTO `member` VALUES ('38', 'zhangsan', '123', 'zhangsan@126.com', '张三', '男', '湖北武汉', '43248929', '13937462611', '0', '2019-05-05 00:37:49', 'no', '1540053443906.jpg', 'no', '通过审核');
INSERT INTO `member` VALUES ('39', 'xgg', '123', '86266000@qq.com', '小哥哥', '男', '天上', '86266000', '13566667777', '0', '2019-05-05 22:16:44', 'no', '1552227385618.jpg', 'no', '通过审核');
INSERT INTO `member` VALUES ('40', 'dgg', '123', '454413@qq.com', '大哥哥', '男', '天上', '454413', '13455556666', '0', '2019-05-05 00:39:47', 'no', '1556117126608.jpg', 'no', '通过审核');
INSERT INTO `member` VALUES ('41', 'ldh', '123', '45652443@qq.com', '刘德华', '男', '天津路1号', '45652443', '13555556666', '0', '2019-05-05 23:53:24', 'no', '1556121189814.jpg', 'no', '通过审核');
INSERT INTO `member` VALUES ('42', 'zh123', '123', '86266000@qq.com', '邹华', '男', '科技港1-101', '86266000', '13455556666', '0', '2019-05-06 14:36:31', 'no', '1557124567627.jpg', 'no', '通过审核');
INSERT INTO `mgword` VALUES ('1', '密码');
INSERT INTO `mgword` VALUES ('2', '去你妈的');
INSERT INTO `mgword` VALUES ('4', '奶奶的');
INSERT INTO `news` VALUES ('9', 'ENJOYZ新版APP正式上线啦！EZer将得到更好的用户体验！', '<p style=\"color:#505B65;font-family:&quot;font-size:16px;background-color:#FFFFFF;\">\r\n	新版本更新如下：\r\n</p>\r\n<p style=\"margin-left:18pt;color:#505B65;font-family:&quot;font-size:16px;background-color:#FFFFFF;\">\r\n	1.&nbsp;&nbsp;&nbsp; 界面升级：全新的结构更一目了然；\r\n</p>\r\n<p style=\"margin-left:18pt;color:#505B65;font-family:&quot;font-size:16px;background-color:#FFFFFF;\">\r\n	2.&nbsp;&nbsp;&nbsp; 聚好货板块：特惠专区和商家专区经过ENJOYZ认证，买得更方便、放心；\r\n</p>\r\n<p style=\"margin-left:18pt;color:#505B65;font-family:&quot;font-size:16px;background-color:#FFFFFF;\">\r\n	3.&nbsp;&nbsp;&nbsp; 问答板块：足球装备的相关问题EZer可在问答板块中提问，由其他EZer进行解答；\r\n</p>\r\n<p style=\"margin-left:18pt;color:#505B65;font-family:&quot;font-size:16px;background-color:#FFFFFF;\">\r\n	4.&nbsp;&nbsp;&nbsp; EZ币：EZ币系统升级，获得EZ币渠道更多，EZ币即将发挥更大的价值。\r\n</p>', '1557124913029.jpg', '2019-05-06 14:42:04');
INSERT INTO `signin` VALUES ('1', '42', '2019-05-06');
INSERT INTO `sxinfo` VALUES ('3', '41', '40', '你好啊', 'ok', '2019-04-25 00:02:37');
INSERT INTO `sxinfo` VALUES ('4', '41', '42', '你好', 'OK', '2019-05-06 14:42:33');
INSERT INTO `tzhtinfo` VALUES ('12', '18', '好像没有什么问题', '41', '2019-05-06 14:39:13', '0', '');
INSERT INTO `tzinfo` VALUES ('18', '24', '25', '[求助] 请大神看看鞋标有问题没', '<p>\r\n	&nbsp; &nbsp; 大神们，帮帮忙\r\n</p>\r\n<p>\r\n	<img src=\"/gymshoesbbs/attached/image/20190506/20190506143837_980.jpg\" alt=\"\" />\r\n</p>', '42', '2019-05-06 14:38:38', '0', '13', 'yes', 'yes', '2019-05-06 14:40:15', 'yes', null);
INSERT INTO `tzinfo` VALUES ('19', '33', '41', '乔11要卖掉', '&nbsp; &nbsp; 有需要的私信我', '42', '2019-05-06 14:45:24', '0', '2', 'no', 'no', '', 'yes', null);
INSERT INTO `user` VALUES ('1', '管理员', 'admin', '123', '程咬金', '男', '13667899876', 'admin@163.com', '湖北武汉东湖高新金地格林东郡1-18-2', '0', '2019-04-15 00:00:00');
INSERT INTO `yqlink` VALUES ('2', '新浪', 'http://www.sina.com');
INSERT INTO `yqlink` VALUES ('3', '百度', 'http://www.baidu.com');
INSERT INTO `yqlink` VALUES ('4', '腾讯', 'http://www.qq.com');
INSERT INTO `yqlink` VALUES ('5', '小米', 'http://www.xiaomi.com');
