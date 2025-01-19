package com.huling.infrastructure.dao;

import com.huling.infrastructure.dao.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStrategyDao {
    Strategy queryStrategyByStrategyId(Long strategyId);
}
