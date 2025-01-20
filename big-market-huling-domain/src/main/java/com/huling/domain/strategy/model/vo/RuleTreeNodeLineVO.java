package com.huling.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeLineVO {

    /** 规则树ID */
    private String treeId;
    /** 规则Key节点 From */
    private String ruleNodeFrom;
    /** 规则Key节点 To */
    private String ruleNodeTo;
    /** 限定类型：EQUAL */
    private RuleLimitTypeVO ruleLimitType;
    /** 限定值：ALLOW、TAKE_OVER */
    private RuleLogicCheckTypeVO ruleLimitValue;

}