package com.huling.domain.strategy.service;

import com.huling.domain.strategy.model.entity.RaffleAwardEntity;
import com.huling.domain.strategy.model.entity.RaffleFactorEntity;

public interface IRaffleStrategy {

    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);

}
