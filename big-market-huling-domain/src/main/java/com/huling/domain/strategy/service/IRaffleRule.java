package com.huling.domain.strategy.service;

import java.util.Map;

public interface IRaffleRule {

    /**
     * 根据规则树ID集合查询奖品解锁次数配置「部分奖品需要抽奖N次解锁」
     *
     * @param treeIds 规则树ID值
     * @return key 规则树ID，value 解锁次数
     */
    Map<String, Integer> queryAwardRuleLockCount(String[] treeIds);

}