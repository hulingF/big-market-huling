package com.huling.test.infrastructure;

import com.alibaba.fastjson.JSON;
import com.huling.infrastructure.dao.IRaffleActivityDao;
import com.huling.infrastructure.dao.po.RaffleActivity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityDaoTest {

    @Resource
    private IRaffleActivityDao raffleActivityDao;


    @Test
    public void test_queryRaffleActivityByActivityId() {
        // 不分库表
        RaffleActivity raffleActivity = raffleActivityDao.queryRaffleActivityByActivityId(10031L);
        log.info("测试结果：{}", JSON.toJSONString(raffleActivity));
    }

}
