package com.huling.infrastructure.dao;

import com.huling.infrastructure.dao.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStrategyAwardDao {

    List<StrategyAward> queryStrategyAwardListByStrategyId(Long strategyId);

}
