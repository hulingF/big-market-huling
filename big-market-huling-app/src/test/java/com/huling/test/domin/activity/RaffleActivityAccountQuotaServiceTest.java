package com.huling.test.domin.activity;

import com.huling.domain.activity.model.entity.SkuRechargeEntity;
import com.huling.domain.activity.service.IRaffleActivityAccountQuotaService;
import com.huling.domain.activity.service.armory.IActivityArmory;
import com.huling.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityAccountQuotaServiceTest {

    @Resource
    private IRaffleActivityAccountQuotaService raffleOrder;

    @Resource
    private IActivityArmory activityArmory;

    @Before
    public void setUp() {
        log.info("装配活动库存：{}", activityArmory.assembleActivitySku(200L));
    }

    @Test
    public void test_createSkuRechargeOrder_Duplicate() {
        // 测试活动下单入库幂等性
        SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
        skuRechargeEntity.setUserId("huling");
        skuRechargeEntity.setSku(200L);
        skuRechargeEntity.setOutBusinessNo("17333");
        String orderId = raffleOrder.createOrder(skuRechargeEntity);
        log.info("测试结果：{}", orderId);
    }

    @Test
    public void test_createSkuRechargeOrder() throws InterruptedException {
        // 测试库存消耗和最终一致更新
        for (int i = 0; i < 20; i++) {
            try {
                SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
                skuRechargeEntity.setUserId("huling");
                skuRechargeEntity.setSku(200L);
                // outBusinessNo 作为幂等仿重使用，同一个业务单号2次使用会抛出索引冲突 Duplicate entry '700091009111' for key 'uq_out_business_no' 确保唯一性。
                skuRechargeEntity.setOutBusinessNo(RandomStringUtils.randomNumeric(12));
                String orderId = raffleOrder.createOrder(skuRechargeEntity);
                log.info("测试结果：{}", orderId);
            } catch (AppException e) {
                log.warn(e.getInfo());
            }
        }
        new CountDownLatch(1).await();
    }

}
