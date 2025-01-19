package com.huling.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS("0000", "成功"),
    UN_ERROR("0001", "未知失败"),
    ILLEGAL_PARAMETER("0002", "非法参数"),
    AWARD_RATE_SUM_ILLEGAL("ERR_BIZ_001", "业务异常，抽奖概率总和计算不为 100"),
    STRATEGY_RULE_WEIGHT_IS_NULL("ERR_BIZ_002", "业务异常，策略规则中 rule_weight 权重规则已使用但未配置"),
    ;

    private String code;
    private String info;

}
