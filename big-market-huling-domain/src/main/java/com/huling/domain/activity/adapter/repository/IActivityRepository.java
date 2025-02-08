package com.huling.domain.activity.adapter.repository;

import com.huling.domain.activity.model.entity.ActivityCountEntity;
import com.huling.domain.activity.model.entity.ActivityEntity;
import com.huling.domain.activity.model.entity.ActivitySkuEntity;

public interface IActivityRepository {

    ActivitySkuEntity queryActivitySku(Long sku);

    ActivityEntity queryRaffleActivityByActivityId(Long activityId);

    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);

}
