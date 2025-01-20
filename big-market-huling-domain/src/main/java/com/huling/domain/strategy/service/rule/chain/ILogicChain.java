package com.huling.domain.strategy.service.rule.chain;

public interface ILogicChain extends ILogicChainArmory {

    /**
     * 责任链过滤执行
     * @param userId 用户ID
     * @param strategyId 策略ID
     * @return 奖品ID
     */
    Long logic(String userId, Long strategyId);

}
