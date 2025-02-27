package com.huling.domain.task.model.entity;

import lombok.Data;

@Data
public class TaskEntity {

    /** 用户ID */
    private String userId;
    /** 消息主题 */
    private String topic;
    /** 消息编号 */
    private String messageId;
    /** 消息主体 */
    private String message;

}
