package com.huling.test.domin;

import com.huling.domain.strategy.service.armory.IStrategyArmory;
import com.huling.domain.strategy.service.rule.chain.ILogicChain;
import com.huling.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import com.huling.domain.strategy.service.rule.chain.impl.RuleWeightLogicChain;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogicChainTest {

    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private RuleWeightLogicChain ruleWeightLogicChain;
    @Resource
    private DefaultChainFactory defaultChainFactory;

    @Before
    public void setUp() {
        // 策略装配 10001
        log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(10001L));
    }

    @Test
    public void test_LogicChain_rule_blacklist() {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(10001L);
        Long awardId = logicChain.logic("user1", 10001L);
        log.info("测试结果：{}", awardId);
    }

    @Test
    public void test_LogicChain_rule_weight() {
        // 通过反射 mock 规则中的值
        ReflectionTestUtils.setField(ruleWeightLogicChain, "userScore", 7000L);
        ILogicChain logicChain = defaultChainFactory.openLogicChain(10001L);
        Long awardId = logicChain.logic("user0", 10001L);
        log.info("测试结果：{}", awardId);
    }

    @Test
    public void test_LogicChain_rule_default() {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(10001L);
        Long awardId = logicChain.logic("user0", 10001L);
        log.info("测试结果：{}", awardId);
    }

}