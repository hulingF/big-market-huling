package com.huling.domain.activity.service.rule;

import com.huling.domain.activity.model.entity.ActivityCountEntity;
import com.huling.domain.activity.model.entity.ActivityEntity;
import com.huling.domain.activity.model.entity.ActivitySkuEntity;

public interface IActionChain extends IActionChainArmory {

    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);

}
