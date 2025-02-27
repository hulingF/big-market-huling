package com.huling.domain.award.model.aggregate;

import com.huling.domain.award.model.entity.TaskEntity;
import com.huling.domain.award.model.entity.UserAwardRecordEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAwardRecordAggregate {

    UserAwardRecordEntity userAwardRecordEntity;
    TaskEntity taskEntity;

}
