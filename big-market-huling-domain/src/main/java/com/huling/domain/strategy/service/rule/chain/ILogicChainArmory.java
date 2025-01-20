package com.huling.domain.strategy.service.rule.chain;

public interface ILogicChainArmory {

    // 责任链的装配接口方法
    ILogicChain appendNext(ILogicChain next);

    ILogicChain next();

}
