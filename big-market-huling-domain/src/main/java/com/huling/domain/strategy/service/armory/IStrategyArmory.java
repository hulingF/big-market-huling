package com.huling.domain.strategy.service.armory;

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

}
