package com.huling.domain.strategy.apapter.repository;

import com.huling.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;
import java.util.Map;

/**
 * 抽奖策略仓储接口
 */
public interface IStrategyRepository {

    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardSearchRateTable(Long strategyId, Integer rateRange, Map<Integer, Long> strategyAwardSearchRateTable);

    int getRateRange(Long strategyId);

    Long getStrategyAwardAssemble(Long strategyId, int rateKey);
}
