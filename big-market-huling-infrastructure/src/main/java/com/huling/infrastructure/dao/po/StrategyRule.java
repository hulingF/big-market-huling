package com.huling.infrastructure.dao.po;

import lombok.Data;

import java.util.Date;

/**
 * 抽奖策略规则
 */
@Data
public class StrategyRule {

    /** 自增ID */
    private Long id;
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖奖品ID */
    private Long awardId;
    /** 抽奖规则描述 */
    private String ruleDesc;
    /** 抽奖规则类型：1-策略规则、2-奖品规则 */
    private Integer ruleType;
    /** 抽奖规则模型：rule_random-随机值计算、rule_lock-N次解锁... */
    private String ruleModel;
    /** 抽奖规则物料 */
    private String ruleValue;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
