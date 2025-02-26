package com.huling.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityOrderStateVO {

    completed("completed", "完成");

    private final String code;
    private final String desc;

}
