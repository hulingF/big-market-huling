package com.huling.api;

import com.huling.api.dto.RaffleAwardListRequestDTO;
import com.huling.api.dto.RaffleAwardListResponseDTO;
import com.huling.api.dto.RaffleStrategyRequestDTO;
import com.huling.api.dto.RaffleStrategyResponseDTO;
import com.huling.api.response.Response;

import java.util.List;

public interface IRaffleStrategyService {

    /**
     * 策略装配接口
     * @param strategyId 策略ID
     * @return 装配结果
     */
    Response<Boolean> strategyArmory(Long strategyId);

    /**
     * 查询奖品列表配置接口
     * @param requestDTO 抽奖奖品列表查询请求
     * @return 奖品列表数据
     */
    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO requestDTO);

    /**
     * 随机抽奖接口
     * @param requestDTO 随机抽奖请求
     * @return 抽奖结果
     */
    Response<RaffleStrategyResponseDTO> randomRaffle(RaffleStrategyRequestDTO requestDTO);

}
