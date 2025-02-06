package com.huling.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleResponseDTO {

    // 奖品ID
    private Long awardId;
    // 排序编号【策略奖品配置的奖品顺序编号】
    private Integer awardIndex;

}