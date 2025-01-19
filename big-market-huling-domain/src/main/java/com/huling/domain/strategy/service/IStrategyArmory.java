package com.huling.domain.strategy.service;

/**
 * 策略计算装配接口
 */
public interface IStrategyArmory {

    /**
     * 装配抽奖策略配置「触发的时机可以为活动审核通过后进行调用」
     * @param strategyId 抽奖策略ID
     * @return 装配结果
     */
    boolean assembleLotteryStrategy(Long strategyId);

    /**
     * 获取抽奖策略装配后的随机抽奖结果
     * @param strategyId 抽奖策略ID
     * @return 奖品ID
     */
    Long getRandomAwardId(Long strategyId);

}
