package com.huling.infrastructure.dao;

import com.huling.infrastructure.dao.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStrategyRuleDao {
    StrategyRule queryStrategyRule(StrategyRule strategyRuleReq);

    String queryStrategyRuleValue(StrategyRule strategyRule);

}
