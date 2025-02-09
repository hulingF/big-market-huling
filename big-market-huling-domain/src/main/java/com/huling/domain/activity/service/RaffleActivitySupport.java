package com.huling.domain.activity.service;

import com.huling.domain.activity.adapter.repository.IActivityRepository;
import com.huling.domain.activity.model.entity.ActivityCountEntity;
import com.huling.domain.activity.model.entity.ActivityEntity;
import com.huling.domain.activity.model.entity.ActivitySkuEntity;
import com.huling.domain.activity.service.rule.IActionChain;
import com.huling.domain.activity.service.rule.factory.DefaultActivityChainFactory;

public class RaffleActivitySupport {

    protected DefaultActivityChainFactory defaultActivityChainFactory;

    protected IActivityRepository activityRepository;

    public RaffleActivitySupport(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
        this.activityRepository = activityRepository;
        this.defaultActivityChainFactory = defaultActivityChainFactory;
    }

    public IActionChain openActionChain() {
        return defaultActivityChainFactory.openActionChain();
    }

    public ActivitySkuEntity queryActivitySku(Long sku) {
        return activityRepository.queryActivitySku(sku);
    }

    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        return activityRepository.queryRaffleActivityByActivityId(activityId);
    }

    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        return activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
    }

}