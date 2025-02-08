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

 Date: 08/02/2025 14:24:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `award_id` bigint NOT NULL COMMENT '抽奖奖品ID',
  `award_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '奖品对接标识-对应发奖策略',
  `award_config` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '奖品配置信息',
  `award_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '奖品内容描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of award
-- ----------------------------
INSERT INTO `award` VALUES (1, 101, 'user_credit_random', '1,100', '用户积分【优先透彻规则范围，没有则走配置】', '2025-01-18 19:51:49', '2025-01-18 19:51:49');
INSERT INTO `award` VALUES (2, 102, 'experience_card', '1', '1天体验卡', '2025-01-18 19:53:04', '2025-01-18 19:53:21');
INSERT INTO `award` VALUES (3, 103, 'experience_card', '3', '3天体验卡', '2025-01-18 19:53:37', '2025-01-18 19:53:37');
INSERT INTO `award` VALUES (4, 104, 'experience_card', '5', '5天体验卡', '2025-01-18 19:53:56', '2025-01-18 19:53:56');
INSERT INTO `award` VALUES (5, 105, 'openai_model', 'GPT4o', 'OpenAI增加模型', '2025-01-18 19:54:24', '2025-01-18 19:56:37');
INSERT INTO `award` VALUES (6, 106, 'openai_model', 'DELL', 'OpenAI增加模型', '2025-01-18 19:55:02', '2025-01-18 19:56:43');
INSERT INTO `award` VALUES (7, 107, 'openai_model', 'GPT4o,DELL', 'OpenAI增加模型', '2025-01-18 19:56:17', '2025-01-18 19:56:49');
INSERT INTO `award` VALUES (8, 100, 'user_credit_blacklist', '1', '黑名单固定1积分', '2025-01-19 19:16:35', '2025-01-19 19:16:35');

-- ----------------------------
-- Table structure for raffle_activity
-- ----------------------------
DROP TABLE IF EXISTS `raffle_activity`;
CREATE TABLE `raffle_activity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
  `activity_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动描述',
  `begin_date_time` datetime NOT NULL COMMENT '开始时间',
  `end_date_time` datetime NOT NULL COMMENT '结束时间',
  `stock_count` int NOT NULL COMMENT '库存总量',
  `stock_count_surplus` int NOT NULL COMMENT '剩余库存',
  `activity_count_id` bigint NOT NULL COMMENT '活动参与次数配置',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `idx_begin_date_time`(`begin_date_time` ASC) USING BTREE,
  INDEX `idx_end_date_time`(`end_date_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '抽奖活动表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of raffle_activity
-- ----------------------------

-- ----------------------------
-- Table structure for raffle_activity_count
-- ----------------------------
DROP TABLE IF EXISTS `raffle_activity_count`;
CREATE TABLE `raffle_activity_count`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_count_id` bigint NOT NULL COMMENT '活动次数编号',
  `total_count` int NOT NULL COMMENT '总次数',
  `day_count` int NOT NULL COMMENT '日次数',
  `month_count` int NOT NULL COMMENT '月次数',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_activity_count_id`(`activity_count_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '抽奖活动次数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of raffle_activity_count
-- ----------------------------

-- ----------------------------
-- Table structure for rule_tree
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree`;
CREATE TABLE `rule_tree`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tree_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则树ID',
  `tree_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则树名字',
  `tree_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则树描述',
  `tree_node_rule_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则树根入口规则',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule_tree
-- ----------------------------
INSERT INTO `rule_tree` VALUES (1, 'tree_lock_1', '规则树1', '规则树1', 'rule_lock', '2025-01-20 19:01:16', '2025-02-04 19:23:27');
INSERT INTO `rule_tree` VALUES (2, 'tree_lock_2', '规则树2', '规则树2', 'rule_lock', '2025-02-04 19:23:20', '2025-02-04 19:26:39');
INSERT INTO `rule_tree` VALUES (3, 'tree_luck_award', '规则树3', '规则树3', 'rule_stock', '2025-02-04 19:27:06', '2025-02-04 19:27:06');

-- ----------------------------
-- Table structure for rule_tree_node
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree_node`;
CREATE TABLE `rule_tree_node`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tree_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则树ID',
  `rule_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则Key',
  `rule_desc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则描述',
  `rule_value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则配置',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule_tree_node
-- ----------------------------
INSERT INTO `rule_tree_node` VALUES (1, 'tree_lock_1', 'rule_lock', '限定用户已完成N次抽奖后解锁', '1', '2025-01-20 19:01:52', '2025-02-04 19:23:34');
INSERT INTO `rule_tree_node` VALUES (2, 'tree_lock_1', 'rule_luck_award', '兜底奖品随机积分', '101:1,100', '2025-01-20 19:02:48', '2025-02-04 19:23:37');
INSERT INTO `rule_tree_node` VALUES (3, 'tree_lock_1', 'rule_stock', '库存扣减规则', NULL, '2025-01-20 19:03:49', '2025-02-04 19:23:40');
INSERT INTO `rule_tree_node` VALUES (4, 'tree_lock_2', 'rule_lock', '限定用户已完成N次抽奖后解锁', '2', '2025-02-04 19:25:20', '2025-02-04 19:34:04');
INSERT INTO `rule_tree_node` VALUES (5, 'tree_lock_2', 'rule_luck_award', '兜底奖品随机积分', '101:1,100', '2025-02-04 19:25:56', '2025-02-04 19:25:56');
INSERT INTO `rule_tree_node` VALUES (6, 'tree_lock_2', 'rule_stock', '库存扣减规则', NULL, '2025-02-04 19:26:24', '2025-02-04 19:26:24');
INSERT INTO `rule_tree_node` VALUES (7, 'tree_luck_award', 'rule_stock', '库存扣减规则', NULL, '2025-02-04 19:28:04', '2025-02-04 19:28:04');
INSERT INTO `rule_tree_node` VALUES (8, 'tree_luck_award', 'rule_luck_award', '兜底奖品随机积分', '101:1,100', '2025-02-04 19:28:36', '2025-02-04 19:28:36');

-- ----------------------------
-- Table structure for rule_tree_node_line
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree_node_line`;
CREATE TABLE `rule_tree_node_line`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tree_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则树ID',
  `rule_node_from` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'From节点规则Key',
  `rule_node_to` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'To节点规则Key',
  `rule_limit_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '限定类型：EQUAL',
  `rule_limit_value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '限定值：ALLOW、TAKE_OVER',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule_tree_node_line
-- ----------------------------
INSERT INTO `rule_tree_node_line` VALUES (1, 'tree_lock_1', 'rule_lock', 'rule_stock', 'EQUAL', 'ALLOW', '2025-01-20 19:05:20', '2025-02-04 19:30:25');
INSERT INTO `rule_tree_node_line` VALUES (2, 'tree_lock_1', 'rule_lock', 'rule_luck_award', 'EQUAL', 'TAKE_OVER', '2025-01-20 19:05:57', '2025-02-04 19:30:22');
INSERT INTO `rule_tree_node_line` VALUES (3, 'tree_lock_1', 'rule_stock', 'rule_luck_award', 'EQUAL', 'TAKE_OVER', '2025-01-20 19:07:06', '2025-02-04 19:30:19');
INSERT INTO `rule_tree_node_line` VALUES (4, 'tree_lock_2', 'rule_lock', 'rule_stock', 'EQUAL', 'ALLOW', '2025-02-04 19:30:15', '2025-02-04 19:30:15');
INSERT INTO `rule_tree_node_line` VALUES (5, 'tree_lock_2', 'rule_lock', 'rule_luck_award', 'EQUAL', 'TAKE_OVER', '2025-02-04 19:31:02', '2025-02-04 19:31:02');
INSERT INTO `rule_tree_node_line` VALUES (6, 'tree_lock_2', 'rule_stock', 'rule_luck_award', 'EQUAL', 'TAKE_OVER', '2025-02-04 19:31:42', '2025-02-04 19:31:42');
INSERT INTO `rule_tree_node_line` VALUES (7, 'tree_luck_award', 'rule_stock', 'rule_luck_award', 'EQUAL', 'TAKE_OVER', '2025-02-04 19:32:32', '2025-02-04 19:32:32');

-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `strategy_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '抽奖策略描述',
  `rule_models` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '策略规则模型，冗余存储',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of strategy
-- ----------------------------
INSERT INTO `strategy` VALUES (1, 10001, '抽奖策略测试A', 'rule_blacklist,rule_weight', '2025-01-18 17:32:13', '2025-01-20 10:19:02');
INSERT INTO `strategy` VALUES (2, 10002, '抽奖策略测试B', 'rule_blacklist,rule_weight', '2025-01-18 17:32:13', '2025-01-20 10:19:02');

-- ----------------------------
-- Table structure for strategy_award
-- ----------------------------
DROP TABLE IF EXISTS `strategy_award`;
CREATE TABLE `strategy_award`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `award_id` bigint NOT NULL COMMENT '抽奖奖品ID',
  `award_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '抽奖奖品标题',
  `award_subtitle` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '抽奖奖品副标题',
  `award_count` int NOT NULL COMMENT '奖品库存总量',
  `award_count_surplus` int NOT NULL COMMENT '奖品库存剩余',
  `award_rate` decimal(4, 2) NOT NULL COMMENT '奖品中奖概率',
  `rule_models` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '奖品规则模型，冗余存储',
  `sort` int NOT NULL DEFAULT 0 COMMENT '奖品排序位次',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of strategy_award
-- ----------------------------
INSERT INTO `strategy_award` VALUES (1, 10001, 101, '随机积分', NULL, 50000, 50000, 80.00, 'rule_random,rule_luck_award', 0, '2025-01-18 17:33:47', '2025-01-20 10:39:57');
INSERT INTO `strategy_award` VALUES (2, 10001, 102, '1天体验卡', NULL, 10000, 10000, 10.00, 'rule_luck_award', 1, '2025-01-18 17:35:08', '2025-01-19 23:06:34');
INSERT INTO `strategy_award` VALUES (3, 10001, 103, '3天体验卡', NULL, 5000, 5000, 5.00, 'rule_luck_award', 2, '2025-01-18 17:36:28', '2025-01-19 23:06:40');
INSERT INTO `strategy_award` VALUES (4, 10001, 104, '5天体验卡', NULL, 5000, 5000, 3.00, 'rule_luck_award', 3, '2025-01-18 17:39:12', '2025-01-19 23:07:07');
INSERT INTO `strategy_award` VALUES (5, 10001, 105, '增加GPT4o模型', '抽奖1次解锁', 2000, 2000, 1.00, 'rule_lock,rule_luck_award', 4, '2025-01-18 17:40:41', '2025-01-20 10:39:52');
INSERT INTO `strategy_award` VALUES (6, 10001, 106, '增加DELL画图模型', '抽奖3次检索', 500, 500, 0.90, 'rule_lock.rule_luck_award', 5, '2025-01-18 17:41:52', '2025-01-19 23:07:17');
INSERT INTO `strategy_award` VALUES (7, 10001, 107, '增加全部模型', '抽奖10次解锁', 100, 100, 0.10, 'rule_lock,rule_luck_award', 6, '2025-01-18 17:42:40', '2025-01-19 23:07:22');
INSERT INTO `strategy_award` VALUES (8, 10002, 101, '随机积分', NULL, 50000, 49997, 80.00, 'tree_luck_award', 0, '2025-01-18 17:33:47', '2025-02-04 19:16:53');
INSERT INTO `strategy_award` VALUES (9, 10002, 102, '1天体验卡', NULL, 10000, 10000, 10.00, 'tree_luck_award', 1, '2025-01-18 17:35:08', '2025-02-04 19:16:56');
INSERT INTO `strategy_award` VALUES (10, 10002, 103, '3天体验卡', NULL, 5000, 4997, 5.00, 'tree_luck_award', 2, '2025-01-18 17:36:28', '2025-02-04 19:16:59');
INSERT INTO `strategy_award` VALUES (11, 10002, 104, '5天体验卡', NULL, 5000, 5000, 3.00, 'tree_luck_award', 3, '2025-01-18 17:39:12', '2025-02-04 19:17:02');
INSERT INTO `strategy_award` VALUES (12, 10002, 105, '增加GPT4o模型', '抽奖1次解锁', 2000, 2000, 1.00, 'tree_lock_1', 4, '2025-01-18 17:40:41', '2025-02-04 19:17:45');
INSERT INTO `strategy_award` VALUES (13, 10002, 106, '增加DELL画图模型', '抽奖3次检索', 500, 500, 0.90, 'tree_lock_1', 5, '2025-01-18 17:41:52', '2025-02-04 19:17:50');
INSERT INTO `strategy_award` VALUES (14, 10002, 107, '增加全部模型', '抽奖10次解锁', 100, 100, 0.10, 'tree_lock_2', 6, '2025-01-18 17:42:40', '2025-02-04 19:17:53');

-- ----------------------------
-- Table structure for strategy_rule
-- ----------------------------
DROP TABLE IF EXISTS `strategy_rule`;
CREATE TABLE `strategy_rule`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `award_id` bigint NULL DEFAULT NULL COMMENT '抽奖奖品ID',
  `rule_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '抽奖规则描述',
  `rule_type` tinyint NOT NULL COMMENT '抽奖规则类型：1-策略规则、2-奖品规则',
  `rule_model` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '抽奖规则模型：rule_random-随机值计算、rule_lock-N次解锁...',
  `rule_value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '抽奖规则物料',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of strategy_rule
-- ----------------------------
INSERT INTO `strategy_rule` VALUES (1, 10001, 101, '随机值计算', 2, 'rule_random', '1,100', '2025-01-18 17:47:00', '2025-01-18 17:47:00');
INSERT INTO `strategy_rule` VALUES (2, 10001, 105, '抽奖1次解锁', 2, 'rule_lock', '1', '2025-01-18 17:48:23', '2025-01-18 17:48:23');
INSERT INTO `strategy_rule` VALUES (3, 10001, 106, '抽奖3次解锁', 2, 'rule_lock', '3', '2025-01-18 17:48:58', '2025-01-18 17:48:58');
INSERT INTO `strategy_rule` VALUES (4, 10001, 107, '抽奖10次解锁', 2, 'rule_lock', '10', '2025-01-18 17:49:36', '2025-01-18 17:49:36');
INSERT INTO `strategy_rule` VALUES (5, 10001, 101, '库存不足兜底', 2, 'rule_luck_award', '1,10', '2025-01-18 17:50:19', '2025-01-20 12:55:54');
INSERT INTO `strategy_rule` VALUES (6, 10001, 102, '库存不足兜底', 2, 'rule_luck_award', '1,10', '2025-01-18 17:50:19', '2025-01-20 12:55:56');
INSERT INTO `strategy_rule` VALUES (7, 10001, 103, '库存不足兜底', 2, 'rule_luck_award', '1,10', '2025-01-18 17:50:19', '2025-01-20 12:55:57');
INSERT INTO `strategy_rule` VALUES (8, 10001, 104, '库存不足兜底', 2, 'rule_luck_award', '1,10', '2025-01-18 17:50:19', '2025-01-20 12:55:58');
INSERT INTO `strategy_rule` VALUES (9, 10001, 105, '库存不足兜底', 2, 'rule_luck_award', '1,10', '2025-01-18 17:50:19', '2025-01-20 12:55:59');
INSERT INTO `strategy_rule` VALUES (10, 10001, 106, '库存不足兜底', 2, 'rule_luck_award', '1,10', '2025-01-18 17:50:19', '2025-01-20 12:55:59');
INSERT INTO `strategy_rule` VALUES (11, 10001, 107, '库存不足兜底', 2, 'rule_luck_award', '1,10', '2025-01-18 17:50:19', '2025-01-20 12:56:02');
INSERT INTO `strategy_rule` VALUES (12, 10001, NULL, '累积积分保底', 1, 'rule_weight', '6000:102,103,104,105,106,107', '2025-01-18 17:56:09', '2025-01-19 19:19:29');
INSERT INTO `strategy_rule` VALUES (13, 10001, NULL, '黑名单过滤', 1, 'rule_blacklist', '100:user1,user2,user3', '2025-01-19 19:15:26', '2025-01-19 19:19:05');
INSERT INTO `strategy_rule` VALUES (14, 10002, NULL, '累积积分保底', 1, 'rule_weight', '6000:102,103,104,105,106,107', '2025-01-18 17:56:09', '2025-01-19 19:19:29');
INSERT INTO `strategy_rule` VALUES (15, 10002, NULL, '黑名单过滤', 1, 'rule_blacklist', '100:user1,user2,user3', '2025-01-19 19:15:26', '2025-01-19 19:19:05');

SET FOREIGN_KEY_CHECKS = 1;
