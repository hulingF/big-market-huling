package com.huling.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleStrategyRequestDTO {

    // 抽奖策略ID
    private Long strategyId;

}