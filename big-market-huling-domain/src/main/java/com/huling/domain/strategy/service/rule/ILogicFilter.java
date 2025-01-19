package com.huling.domain.strategy.service.rule;

import com.huling.domain.strategy.model.entity.RuleActionEntity;
import com.huling.domain.strategy.model.entity.RuleMatterEntity;

public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {

    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);

}
