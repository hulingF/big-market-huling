package com.huling.infrastructure.dao;

import com.huling.infrastructure.dao.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAwardDao {

    List<Award> queryAwardList();

}
