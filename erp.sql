/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : erp

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 14/06/2019 13:11:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for console_log
-- ----------------------------
DROP TABLE IF EXISTS `console_log`;
CREATE TABLE `console_log`  (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ENTITY_ID` int(11) NULL DEFAULT NULL,
  `TABLE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `OPT_TYPE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `OPT_TIME` datetime(0) NULL DEFAULT NULL,
  `EMP_ID` int(11) NULL DEFAULT NULL,
  `NOTE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`) USING BTREE,
  INDEX `FK_7v6m59i8the8dgduth2ppotlr`(`EMP_ID`) USING BTREE,
  CONSTRAINT `FK_7v6m59i8the8dgduth2ppotlr` FOREIGN KEY (`EMP_ID`) REFERENCES `emp` (`emp_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of console_log
-- ----------------------------
INSERT INTO `console_log` VALUES (1, 13, 'order_model', '审核订单', '2019-05-16 16:01:26', 20, '数量太少');
INSERT INTO `console_log` VALUES (2, 13, 'order_model', '审核订单', '2019-05-16 21:37:20', 20, '价格不对');
INSERT INTO `console_log` VALUES (3, 13, 'order_model', '审核订单', '2019-05-16 21:38:43', 20, '');
INSERT INTO `console_log` VALUES (4, 14, 'order_model', '审核订单', '2019-05-17 22:15:15', 18, '');
INSERT INTO `console_log` VALUES (5, 15, 'order_model', '审核订单', '2019-05-17 22:17:22', 18, '');
INSERT INTO `console_log` VALUES (6, 20, 'order_model', '审核订单', '2019-05-17 22:57:25', 18, '');
INSERT INTO `console_log` VALUES (7, 21, 'order_model', '审核订单', '2019-05-20 09:54:21', 20, '');
INSERT INTO `console_log` VALUES (8, 22, 'order_model', '审核订单', '2019-05-27 22:20:11', 22, 'ok');
INSERT INTO `console_log` VALUES (9, 23, 'order_model', '审核订单', '2019-06-08 18:41:58', 20, 'OK');
INSERT INTO `console_log` VALUES (10, 24, 'order_model', '审核订单', '2019-06-12 22:37:28', 22, '商品数量不对');
INSERT INTO `console_log` VALUES (11, 24, 'order_model', '审核订单', '2019-06-12 22:37:49', 22, '');

-- ----------------------------
-- Table structure for dep
-- ----------------------------
DROP TABLE IF EXISTS `dep`;
CREATE TABLE `dep`  (
  `dep_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dep_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dep
-- ----------------------------
INSERT INTO `dep` VALUES (1, '采购部门', '029-8168122');
INSERT INTO `dep` VALUES (2, '运输部门', '029-8168123');
INSERT INTO `dep` VALUES (3, '仓储部门', '029-8168124');
INSERT INTO `dep` VALUES (4, '商品部门', '029-8168127');
INSERT INTO `dep` VALUES (5, '销售部', '029-8168125');
INSERT INTO `dep` VALUES (6, '财务办公室', '029-8168128');
INSERT INTO `dep` VALUES (7, '总裁室', '029-8168126');
INSERT INTO `dep` VALUES (8, '人事部门', '029-12345678');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `emp_id` int(20) NOT NULL AUTO_INCREMENT,
  `dep_id` int(20) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(1) NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE,
  INDEX `FK_jhhn48n7f40gub36dvlhfxxnh`(`dep_id`) USING BTREE,
  CONSTRAINT `FK_jhhn48n7f40gub36dvlhfxxnh` FOREIGN KEY (`dep_id`) REFERENCES `dep` (`dep_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (1, 2, '张三', 'zhangsan', '9888764@qq.com', '15010090634', 2, '新城区', '2016-05-01', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `emp` VALUES (6, 1, '张希希', 'zhangxixi', '87456454@qq.com', '15010090634', 2, '未央区', '2010-06-18', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `emp` VALUES (7, 3, '李四', 'lisi', '81364285@qq.com', '13812345687', 1, '碑林区', '2016-05-01', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `emp` VALUES (18, 2, '王五', 'wangwu', '69784684@qq.com', '13812345678', 1, '鄠邑区', '1993-05-01', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `emp` VALUES (19, 2, '张麻子', 'zhangmazi', '12345678@qq.com', '13898765432', 1, '蓝田县', '1998-05-15', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `emp` VALUES (20, 7, '管理员', 'admin', '123456789@qq.com', '13898765423', 1, '未央区', '2006-05-18', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `emp` VALUES (21, 4, '商品', 'shangpin', '123@qq.com', '13812345678', 1, '未央区', '2004-05-06', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `emp` VALUES (22, 1, '采购', 'caigou', '123@qq.com', '13892408409', 1, '鄠邑区', '2010-06-23', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `emp` VALUES (23, 8, '人事', 'renshi', '123@qq.com', '13892408409', 2, '鄠邑区', '2010-06-17', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `emp` VALUES (24, 2, '运输', 'yunshu', '123@qq.com', '13892408409', 1, '鄠邑区', '2011-06-09', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(20) NOT NULL AUTO_INCREMENT,
  `parent_menu_id` int(20) NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_menu` int(20) NULL DEFAULT NULL,
  `level` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `FK_2nax3eqqs9ensaahh5atyepn7`(`parent_menu_id`) USING BTREE,
  CONSTRAINT `FK_2nax3eqqs9ensaahh5atyepn7` FOREIGN KEY (`parent_menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 807 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, NULL, '系统菜单', NULL, 1, 1);
INSERT INTO `menu` VALUES (2, 1, '商品管理', '', 1, 2);
INSERT INTO `menu` VALUES (3, 1, '采购管理', NULL, 1, 2);
INSERT INTO `menu` VALUES (5, 1, '商品运输', '', 1, 2);
INSERT INTO `menu` VALUES (6, 1, '仓库管理', '', 1, 2);
INSERT INTO `menu` VALUES (8, 1, '基础维护', '', 1, 2);
INSERT INTO `menu` VALUES (201, 2, '供应商', '${path }/supplier_list', 1, 3);
INSERT INTO `menu` VALUES (202, 2, '商品类别', '${path }/productType_list', 1, 3);
INSERT INTO `menu` VALUES (203, 2, '商品', '${path }/product_list', 1, 3);
INSERT INTO `menu` VALUES (301, 3, '采购订单', '${path }/orderModel_list?query.orderType=1&query.orderState=1', 1, 3);
INSERT INTO `menu` VALUES (303, 3, '采购审批', '${path }/orderModel_checklist?query.orderType=1&query.orderState=1', 1, 3);
INSERT INTO `menu` VALUES (501, 5, '运输任务查询', '${path }/tranOrder_tasks?query.orderType=2&query.orderState=1', 1, 3);
INSERT INTO `menu` VALUES (502, 5, '运输任务指派', '${path }/tranOrder_taskList?query.orderType=1&query.orderState=2', 1, 3);
INSERT INTO `menu` VALUES (601, 6, '库存查询', '${path }/store_slist', 1, 3);
INSERT INTO `menu` VALUES (602, 6, '入库', '${path }/tranOrder_inList?query.orderType=3&query.orderState=1', 1, 3);
INSERT INTO `menu` VALUES (801, 8, '部门维护', '${path }/dep_list', 1, 3);
INSERT INTO `menu` VALUES (802, 8, '员工维护', '${path }/emp_list', 1, 3);
INSERT INTO `menu` VALUES (803, 8, '角色维护', '${path }/role_list', 1, 3);
INSERT INTO `menu` VALUES (805, 8, '菜单维护', '${path }/menu_list', 1, 3);
INSERT INTO `menu` VALUES (806, 8, '仓库管理', '${path }/store_store', 1, 3);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `order_detail_id` int(20) NOT NULL AUTO_INCREMENT,
  `detail_num` int(20) NULL DEFAULT NULL,
  `detail_price` double(10, 2) NULL DEFAULT NULL,
  `product_id` int(20) NULL DEFAULT NULL,
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `surplus` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`) USING BTREE,
  INDEX `FK_nkb731c2u3fxwuln18o6lyx6u`(`product_id`) USING BTREE,
  CONSTRAINT `FK_nkb731c2u3fxwuln18o6lyx6u` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (22, 1, 3.00, 3, '14', 0);
INSERT INTO `order_detail` VALUES (23, 1, 1.00, 1, '15', 0);
INSERT INTO `order_detail` VALUES (28, 11, 11.00, 1, '18', 0);
INSERT INTO `order_detail` VALUES (29, 10, 1.00, 1, '19', 0);
INSERT INTO `order_detail` VALUES (30, 10, 5.00, 5, '19', 0);
INSERT INTO `order_detail` VALUES (31, 10, 30.00, 3, '19', 0);
INSERT INTO `order_detail` VALUES (43, 100, 5.00, 1, '13', 100);
INSERT INTO `order_detail` VALUES (44, 100, 2.00, 2, '20', 100);
INSERT INTO `order_detail` VALUES (45, 100, 1.00, 1, '20', 100);
INSERT INTO `order_detail` VALUES (46, 100, 5.00, 5, '20', 100);
INSERT INTO `order_detail` VALUES (47, 100, 3.00, 3, '20', 100);
INSERT INTO `order_detail` VALUES (48, 200, 2.00, 2, '21', 200);
INSERT INTO `order_detail` VALUES (49, 200, 1.00, 1, '21', 200);
INSERT INTO `order_detail` VALUES (50, 200, 5.00, 5, '21', 200);
INSERT INTO `order_detail` VALUES (51, 200, 3.00, 3, '21', 200);
INSERT INTO `order_detail` VALUES (52, 100, 60.00, 12, '22', 0);
INSERT INTO `order_detail` VALUES (53, 100, 60.00, 12, '23', 0);
INSERT INTO `order_detail` VALUES (55, 200, 120.00, 6, '24', 0);

-- ----------------------------
-- Table structure for order_model
-- ----------------------------
DROP TABLE IF EXISTS `order_model`;
CREATE TABLE `order_model`  (
  `order_id` int(20) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creater` int(20) NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  `checker` int(20) NULL DEFAULT NULL,
  `check_time` date NULL DEFAULT NULL,
  `completer` int(20) NULL DEFAULT NULL,
  `end_time` date NULL DEFAULT NULL,
  `order_type` int(1) NULL DEFAULT NULL,
  `order_state` int(3) NULL DEFAULT NULL,
  `total_num` int(11) NULL DEFAULT NULL,
  `total_price` double(10, 2) NULL DEFAULT NULL,
  `supplier_id` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `FK_5dbo0ebhs6l1ng5vvc9s5pg78`(`completer`) USING BTREE,
  INDEX `FK_86xtqxo9x0ydisyyykdshfvis`(`creater`) USING BTREE,
  INDEX `FK_8p4oeyr7ov98689ggskhuv1yy`(`supplier_id`) USING BTREE,
  INDEX `FK_oyiru0vmpv0k4us65acon5cxy`(`checker`) USING BTREE,
  CONSTRAINT `FK_5dbo0ebhs6l1ng5vvc9s5pg78` FOREIGN KEY (`completer`) REFERENCES `emp` (`emp_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_86xtqxo9x0ydisyyykdshfvis` FOREIGN KEY (`creater`) REFERENCES `emp` (`emp_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_8p4oeyr7ov98689ggskhuv1yy` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_oyiru0vmpv0k4us65acon5cxy` FOREIGN KEY (`checker`) REFERENCES `emp` (`emp_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_model
-- ----------------------------
INSERT INTO `order_model` VALUES (20, '1465468290270105', 18, '2019-05-17', 18, '2019-05-17', NULL, NULL, 2, 1, 400, 1100.00, 1);
INSERT INTO `order_model` VALUES (21, '1465488343016669', 20, '2019-05-20', 20, '2019-05-20', NULL, NULL, 2, 1, 800, 2200.00, 1);
INSERT INTO `order_model` VALUES (22, '20190527221702941', 6, '2019-05-27', 22, '2019-05-27', 19, NULL, 2, 3, 100, 6000.00, 3);
INSERT INTO `order_model` VALUES (23, '20190603153033704', 22, '2019-06-03', 20, '2019-06-08', 18, NULL, 3, 3, 100, 6000.00, 3);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_type_id` int(20) NULL DEFAULT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `origin` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `producer` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `in_price` double(10, 2) NULL DEFAULT NULL,
  `out_price` double(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `FK_pe3bpejwrpkqc4ao43xrw0wpo`(`product_type_id`) USING BTREE,
  CONSTRAINT `FK_pe3bpejwrpkqc4ao43xrw0wpo` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`product_type_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 1, '2019年新款男士休闲袜', '福建', '晋江总部厂', '双', 1.00, 200.00);
INSERT INTO `product` VALUES (2, 2, '2019年新款男士休闲七分短裤', '福建', '晋江总部厂', '条', 2.00, 200.00);
INSERT INTO `product` VALUES (3, 1, '足球鞋', '福建', '福建晋江', '双', 3.00, 200.00);
INSERT INTO `product` VALUES (4, 3, '2019年新款女士休闲上衣', '福建', '晋江总部厂', '件', 4.00, 20.00);
INSERT INTO `product` VALUES (5, 1, '2019年新款男士运动袜', '福建', '晋江总部厂', '双', 5.00, 50.00);
INSERT INTO `product` VALUES (6, 6, '2019年春季新款男士休闲网鞋', '福建', '晋江总部厂', '双', 120.00, 200.00);
INSERT INTO `product` VALUES (9, 14, '篮球鞋', '福建', '李宁', '双', 90.00, 270.00);
INSERT INTO `product` VALUES (10, 3, '牛仔裤', '福建', '七匹狼', '条', 90.00, 270.00);
INSERT INTO `product` VALUES (11, 1, '短裤', '福建', '七匹狼', '条', 90.00, 270.00);
INSERT INTO `product` VALUES (12, 15, '李宁', '福建', '李宁', '条', 60.00, 150.00);

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type`  (
  `product_type_id` int(20) NOT NULL AUTO_INCREMENT,
  `supplier_id` int(20) NULL DEFAULT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`product_type_id`) USING BTREE,
  INDEX `FK_qorfgmrkr55syk7yyprvymiyo`(`supplier_id`) USING BTREE,
  CONSTRAINT `FK_qorfgmrkr55syk7yyprvymiyo` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_type
-- ----------------------------
INSERT INTO `product_type` VALUES (1, 1, '短裤');
INSERT INTO `product_type` VALUES (2, 2, '上衣');
INSERT INTO `product_type` VALUES (3, 1, '长筒牛仔裤');
INSERT INTO `product_type` VALUES (6, 6, '鞋');
INSERT INTO `product_type` VALUES (8, 2, '足球');
INSERT INTO `product_type` VALUES (10, 2, '皮带');
INSERT INTO `product_type` VALUES (12, 1, '皮带');
INSERT INTO `product_type` VALUES (13, 2, '运动衣');
INSERT INTO `product_type` VALUES (14, 5, '鞋');
INSERT INTO `product_type` VALUES (15, 6, '裤子');

-- ----------------------------
-- Table structure for relation_emp_role
-- ----------------------------
DROP TABLE IF EXISTS `relation_emp_role`;
CREATE TABLE `relation_emp_role`  (
  `emp_id` int(20) NULL DEFAULT NULL,
  `role_id` int(20) NULL DEFAULT NULL,
  INDEX `FK_316fr9ltkexw6y7xdb775698v`(`emp_id`) USING BTREE,
  INDEX `FK_awyadfx7xf5gp283dhqy2xq99`(`role_id`) USING BTREE,
  CONSTRAINT `FK_316fr9ltkexw6y7xdb775698v` FOREIGN KEY (`emp_id`) REFERENCES `emp` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_awyadfx7xf5gp283dhqy2xq99` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_emp_role
-- ----------------------------
INSERT INTO `relation_emp_role` VALUES (20, 9);
INSERT INTO `relation_emp_role` VALUES (19, 4);
INSERT INTO `relation_emp_role` VALUES (1, 4);
INSERT INTO `relation_emp_role` VALUES (21, 1);
INSERT INTO `relation_emp_role` VALUES (7, 2);
INSERT INTO `relation_emp_role` VALUES (22, 3);
INSERT INTO `relation_emp_role` VALUES (18, 5);
INSERT INTO `relation_emp_role` VALUES (24, 4);

-- ----------------------------
-- Table structure for relation_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `relation_role_menu`;
CREATE TABLE `relation_role_menu`  (
  `role_id` int(20) NULL DEFAULT NULL,
  `menu_id` int(20) NULL DEFAULT NULL,
  INDEX `FK_axbfr9v6kviftl9akkd13hmm`(`role_id`) USING BTREE,
  INDEX `FK_fj5vwhdappi1q3kvvgcoeevm`(`menu_id`) USING BTREE,
  CONSTRAINT `FK_axbfr9v6kviftl9akkd13hmm` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_fj5vwhdappi1q3kvvgcoeevm` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_role_menu
-- ----------------------------
INSERT INTO `relation_role_menu` VALUES (1, 2);
INSERT INTO `relation_role_menu` VALUES (1, 201);
INSERT INTO `relation_role_menu` VALUES (1, 202);
INSERT INTO `relation_role_menu` VALUES (1, 203);
INSERT INTO `relation_role_menu` VALUES (2, 6);
INSERT INTO `relation_role_menu` VALUES (2, 602);
INSERT INTO `relation_role_menu` VALUES (2, 601);
INSERT INTO `relation_role_menu` VALUES (3, 303);
INSERT INTO `relation_role_menu` VALUES (3, 301);
INSERT INTO `relation_role_menu` VALUES (3, 3);
INSERT INTO `relation_role_menu` VALUES (4, 501);
INSERT INTO `relation_role_menu` VALUES (4, 5);
INSERT INTO `relation_role_menu` VALUES (4, 502);
INSERT INTO `relation_role_menu` VALUES (5, 5);
INSERT INTO `relation_role_menu` VALUES (5, 501);
INSERT INTO `relation_role_menu` VALUES (6, 3);
INSERT INTO `relation_role_menu` VALUES (6, 301);
INSERT INTO `relation_role_menu` VALUES (9, 602);
INSERT INTO `relation_role_menu` VALUES (9, 601);
INSERT INTO `relation_role_menu` VALUES (9, 201);
INSERT INTO `relation_role_menu` VALUES (9, 3);
INSERT INTO `relation_role_menu` VALUES (9, 8);
INSERT INTO `relation_role_menu` VALUES (9, 303);
INSERT INTO `relation_role_menu` VALUES (9, 502);
INSERT INTO `relation_role_menu` VALUES (9, 501);
INSERT INTO `relation_role_menu` VALUES (9, 203);
INSERT INTO `relation_role_menu` VALUES (9, 801);
INSERT INTO `relation_role_menu` VALUES (9, 805);
INSERT INTO `relation_role_menu` VALUES (9, 301);
INSERT INTO `relation_role_menu` VALUES (9, 5);
INSERT INTO `relation_role_menu` VALUES (9, 802);
INSERT INTO `relation_role_menu` VALUES (9, 202);
INSERT INTO `relation_role_menu` VALUES (9, 6);
INSERT INTO `relation_role_menu` VALUES (9, 2);
INSERT INTO `relation_role_menu` VALUES (9, 806);
INSERT INTO `relation_role_menu` VALUES (9, 803);
INSERT INTO `relation_role_menu` VALUES (10, 502);
INSERT INTO `relation_role_menu` VALUES (10, 201);
INSERT INTO `relation_role_menu` VALUES (10, 6);
INSERT INTO `relation_role_menu` VALUES (10, 601);
INSERT INTO `relation_role_menu` VALUES (10, 5);
INSERT INTO `relation_role_menu` VALUES (10, 303);
INSERT INTO `relation_role_menu` VALUES (10, 301);
INSERT INTO `relation_role_menu` VALUES (10, 602);
INSERT INTO `relation_role_menu` VALUES (10, 2);
INSERT INTO `relation_role_menu` VALUES (10, 202);
INSERT INTO `relation_role_menu` VALUES (10, 3);
INSERT INTO `relation_role_menu` VALUES (10, 501);
INSERT INTO `relation_role_menu` VALUES (10, 203);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '商品管理员', '000');
INSERT INTO `role` VALUES (2, '仓库管理员', '001');
INSERT INTO `role` VALUES (3, '采购管理员', '002');
INSERT INTO `role` VALUES (4, '运输管理员', '003');
INSERT INTO `role` VALUES (5, '运输员', '004');
INSERT INTO `role` VALUES (6, '采购员', '005');
INSERT INTO `role` VALUES (9, '超级管理员', '008');
INSERT INTO `role` VALUES (10, '管理员', '009');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `store_id` int(20) NOT NULL AUTO_INCREMENT,
  `stockman` int(20) NULL DEFAULT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`store_id`) USING BTREE,
  INDEX `FK_bivav0a07yoegxca6euxr3jn`(`stockman`) USING BTREE,
  CONSTRAINT `FK_bivav0a07yoegxca6euxr3jn` FOREIGN KEY (`stockman`) REFERENCES `emp` (`emp_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, 19, '服装仓库', '北郊物流园');
INSERT INTO `store` VALUES (2, 1, '电子仓库', '长安物流园');
INSERT INTO `store` VALUES (5, 7, '服装仓库', '灞桥区');

-- ----------------------------
-- Table structure for store_detail
-- ----------------------------
DROP TABLE IF EXISTS `store_detail`;
CREATE TABLE `store_detail`  (
  `detail_id` int(20) NOT NULL AUTO_INCREMENT,
  `store_id` int(20) NULL DEFAULT NULL,
  `product_id` int(20) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL COMMENT '仓库明细中已经存储的商品量',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `FK_88tr6l9o56p5e7cd0k6texg69`(`product_id`) USING BTREE,
  INDEX `FK_cfr7mly6ntpwylorqwoorsugh`(`store_id`) USING BTREE,
  CONSTRAINT `FK_88tr6l9o56p5e7cd0k6texg69` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_cfr7mly6ntpwylorqwoorsugh` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store_detail
-- ----------------------------
INSERT INTO `store_detail` VALUES (2, 5, 3, 11);
INSERT INTO `store_detail` VALUES (4, 1, 12, 200);
INSERT INTO `store_detail` VALUES (5, 5, 12, 200);
INSERT INTO `store_detail` VALUES (7, 1, 6, 400);

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplier_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `needs` int(1) NULL DEFAULT NULL COMMENT '如果是1，那么是送货，如果是2那么是自提',
  PRIMARY KEY (`supplier_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '福建七匹狼实业股份有限公司', '江苏', '李大明', '400-8868677', 1);
INSERT INTO `supplier` VALUES (2, '安踏体育用品有限公司', '福建', '张大明', '86-595 8592 9999', 1);
INSERT INTO `supplier` VALUES (3, '李宁（中国）体育用品有限公司', '北京', '李大建', '010-80800808', 1);
INSERT INTO `supplier` VALUES (5, '鸿星尔克（中国）体育用品有限公司', '北京', '李大鹏', '18244014432', 2);
INSERT INTO `supplier` VALUES (6, '鸿星尔克体育', '北京', '测试1', '123456789', 1);

SET FOREIGN_KEY_CHECKS = 1;
