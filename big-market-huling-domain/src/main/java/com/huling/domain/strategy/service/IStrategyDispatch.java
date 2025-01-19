package com.huling.domain.strategy.service;

public interface IStrategyDispatch {

    /**
     * 获取抽奖策略装配后的随机抽奖结果
     * @param strategyId 抽奖策略ID
     * @return 奖品ID
     */
    Long getRandomAwardId(Long strategyId);

    Long getRandomAwardId(Long strategyId, String ruleWeightValue);

}
