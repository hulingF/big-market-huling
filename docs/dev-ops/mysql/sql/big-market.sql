/*
 Navicat Premium Data Transfer

 Source Server         : MySQL-Hul
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : big-market

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 18/01/2025 17:58:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` int NOT NULL COMMENT '抽奖策略ID',
  `strategy_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '抽奖策略描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of strategy
-- ----------------------------
INSERT INTO `strategy` VALUES (1, 10001, '抽奖策略测试A', '2025-01-18 17:32:13', '2025-01-18 17:32:13');

-- ----------------------------
-- Table structure for strategy_award
-- ----------------------------
DROP TABLE IF EXISTS `strategy_award`;
CREATE TABLE `strategy_award`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` int NOT NULL COMMENT '抽奖策略ID',
  `award_id` int NOT NULL COMMENT '抽奖奖品ID',
  `award_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '抽奖奖品标题',
  `award_subtitle` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '抽奖奖品副标题',
  `award_count` int NOT NULL COMMENT '奖品库存总量',
  `award_count_surplus` int NOT NULL COMMENT '奖品库存剩余',
  `award_rate` decimal(6, 4) NOT NULL COMMENT '奖品中奖概率',
  `rule_models` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '奖品规则模型',
  `sort` int NOT NULL DEFAULT 0 COMMENT '奖品排序位次',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of strategy_award
-- ----------------------------
INSERT INTO `strategy_award` VALUES (1, 10001, 101, '随机积分', NULL, 50000, 50000, 80.0000, 'rule_random,rule_lucky', 0, '2025-01-18 17:33:47', '2025-01-18 17:45:11');
INSERT INTO `strategy_award` VALUES (2, 10001, 102, '1天体验卡', NULL, 10000, 10000, 10.0000, 'rule_lucky', 1, '2025-01-18 17:35:08', '2025-01-18 17:45:19');
INSERT INTO `strategy_award` VALUES (3, 10001, 103, '3天体验卡', NULL, 5000, 5000, 5.0000, 'rule_lucky', 2, '2025-01-18 17:36:28', '2025-01-18 17:45:26');
INSERT INTO `strategy_award` VALUES (4, 10001, 104, '5天体验卡', NULL, 5000, 5000, 3.0000, 'rule_lucky', 3, '2025-01-18 17:39:12', '2025-01-18 17:45:27');
INSERT INTO `strategy_award` VALUES (5, 10001, 105, '增加GPT4o模型', '抽奖1次解锁', 2000, 2000, 1.0000, 'rule_lock,rule_lucky', 4, '2025-01-18 17:40:41', '2025-01-18 17:45:31');
INSERT INTO `strategy_award` VALUES (6, 10001, 106, '增加DELL画图模型', '抽奖3次检索', 500, 500, 0.9000, 'rule_lock.rule_lucky', 5, '2025-01-18 17:41:52', '2025-01-18 17:45:36');
INSERT INTO `strategy_award` VALUES (7, 10001, 107, '增加全部模型', '抽奖10次解锁', 100, 100, 0.1000, 'rule_lock,rule_lucky', 6, '2025-01-18 17:42:40', '2025-01-18 17:45:40');

-- ----------------------------
-- Table structure for strategy_rule
-- ----------------------------
DROP TABLE IF EXISTS `strategy_rule`;
CREATE TABLE `strategy_rule`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` int NOT NULL COMMENT '抽奖策略ID',
  `award_id` int NULL DEFAULT NULL COMMENT '抽奖奖品ID',
  `rule_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '抽奖规则描述',
  `rule_type` int NOT NULL COMMENT '抽奖规则类型：1-策略规则、2-奖品规则',
  `rule_model` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '抽奖规则模型：rule_random-随机值计算、rule_lock-N次解锁...',
  `rule_value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '抽奖规则物料',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of strategy_rule
-- ----------------------------
INSERT INTO `strategy_rule` VALUES (1, 10001, 101, '随机值计算', 2, 'rule_random', '1,100', '2025-01-18 17:47:00', '2025-01-18 17:47:00');
INSERT INTO `strategy_rule` VALUES (2, 10001, 105, '抽奖1次解锁', 2, 'rule_lock', '1', '2025-01-18 17:48:23', '2025-01-18 17:48:23');
INSERT INTO `strategy_rule` VALUES (3, 10001, 106, '抽奖3次解锁', 2, 'rule_lock', '3', '2025-01-18 17:48:58', '2025-01-18 17:48:58');
INSERT INTO `strategy_rule` VALUES (4, 10001, 107, '抽奖10次解锁', 2, 'rule_lock', '10', '2025-01-18 17:49:36', '2025-01-18 17:49:36');
INSERT INTO `strategy_rule` VALUES (5, 10001, 101, '库存不足兜底', 2, 'rule_lucky', '1,100', '2025-01-18 17:50:19', '2025-01-18 17:50:19');
INSERT INTO `strategy_rule` VALUES (6, 10001, 102, '库存不足兜底', 2, 'rule_lucky', '1,100', '2025-01-18 17:50:19', '2025-01-18 17:50:19');
INSERT INTO `strategy_rule` VALUES (7, 10001, 103, '库存不足兜底', 2, 'rule_lucky', '1,100', '2025-01-18 17:50:19', '2025-01-18 17:51:11');
INSERT INTO `strategy_rule` VALUES (8, 10001, 104, '库存不足兜底', 2, 'rule_lucky', '1,100', '2025-01-18 17:50:19', '2025-01-18 17:50:19');
INSERT INTO `strategy_rule` VALUES (9, 10001, 105, '库存不足兜底', 2, 'rule_lucky', '1,100', '2025-01-18 17:50:19', '2025-01-18 17:50:19');
INSERT INTO `strategy_rule` VALUES (10, 10001, 106, '库存不足兜底', 2, 'rule_lucky', '1,100', '2025-01-18 17:50:19', '2025-01-18 17:50:19');
INSERT INTO `strategy_rule` VALUES (11, 10001, 107, '库存不足兜底', 2, 'rule_lucky', '1,100', '2025-01-18 17:50:19', '2025-01-18 17:50:19');
INSERT INTO `strategy_rule` VALUES (12, 10001, NULL, '积分累积保底', 1, 'rule_weight', '6000:102,103,104,105,106,107', '2025-01-18 17:56:09', '2025-01-18 17:56:09');

SET FOREIGN_KEY_CHECKS = 1;
