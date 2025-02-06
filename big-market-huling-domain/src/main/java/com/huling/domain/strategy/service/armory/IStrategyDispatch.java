package com.huling.domain.strategy.service.armory;

public interface IStrategyDispatch {

    /**
     * 获取抽奖策略装配后的随机抽奖结果
     * @param strategyId 抽奖策略ID
     * @return 奖品ID
     */
    Long getRandomAwardId(Long strategyId);

    /**
     * 获取权重抽奖策略装配后的随机抽奖结果
     * @param strategyId 策略ID
     * @param ruleWeightValue 权重规则Key
     * @return 奖品ID
     */
    Long getRandomAwardId(Long strategyId, String ruleWeightValue);

    /**
     * Redis预减库存
     * @param strategyId 策略ID
     * @param awardId 奖品ID
     * @return 扣减库存结果
     */
    Boolean subtractionAwardStock(Long strategyId, Long awardId);

}
