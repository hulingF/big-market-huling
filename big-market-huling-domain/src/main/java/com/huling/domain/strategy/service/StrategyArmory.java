package com.huling.domain.strategy.service;

import com.huling.domain.strategy.apapter.repository.IStrategyRepository;
import com.huling.domain.strategy.model.entity.StrategyAwardEntity;
import com.huling.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

@Slf4j
@Service
public class StrategyArmory implements IStrategyArmory {

    @Resource
    private IStrategyRepository repository;

    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {
        // 1.获取抽奖策略奖品明细配置
        List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);
        // 2.获取抽奖概率总和
        BigDecimal total = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (total.compareTo(new BigDecimal("100")) != 0) return false;
        // 3.获取抽奖概率范围分布
        List<Long> strategyAwardSearchRateTables = new ArrayList<>(Constants.AWARD_RATE_RANGE);
        for (StrategyAwardEntity strategyAwardEntity : strategyAwardEntities) {
            Long awardId = strategyAwardEntity.getAwardId();
            int awardRateRange = strategyAwardEntity.getAwardRate().multiply(new BigDecimal(Constants.AWARD_RATE_FACTOR)).setScale(0, RoundingMode.CEILING).intValue();
            log.info("奖品{} 概率范围{}", awardId, awardRateRange);
            for (int i = 0; i < awardRateRange; i++) {
                strategyAwardSearchRateTables.add(awardId);
            }
        }
        // 4.打乱抽奖概率范围分布
        Collections.shuffle(strategyAwardSearchRateTables);
        // 5.生成策略抽奖范围表
        Map<Integer, Long> shuffleStrategyAwardSearchRateTable = new HashMap<>();
        for (int i = 0; i < strategyAwardSearchRateTables.size(); i++) {
            shuffleStrategyAwardSearchRateTable.put(i, strategyAwardSearchRateTables.get(i));
        }
        // 6.持久化到Redis中
        repository.storeStrategyAwardSearchRateTable(strategyId, shuffleStrategyAwardSearchRateTable.size(), shuffleStrategyAwardSearchRateTable);
        return true;
    }

    @Override
    public Long getRandomAwardId(Long strategyId) {
        // 1.获取抽奖策略范围值
        int rateRange = repository.getRateRange(strategyId);
        // 2.随机抽奖
        return repository.getStrategyAwardAssemble(strategyId, new SecureRandom().nextInt(rateRange));
    }
}
