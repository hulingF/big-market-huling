package com.huling.domain.activity.service.armory;

import java.util.Date;

public interface IActivityDispatch {

    /**
     * 扣减活动sku缓存库存
     *
     * @param sku 活动SKU
     * @param endDateTime 活动结束时间，根据结束时间设置加锁的key为结束时间
     * @return 扣减结果
     */
    boolean subtractionActivitySkuStock(Long sku, Date endDateTime);

}
