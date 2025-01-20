package com.huling.domain.strategy.service.rule.chain;

import com.huling.domain.strategy.service.rule.chain.factory.DefaultChainFactory;

public interface ILogicChain extends ILogicChainArmory {

    /**
     * 责任链过滤执行
     * @param userId 用户ID
     * @param strategyId 策略ID
     * @return StrategyAwardVO：奖品ID和奖品类型
     */
    DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId);

}
