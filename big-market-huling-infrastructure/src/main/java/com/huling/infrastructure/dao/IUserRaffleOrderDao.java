package com.huling.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import com.huling.infrastructure.dao.po.UserRaffleOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserRaffleOrderDao {

    @DBRouter
    UserRaffleOrder queryNoUsedRaffleOrder(UserRaffleOrder userRaffleOrderReq);

    void insert(UserRaffleOrder userRaffleOrder);

    int updateUserRaffleOrderStateUsed(UserRaffleOrder userRaffleOrderReq);

}
