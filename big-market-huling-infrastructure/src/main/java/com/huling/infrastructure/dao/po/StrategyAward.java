package com.huling.infrastructure.dao.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 抽奖策略奖品明细
 */
@Data
public class StrategyAward {

    /** 自增ID */
    private Long id;
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖奖品ID */
    private Long awardId;
    /** 抽奖奖品标题 */
    private String awardTitle;
    /** 抽奖奖品副标题 */
    private String awardSubtitle;
    /** 奖品库存总量 */
    private Integer awardCount;
    /** 奖品库存剩余 */
    private Integer awardCountSurplus;
    /** 奖品中奖概率 */
    private BigDecimal awardRate;
    /** 奖品规则模型 */
    private String ruleModels;
    /** 奖品排序位次 */
    private Integer sort;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
