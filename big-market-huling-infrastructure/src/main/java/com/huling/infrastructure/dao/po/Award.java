package com.huling.infrastructure.dao.po;

import lombok.Data;

import java.util.Date;

/**
 * 抽奖奖品
 */
@Data
public class Award {

    /** 自增ID */
    private Long id;
    /** 抽奖奖品ID */
    private Long awardId;
    /** 奖品对接标识-对应发奖策略 */
    private String awardKey;
    /** 奖品配置信息 */
    private String awardConfig;
    /** 奖品内容描述 */
    private String awardDesc;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
