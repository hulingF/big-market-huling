package com.huling.domain.strategy.model.entity;

import lombok.Data;

@Data
public class RuleMatterEntity {

    /** 用户ID */
    private String userId;
    /** 策略ID */
    private Long strategyId;
    /** 抽奖奖品ID【可选项，规则类型为策略1则不需要奖品ID】 */
    private Long awardId;
    /** 抽奖规则模型 */
    private String ruleModel;

}
