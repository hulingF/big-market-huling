package com.huling.domain.strategy.service;

import com.huling.domain.strategy.apapter.repository.IStrategyRepository;
import com.huling.domain.strategy.model.entity.RaffleAwardEntity;
import com.huling.domain.strategy.model.entity.RaffleFactorEntity;
import com.huling.domain.strategy.model.entity.RuleActionEntity;
import com.huling.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.huling.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import com.huling.domain.strategy.service.rule.chain.ILogicChain;
import com.huling.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import com.huling.types.enums.ResponseCode;
import com.huling.types.exception.AppException;
import org.apache.commons.lang3.StringUtils;

public abstract class AbstractRaffleStrategy implements IRaffleStrategy {

    protected IStrategyRepository repository;
    protected DefaultChainFactory defaultLogicFactory;

    public AbstractRaffleStrategy(IStrategyRepository repository, DefaultChainFactory defaultLogicFactory) {
        this.repository = repository;
        this.defaultLogicFactory = defaultLogicFactory;
    }

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {
        // 1.参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (strategyId == null || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        // 2.执行责任链抽奖
        ILogicChain logicChain = defaultLogicFactory.openLogicChain(strategyId);
        Long awardId = logicChain.logic(userId, strategyId);
        // 3.查询奖品规则模型
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId, awardId);
        // 4.抽奖中奖品规则过滤
        RuleActionEntity<RuleActionEntity.RaffleCenterEntity> ruleActionCenterEntity = this.doCheckRaffleCenterLogic(RaffleFactorEntity.builder()
                .userId(userId)
                .strategyId(strategyId)
                .awardId(awardId)
                .build(), strategyAwardRuleModelVO.raffleCenterRuleModelList());
        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionCenterEntity.getCode())){
            // TODO:这里不能直接返回，需要经过抽奖后的流程
            return RaffleAwardEntity.builder()
                    .awardDesc("抽奖中规则拦截，通过抽奖后规则 rule_luck_award 走兜底奖励")
                    .build();
        }
        // 5.返回默认抽中奖品
        return RaffleAwardEntity.builder()
                .strategyId(strategyId)
                .awardId(awardId)
                .build();
    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleCenterEntity> doCheckRaffleCenterLogic(RaffleFactorEntity raffleFactorEntity, String... logics);

}
