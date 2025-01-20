package com.huling.domain.strategy.service.rule.chain.impl;

import com.huling.domain.strategy.service.armory.IStrategyDispatch;
import com.huling.domain.strategy.service.rule.chain.AbstractLogicChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component("default")
public class DefaultLogicChain extends AbstractLogicChain {

    @Resource
    protected IStrategyDispatch strategyDispatch;

    @Override
    protected String ruleModel() {
        return "default";
    }

    @Override
    public Long logic(String userId, Long strategyId) {
        Long awardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
        return awardId;
    }
}