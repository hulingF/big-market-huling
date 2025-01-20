package com.huling.domain.strategy.service.rule.tree.factory.engine.impl;

import com.huling.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import com.huling.domain.strategy.model.vo.RuleTreeNodeLineVO;
import com.huling.domain.strategy.model.vo.RuleTreeNodeVO;
import com.huling.domain.strategy.model.vo.RuleTreeVO;
import com.huling.domain.strategy.service.rule.tree.ILogicTreeNode;
import com.huling.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import com.huling.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
@Slf4j
public class DecisionTreeEngine implements IDecisionTreeEngine {

    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    private final RuleTreeVO ruleTreeVO;

    public DecisionTreeEngine(Map<String, ILogicTreeNode> logicTreeNodeGroup, RuleTreeVO ruleTreeVO) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
        this.ruleTreeVO = ruleTreeVO;
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Long awardId) {

        // 1.获取决策树基本信息
        String currentNode = ruleTreeVO.getTreeRootRuleNode();
        Map<String, RuleTreeNodeVO> treeNodeMap = ruleTreeVO.getTreeNodeMap();
        // 2.决策树执行
        DefaultTreeFactory.StrategyAwardVO strategyAwardVO = null;
        while (currentNode != null) {
            RuleTreeNodeVO ruleTreeNode = treeNodeMap.get(currentNode);
            // 获取决策树节点
            ILogicTreeNode logicTreeNode = logicTreeNodeGroup.get(ruleTreeNode.getRuleKey());
            // 决策树节点计算
            DefaultTreeFactory.TreeActionEntity logicEntity = logicTreeNode.logic(userId, strategyId, awardId, ruleTreeNode.getRuleValue());
            RuleLogicCheckTypeVO ruleLogicCheckType = logicEntity.getRuleLogicCheckType();
            strategyAwardVO = logicEntity.getStrategyAwardVO();
            log.info("决策树引擎【{}】treeId:{} node:{} code:{}", ruleTreeVO.getTreeName(), ruleTreeVO.getTreeId(), currentNode, ruleLogicCheckType.getCode());
            // 获取下一节点
            currentNode = nextNode(ruleLogicCheckType.getCode(), ruleTreeNode.getTreeNodeLineVOList());
        }
        return strategyAwardVO;
    }

    private String nextNode(String matterValue, List<RuleTreeNodeLineVO> treeNodeLineVOList) {
        // 到达决策树叶子节点如rule_luck_award，返回null
        if (null == treeNodeLineVOList || treeNodeLineVOList.isEmpty()) return null;
        // 遍历决策树节点的所有连线，寻找符合的连线
        for (RuleTreeNodeLineVO nodeLine : treeNodeLineVOList) {
            if (decisionLogic(matterValue, nodeLine)) {
                return nodeLine.getRuleNodeTo();
            }
        }
        // 未找到符合的连线，决策树执行结束，返回null
        //throw new RuntimeException("决策树引擎，nextNode 计算失败，未找到可执行节点！");
        return null;
    }

    private boolean decisionLogic(String matterValue, RuleTreeNodeLineVO nodeLine) {
        switch (nodeLine.getRuleLimitType()) {
            case EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue().getCode());
            // 以下规则暂时不需要实现
            case GT:
            case LT:
            case GE:
            case LE:
            default:
                return false;
        }
    }
}
