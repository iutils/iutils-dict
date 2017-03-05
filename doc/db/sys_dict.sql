/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50547
 Source Host           : localhost
 Source Database       : db_iutils

 Target Server Version : 50547
 File Encoding         : utf-8

 Date: 03/02/2017 14:46:24 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '值',
  `label` varchar(100) NOT NULL COMMENT '标签',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `sort` int(10) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典表';

SET FOREIGN_KEY_CHECKS = 1;
