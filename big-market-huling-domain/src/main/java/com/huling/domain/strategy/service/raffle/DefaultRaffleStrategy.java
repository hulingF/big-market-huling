package com.huling.domain.strategy.service.raffle;

import com.huling.domain.strategy.adapter.repository.IStrategyRepository;
import com.huling.domain.strategy.model.entity.StrategyAwardEntity;
import com.huling.domain.strategy.model.vo.RuleTreeVO;
import com.huling.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import com.huling.domain.strategy.model.vo.StrategyAwardStockKeyVO;
import com.huling.domain.strategy.service.AbstractRaffleStrategy;
import com.huling.domain.strategy.service.IRaffleAward;
import com.huling.domain.strategy.service.IRaffleRule;
import com.huling.domain.strategy.service.IRaffleStock;
import com.huling.domain.strategy.service.rule.chain.ILogicChain;
import com.huling.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import com.huling.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import com.huling.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DefaultRaffleStrategy extends AbstractRaffleStrategy implements IRaffleStock, IRaffleAward, IRaffleRule {

    @Resource
    private DefaultChainFactory chainFactory;
    @Resource
    private DefaultTreeFactory treeFactory;

    public DefaultRaffleStrategy(IStrategyRepository repository, DefaultChainFactory defaultChainFactory, DefaultTreeFactory defaultTreeFactory) {
        super(repository, defaultChainFactory, defaultTreeFactory);
    }


    @Override
    public DefaultChainFactory.StrategyAwardVO raffleLogicChain(String userId, Long strategyId) {
        // 执行责任链抽奖
        ILogicChain logicChain = chainFactory.openLogicChain(strategyId);
        return logicChain.logic(userId, strategyId);
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Long awardId) {
        // 查询奖品规则模型 tree_lock_1
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId, awardId);
        if (strategyAwardRuleModelVO == null) {
            return DefaultTreeFactory.StrategyAwardVO.builder().awardId(awardId).build();
        }
        // 查询奖品对应规则树
        RuleTreeVO ruleTreeVO = repository.queryRuleTreeVOByTreeId(strategyAwardRuleModelVO.getRuleModels());
        if (ruleTreeVO == null) {
            throw new RuntimeException("存在抽奖策略配置的规则模型 Key，未在库表 rule_tree、rule_tree_node、rule_tree_line 配置对应的规则树信息 " + strategyAwardRuleModelVO.getRuleModels());
        }
        // 执行规则树引擎
        IDecisionTreeEngine treeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
        return treeEngine.process(userId, strategyId, awardId);
    }

    @Override
    public StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException {
        return repository.takeQueueValue();
    }

    @Override
    public void updateStrategyAwardStock(Long strategyId, Long awardId) {
        repository.updateStrategyAwardStock(strategyId, awardId);
    }

    @Override
    public List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId) {
        return repository.queryStrategyAwardList(strategyId);
    }

    @Override
    public List<StrategyAwardEntity> queryRaffleStrategyAwardListByActivityId(Long activityId) {
        Long strategyId = repository.queryStrategyIdByActivityId(activityId);
        return queryRaffleStrategyAwardList(strategyId);
    }

    @Override
    public Map<String, Integer> queryAwardRuleLockCount(String[] treeIds) {
        return repository.queryAwardRuleLockCount(treeIds);
    }
}
