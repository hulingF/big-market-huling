package com.huling.domain.strategy.service.armory;

import com.huling.domain.strategy.adapter.repository.IStrategyRepository;
import com.huling.domain.strategy.model.entity.StrategyAwardEntity;
import com.huling.domain.strategy.model.entity.StrategyEntity;
import com.huling.domain.strategy.model.entity.StrategyRuleEntity;
import com.huling.types.common.Constants;
import com.huling.types.enums.ResponseCode;
import com.huling.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

@Slf4j
@Service
public class StrategyArmoryDispatch implements IStrategyArmory, IStrategyDispatch {

    @Resource
    private IStrategyRepository repository;

    @Override
    public boolean assembleLotteryStrategy(Long strategyId) throws AppException {
        // 1.获取抽奖策略奖品明细配置
        List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);

        // 2.缓存装配奖品库存
        for (StrategyAwardEntity strategyAwardEntity : strategyAwardEntities) {
            Long awardId = strategyAwardEntity.getAwardId();
            Integer awardCount = strategyAwardEntity.getAwardCountSurplus();
            // Integer awardCount = strategyAwardEntity.getAwardCount();
            cacheStrategyAwardCount(strategyId, awardId, awardCount);
        }

        // 3.1.全量装配抽奖概率配置
        assembleLotteryStrategy(String.valueOf(strategyId), strategyAwardEntities);
        // 3.2.权重装配抽奖概率配置
        StrategyEntity strategyEntity = repository.queryStrategyEntityByStrategyId(strategyId);
        String ruleWeight = strategyEntity.getRuleWeight();
        if (ruleWeight == null) return true;
        StrategyRuleEntity strategyRuleEntity = repository.queryStrategyRule(strategyId, ruleWeight);
        if (strategyRuleEntity == null) {
            throw new AppException(ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getCode(), ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getInfo());
        }
        Map<String, List<Long>> ruleWeightValueMap = strategyRuleEntity.getRuleWeightValues();
        ruleWeightValueMap.forEach((ruleWight, awardIdList) -> {
            List<StrategyAwardEntity> strategyAwardEntitiesClone = new ArrayList<>(strategyAwardEntities);
            strategyAwardEntitiesClone.removeIf(entity -> !awardIdList.contains(entity.getAwardId()));
            assembleLotteryStrategy(String.valueOf(strategyId).concat("_").concat(ruleWight), strategyAwardEntitiesClone);
        });
        return true;
    }

    @Override
    public boolean assembleLotteryStrategyByActivityId(Long activityId) {
        Long strategyId = repository.queryStrategyIdByActivityId(activityId);
        return assembleLotteryStrategy(strategyId);
    }

    private void assembleLotteryStrategy(String key, List<StrategyAwardEntity> strategyAwardEntities) {
        // 1.获取抽奖概率总和
        BigDecimal total = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 2.获取抽奖概率范围分布
        List<Long> strategyAwardSearchRateTables = new ArrayList<>();
        for (StrategyAwardEntity strategyAwardEntity : strategyAwardEntities) {
            Long awardId = strategyAwardEntity.getAwardId();
            int awardRateRange = strategyAwardEntity.getAwardRate().multiply(new BigDecimal(Constants.AWARD_RATE_FACTOR)).setScale(0, RoundingMode.CEILING).intValue();
            log.info("奖品{} 概率范围{}", awardId, awardRateRange);
            for (int i = 0; i < awardRateRange; i++) {
                strategyAwardSearchRateTables.add(awardId);
            }
        }
        // 3.打乱抽奖概率范围分布
        Collections.shuffle(strategyAwardSearchRateTables);
        // 4.生成策略抽奖范围表
        Map<Integer, Long> shuffleStrategyAwardSearchRateTable = new HashMap<>();
        for (int i = 0; i < strategyAwardSearchRateTables.size(); i++) {
            shuffleStrategyAwardSearchRateTable.put(i, strategyAwardSearchRateTables.get(i));
        }
        // 5.持久化到Redis中
        repository.storeStrategyAwardSearchRateTable(key, shuffleStrategyAwardSearchRateTable.size(), shuffleStrategyAwardSearchRateTable);
    }

    private void cacheStrategyAwardCount(Long strategyId, Long awardId, Integer awardCount) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_COUNT_KEY + strategyId + Constants.UNDERLINE + awardId;
        repository.cacheStrategyAwardCount(cacheKey, awardCount);
    }

    @Override
    public Long getRandomAwardId(Long strategyId) {
        // 1.全量概率查找表的key
        String key = String.valueOf(strategyId);
        // 2.获取抽奖策略范围值
        int rateRange = repository.getRateRange(key);
        // 3.随机抽奖
        return repository.getStrategyAwardAssemble(key, new SecureRandom().nextInt(rateRange));
    }

    @Override
    public Long getRandomAwardId(Long strategyId, String ruleWeightValue) {
        // 1.权重概率查找表的key
        String key = String.valueOf(strategyId).concat("_").concat(ruleWeightValue);
        // 2.获取抽奖策略范围值
        int rateRange = repository.getRateRange(key);
        // 3.随机抽奖
        return repository.getStrategyAwardAssemble(key, new SecureRandom().nextInt(rateRange));
    }

    @Override
    public Boolean subtractionAwardStock(Long strategyId, Long awardId) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_COUNT_KEY + strategyId + Constants.UNDERLINE + awardId;
        return repository.subtractionAwardStock(cacheKey);
    }
}
