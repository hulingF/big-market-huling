package com.huling.domain.strategy.service.rule.impl;

import com.huling.domain.strategy.apapter.repository.IStrategyRepository;
import com.huling.domain.strategy.model.entity.RuleActionEntity;
import com.huling.domain.strategy.model.entity.RuleMatterEntity;
import com.huling.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.huling.domain.strategy.service.annotation.LogicStrategy;
import com.huling.domain.strategy.service.rule.ILogicFilter;
import com.huling.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.huling.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_WIGHT)
public class RuleWeightLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepository repository;

    // Mock的用户积分值
    public Long userScore = 4500L;

    /**
     * 权重规则过滤；
     * 1. 权重规则格式：4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
     * 2. 解析数据格式：判断哪个范围符合用户的特定抽奖范围
     *
     * @param ruleMatterEntity 规则物料实体对象
     * @return 规则过滤结果
     */
    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-权重范围 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());
        // 1.查询权重规则配置 4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
        String ruleValue = repository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());
        // 2.解析权重规则配置
        Map<Long, String> analyticalValueGroup = getAnalyticalValue(ruleValue);
        if (null == analyticalValueGroup || analyticalValueGroup.isEmpty()) {
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }
        // 3.定位权重范围
        Optional<Long> ruleWeightKey = analyticalValueGroup.keySet().stream()
                .filter(ruleWeight -> ruleWeight <= userScore)
                .max(Long::compareTo);
        if (ruleWeightKey.isPresent()) {
            Long ruleWight = ruleWeightKey.get();
            Long strategyId = ruleMatterEntity.getStrategyId();
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .data(RuleActionEntity.RaffleBeforeEntity.builder()
                            .strategyId(strategyId)
                            .ruleWeightValueKey(String.valueOf(ruleWight))
                            .build())
                    .ruleModel(DefaultLogicFactory.LogicModel.RULE_WIGHT.getCode())
                    .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                    .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                    .build();
        }
        // 4.权重规则放行
        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }

    private Map<Long, String> getAnalyticalValue(String ruleValue) {
        if (StringUtils.isBlank(ruleValue)) return new HashMap<>();
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<Long, String> ruleValueMap = new HashMap<>();
        for (String ruleValueKey : ruleValueGroups) {
            // 检查输入是否为空
            if (ruleValueKey == null || ruleValueKey.isEmpty()) {
                return ruleValueMap;
            }
            // 分割字符串以获取键和值
            String[] parts = ruleValueKey.split(Constants.COLON);
            if (parts.length != 2) {
                throw new IllegalArgumentException("rule_weight invalid input format" + ruleValueKey);
            }
            ruleValueMap.put(Long.parseLong(parts[0]), ruleValueKey);
        }
        return ruleValueMap;
    }


}
