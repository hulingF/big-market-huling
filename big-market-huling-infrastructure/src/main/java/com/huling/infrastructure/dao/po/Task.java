package com.huling.infrastructure.dao.po;

import lombok.Data;

import java.util.Date;

@Data
public class Task {

    /** 自增ID */
    private Long id;
    /** 消息主题 */
    private String topic;
    /** 消息主体 */
    private String message;
    /** 任务状态；create-创建、completed-完成、fail-失败 */
    private String state;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}