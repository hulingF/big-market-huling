package com.huling.test.domin.strategy;

import com.huling.domain.strategy.service.armory.IStrategyArmory;
import com.huling.domain.strategy.service.armory.IStrategyDispatch;
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
    @Resource
    private IStrategyDispatch strategyDispatch;

    @Test
    public void test_assembleLotteryStrategy() {
        boolean success = strategyArmory.assembleLotteryStrategy(10001L);
        log.info("测试结果：{}", success);
    }

    @Test
    public void test_getRandomAwardId() {
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L));
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L));
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L));
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L));
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L));
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L));
    }

    @Test
    public void test_getRandomAwardIdForRuleWeight() {
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L, "6000"));
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L, "6000"));
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L, "6000"));
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L, "6000"));
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(10001L, "6000"));
    }

}
