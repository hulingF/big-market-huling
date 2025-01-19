package com.huling.domain.strategy.apapter.repository;

import com.huling.domain.strategy.model.entity.StrategyAwardEntity;
import com.huling.domain.strategy.model.entity.StrategyEntity;
import com.huling.domain.strategy.model.entity.StrategyRuleEntity;

import java.util.List;
import java.util.Map;

/**
 * 抽奖策略仓储接口
 */
public interface IStrategyRepository {

    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardSearchRateTable(String key, Integer rateRange, Map<Integer, Long> strategyAwardSearchRateTable);

    int getRateRange(String key);

    Long getStrategyAwardAssemble(String key, int rateKey);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Long awardId, String ruleModel);
}
