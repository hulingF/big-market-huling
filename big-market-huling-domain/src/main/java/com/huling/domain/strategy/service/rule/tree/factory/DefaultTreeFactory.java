package com.huling.domain.strategy.service.rule.tree.factory;

import com.huling.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.huling.domain.strategy.model.vo.RuleTreeVO;
import com.huling.domain.strategy.service.rule.tree.ILogicTreeNode;
import com.huling.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import com.huling.domain.strategy.service.rule.tree.factory.engine.impl.DecisionTreeEngine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DefaultTreeFactory {

    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    public DefaultTreeFactory(Map<String, ILogicTreeNode> logicTreeNodeGroup) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
    }

    public IDecisionTreeEngine openLogicTree(RuleTreeVO ruleTreeVO) {
        return new DecisionTreeEngine(logicTreeNodeGroup, ruleTreeVO);
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeActionEntity {
        private RuleLogicCheckTypeVO ruleLogicCheckType;//决定决策树的走向
        private StrategyAwardVO strategyAwardVO;//决策节点计算结果
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardVO {
        /** 奖品ID - 内部流转使用 */
        private Long awardId;
        /** 奖品配置信息 */
        private String awardConfig;
    }

}
