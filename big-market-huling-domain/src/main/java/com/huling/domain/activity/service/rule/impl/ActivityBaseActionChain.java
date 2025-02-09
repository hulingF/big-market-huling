package com.huling.domain.activity.service.rule.impl;

import com.huling.domain.activity.model.entity.ActivityCountEntity;
import com.huling.domain.activity.model.entity.ActivityEntity;
import com.huling.domain.activity.model.entity.ActivitySkuEntity;
import com.huling.domain.activity.service.rule.AbstractActionChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("activity_base_action")
public class ActivityBaseActionChain extends AbstractActionChain {

    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {

        log.info("活动责任链-基础信息【有效期、状态】校验开始。");

        return next().action(activitySkuEntity, activityEntity, activityCountEntity);
    }

}
