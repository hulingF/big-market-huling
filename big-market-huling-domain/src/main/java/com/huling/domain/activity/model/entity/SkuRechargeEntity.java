package com.huling.domain.activity.model.entity;

import lombok.Data;

@Data
public class SkuRechargeEntity {

    /** 用户ID */
    private String userId;
    /** 商品SKU */
    private Long sku;
    /** 幂等业务单号，外部谁充值谁透传，这样来保证幂等（多次调用也能确保结果唯一，不会多次充值）。 */
    private String outBusinessNo;

}