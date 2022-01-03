/*
 Navicat MySQL Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 101.43.16.42:3306
 Source Schema         : officialwebsite

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 30/12/2021 10:28:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fileUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `isDel` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document
-- ----------------------------
INSERT INTO `document` VALUES (1, '404 error with portals-amico.png', 'E:\\ACM技术组\\acm-web-site\\acmweb-java/src/main/resources/document/404 error with portals-amico.png', 1);

-- ----------------------------
-- Table structure for introduce
-- ----------------------------
DROP TABLE IF EXISTS `introduce`;
CREATE TABLE `introduce`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `created` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of introduce
-- ----------------------------
INSERT INTO `introduce` VALUES (1, '1', '1', '1', '2021-12-29 21:49:30');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `honor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, '姚辉', '2020', '计算机与通信工程学院', '第九届NEUQ-ACM会长', NULL);
INSERT INTO `member` VALUES (2, '黄一珂', '2020', '计算机与通信工程学院', '第九届NEUQ-ACM技术部部长', NULL);
INSERT INTO `member` VALUES (3, '王奕霄', '2020', '计算机与通信工程学院', '第九届NEUQ-ACM技术部副部长', NULL);
INSERT INTO `member` VALUES (4, '胡鸿捷', '2020', '计算机与通信工程学院', '第九届NEUQ-ACM竞赛队队长', NULL);
INSERT INTO `member` VALUES (5, '张逸凡', '2019', '控制工程学院', '第八届NEUQ-ACM会长', NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '欢迎来到NEUQ-ACM');

-- ----------------------------
-- Table structure for qrcode
-- ----------------------------
DROP TABLE IF EXISTS `qrcode`;
CREATE TABLE `qrcode`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrcode
-- ----------------------------

-- ----------------------------
-- Table structure for rotation
-- ----------------------------
DROP TABLE IF EXISTS `rotation`;
CREATE TABLE `rotation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDel` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rotation
-- ----------------------------
INSERT INTO `rotation` VALUES (1, 'https://s3.bmp.ovh/imgs/2021/12/801449051829eb13.jpg', NULL, 0);
INSERT INTO `rotation` VALUES (2, 'https://s3.bmp.ovh/imgs/2021/12/5f60ab4a82d1e62c.jpg', NULL, 0);
INSERT INTO `rotation` VALUES (3, 'https://s3.bmp.ovh/imgs/2021/12/da8ab15e39018c1c.jpg', NULL, 0);
INSERT INTO `rotation` VALUES (4, 'http://101.43.16.42:8082/rotation/参考LOGO2 (1).png', '参考LOGO2 (1).png', 0);
INSERT INTO `rotation` VALUES (5, 'http://101.43.16.42:8082/rotation/参考LOGO2 (1).png', '参考LOGO2 (1).png', 0);
INSERT INTO `rotation` VALUES (6, 'http://101.43.16.42:8082/rotation/参考LOGO2 (1).png', '参考LOGO2 (1).png', 0);
INSERT INTO `rotation` VALUES (7, 'http://101.43.16.42:8082/rotation/henrik.ico', 'henrik.ico', 0);
INSERT INTO `rotation` VALUES (8, 'http://101.43.16.42:8082/rotation/参考LOGO(1).png', '参考LOGO(1).png', 0);

-- ----------------------------
-- Table structure for time
-- ----------------------------
DROP TABLE IF EXISTS `time`;
CREATE TABLE `time`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of time
-- ----------------------------
INSERT INTO `time` VALUES (1, '2021-12-27 13:27:22', 'test');
INSERT INTO `time` VALUES (2, '2022-01-05 20:00:00', '第九届图灵杯宣讲会');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码,md5加盐加密',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'admin', '316ef45bbb34268193be069ce29f2167', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
