package com.huling.infrastructure.dao.po;

import lombok.Data;

import java.util.Date;

@Data
public class RuleTreeNodeLine {

    /** 自增ID */
    private Long id;
    /** 规则树ID */
    private String treeId;
    /** 规则Key节点 From */
    private String ruleNodeFrom;
    /** 规则Key节点 To */
    private String ruleNodeTo;
    /** 限定类型：EQUAL */
    private String ruleLimitType;
    /** 限定值：ALLOW、TAKE_OVER */
    private String ruleLimitValue;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
