package com.huling.test.domin.activity;

import com.alibaba.fastjson.JSON;
import com.huling.domain.activity.model.entity.ActivityOrderEntity;
import com.huling.domain.activity.model.entity.ActivityShopCartEntity;
import com.huling.domain.activity.model.entity.SkuRechargeEntity;
import com.huling.domain.activity.service.IRaffleOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleOrderTest {

    @Resource
    private IRaffleOrder raffleOrder;

    @Test
    public void test_createRaffleActivityOrder() {
        SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
        skuRechargeEntity.setUserId("huling");
        skuRechargeEntity.setSku(200L);
        skuRechargeEntity.setOutBusinessNo("17333");
        String orderId = raffleOrder.createSkuRechargeOrder(skuRechargeEntity);
        log.info("测试结果：{}", orderId);
    }

}
