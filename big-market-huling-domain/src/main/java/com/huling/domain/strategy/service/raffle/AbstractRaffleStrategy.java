package com.huling.domain.strategy.service.raffle;

import com.huling.domain.strategy.apapter.repository.IStrategyRepository;
import com.huling.domain.strategy.model.entity.RaffleAwardEntity;
import com.huling.domain.strategy.model.entity.RaffleFactorEntity;
import com.huling.domain.strategy.model.entity.RuleActionEntity;
import com.huling.domain.strategy.model.entity.StrategyEntity;
import com.huling.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.huling.domain.strategy.service.armory.IStrategyDispatch;
import com.huling.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.huling.types.enums.ResponseCode;
import com.huling.types.exception.AppException;
import org.apache.commons.lang3.StringUtils;

public abstract class AbstractRaffleStrategy implements IRaffleStrategy {

    protected IStrategyRepository repository;
    protected IStrategyDispatch strategyDispatch;

    public AbstractRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch) {
        this.repository = repository;
        this.strategyDispatch = strategyDispatch;
    }

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {
        // 1.参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (strategyId == null || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        // 2.抽奖策略查询
        StrategyEntity strategyEntity = repository.queryStrategyEntityByStrategyId(strategyId);
        // 3.抽奖前规则过滤
        RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> ruleActionEntity = this.doCheckRaffleBeforeLogic(RaffleFactorEntity.builder().userId(userId).strategyId(strategyId).build(), strategyEntity.ruleModels());
        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionEntity.getCode())) {
            if (DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode().equals(ruleActionEntity.getRuleModel())) {
                // 黑名单返回固定的奖品ID
                return RaffleAwardEntity.builder()
                        .awardId(ruleActionEntity.getData().getAwardId())
                        .build();
            } else if (DefaultLogicFactory.LogicModel.RULE_WIGHT.getCode().equals(ruleActionEntity.getRuleModel())) {
                // 权重根据返回的信息进行抽奖
                RuleActionEntity.RaffleBeforeEntity raffleBeforeEntity = ruleActionEntity.getData();
                String ruleWeightValueKey = raffleBeforeEntity.getRuleWeightValueKey();
                Long awardId = strategyDispatch.getRandomAwardId(strategyId, ruleWeightValueKey);
                return RaffleAwardEntity.builder()
                        .awardId(awardId)
                        .build();
            }
        }
        // 4.执行默认抽奖流程
        Long awardId = strategyDispatch.getRandomAwardId(strategyId);
        return RaffleAwardEntity.builder()
                .strategyId(strategyId)
                .awardId(awardId)
                .build();
    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity raffleFactorEntity, String... logics);

}
