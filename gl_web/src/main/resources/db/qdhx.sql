/*
Navicat MySQL Data Transfer

Source Server         : itgs
Source Server Version : 50536
Source Host           : 127.0.0.1:3306
Source Database       : qdhx

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2018-01-07 22:30:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gl_price_info
-- ----------------------------
DROP TABLE IF EXISTS `gl_price_info`;
CREATE TABLE `gl_price_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `day` varchar(10) DEFAULT NULL COMMENT '日期-天',
  `area` varchar(30) DEFAULT NULL COMMENT '地区',
  `province` varchar(30) DEFAULT NULL COMMENT '省份',
  `market` varchar(30) DEFAULT NULL COMMENT '市场',
  `product` varchar(60) DEFAULT NULL COMMENT '产品名称',
  `material` varchar(30) DEFAULT NULL COMMENT '材质',
  `specification` varchar(60) DEFAULT NULL COMMENT '规格',
  `price` varchar(20) DEFAULT NULL COMMENT '价格',
  `price_change` varchar(30) DEFAULT NULL COMMENT '价格涨跌',
  `price_change_value` varchar(20) DEFAULT NULL COMMENT '价格涨跌值',
  `price_change_reate` varchar(20) DEFAULT NULL COMMENT '价格涨跌率',
  `unit` varchar(20) DEFAULT NULL COMMENT '价格单位',
  `vendor` varchar(30) DEFAULT NULL COMMENT '生产厂家',
  `price_type` varchar(20) DEFAULT NULL COMMENT '价格类型',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='价格表';

-- ----------------------------
-- Records of gl_price_info
-- ----------------------------

-- ----------------------------
-- Table structure for gl_sys_code
-- ----------------------------
DROP TABLE IF EXISTS `gl_sys_code`;
CREATE TABLE `gl_sys_code` (
  `type` varchar(20) DEFAULT NULL COMMENT '类别',
  `code` varchar(20) DEFAULT NULL COMMENT '代码',
  `comment` varchar(50) DEFAULT NULL COMMENT '代码说明',
  `order_number` int(11) DEFAULT NULL COMMENT '序号',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of gl_sys_code
-- ----------------------------

-- ----------------------------
-- Table structure for gl_sys_func
-- ----------------------------
DROP TABLE IF EXISTS `gl_sys_func`;
CREATE TABLE `gl_sys_func` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '注释',
  `path` varchar(30) DEFAULT NULL COMMENT '路径',
  `parent` varchar(30) DEFAULT NULL COMMENT '父目录',
  `text` varchar(100) DEFAULT NULL COMMENT '显示内容',
  `icon` varchar(30) DEFAULT NULL COMMENT '图标',
  `enable` tinyint(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of gl_sys_func
-- ----------------------------

-- ----------------------------
-- Table structure for gl_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `gl_sys_user`;
CREATE TABLE `gl_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门编号',
  `post_id` int(11) DEFAULT NULL COMMENT '岗位编号',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of gl_sys_user
-- ----------------------------
