package com.huling.test.domin;

import com.huling.domain.strategy.service.IStrategyArmory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Test
    public void test_assembleLotteryStrategy() {
        boolean success = strategyArmory.assembleLotteryStrategy(10001L);
        log.info("测试结果：{}", success);
    }

    @Test
    public void test_getRandomAwardId() {
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(10001L));
    }

}
