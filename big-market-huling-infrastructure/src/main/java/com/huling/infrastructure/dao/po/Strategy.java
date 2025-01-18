package com.huling.infrastructure.dao.po;

import lombok.Data;

import java.util.Date;

/**
 * 抽奖策略
 * IDEA快捷键：Shift+Alt选择数据库脚本字段名，Alt下拉，Shift+Tab拉回行头，Clt+右箭头回到单词尾部
 */
@Data
public class Strategy {

    /** 自增ID */
    private Long id;
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖策略描述 */
    private String strategyDesc;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
