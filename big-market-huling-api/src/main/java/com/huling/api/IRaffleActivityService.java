package com.huling.api;

import com.huling.api.dto.ActivityDrawRequestDTO;
import com.huling.api.dto.ActivityDrawResponseDTO;
import com.huling.api.response.Response;

public interface IRaffleActivityService {

    /**
     * 活动装配，数据预热缓存
     * @param activityId 活动ID
     * @return 装配结果
     */
    Response<Boolean> armory(Long activityId);

    /**
     * 活动抽奖
     * @param request 请求对象
     * @return 返回结果
     */
    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request);

}
