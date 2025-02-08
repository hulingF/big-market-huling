package com.huling.test.infrastructure;

import com.alibaba.fastjson.JSON;
import com.huling.infrastructure.dao.IRaffleActivityOrderDao;
import com.huling.infrastructure.dao.po.RaffleActivityOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityOrderDaoTest {

    @Resource
    IRaffleActivityOrderDao raffleActivityOrderDao;

    @Test
    public void test_insert() {
        // 分库分表
        RaffleActivityOrder raffleActivityOrder = new RaffleActivityOrder();
        raffleActivityOrder.setUserId("huling02");
        raffleActivityOrder.setActivityId(100301L);
        raffleActivityOrder.setActivityName("测试活动");
        raffleActivityOrder.setStrategyId(10002L);
        raffleActivityOrder.setOrderId(RandomStringUtils.randomNumeric(12));
        raffleActivityOrder.setOrderTime(new Date());
        raffleActivityOrder.setState("not_used");
        // 插入数据
        raffleActivityOrderDao.insert(raffleActivityOrder);
        // 查询数据
        List<RaffleActivityOrder> raffleActivityOrderList = raffleActivityOrderDao.queryRaffleActivityOrderByUserId("huling02");
        log.info("测试结果：{}", JSON.toJSONString(raffleActivityOrderList));
    }

}
