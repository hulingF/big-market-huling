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
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_BLACKLIST)
public class RuleBackListLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepository repository;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-黑名单 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());
        // 1.查询黑名单规则配置 100:user1,user2,user3
        String ruleValue = repository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());
        String[] splitRuleValue = ruleValue.split(Constants.COLON);
        Long awardId = Long.parseLong(splitRuleValue[0]);
        // 2.过滤黑名单用户
        String userId = ruleMatterEntity.getUserId();
        String[] userBlackIds = splitRuleValue[1].split(Constants.SPLIT);
        for (String userBlackId : userBlackIds) {
            if (userId.equals(userBlackId)) {
                return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                        .ruleModel(DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode())
                        .data(RuleActionEntity.RaffleBeforeEntity.builder()
                                .strategyId(ruleMatterEntity.getStrategyId())
                                .awardId(awardId)
                                .build())
                        .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                        .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                        .build();
            }
        }
        // 3.黑名单规则放行
        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }

}
