package com.huling.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardListRequestDTO {
    
    // 用户ID
    private String userId;
    // 抽奖活动ID
    private Long activityId;

}